package com.twitter.security;

import com.twitter.dto.Session;
import com.twitter.repository.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.twitter.exception.ErrorCode.TWTR10007;

@Component
@Order(1)
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private SessionRepository sessionDao;

    @Autowired
    private UsersService usersService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            log.info("Token received from the client is: {} ", jwt);
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                Object roles = tokenProvider.getRolesByToken(jwt);
                List<String> teamRoles;
                if (roles instanceof Collection) {
                    teamRoles = new ArrayList<>((Collection<String>) roles);
                } else {
                    logger.error("Unexpected result in fetching roles from token.");
                    throw new UsernameNotFoundException(TWTR10007.getErrorMessage());
                }
                log.info("Roles in the token are: {}", teamRoles);
                if(teamRoles.get(0).equals("user")) {
                    setSecurityContext(jwt, teamRoles, request);
                }
            }
        }catch (Exception ex) {
            log.error("Could not set user authentication in security context {}", ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private void setSecurityContext(String jwt,
                                    List<String> teamRoles,
                                    HttpServletRequest request){
        Session session = getSessionFromToken(jwt);
        String uuid = tokenProvider.getUUIDFromToken(jwt);
        UserDetails userDetails = usersService.loadUserByUUID(uuid, teamRoles);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        setDetailsInSecurityContext(authentication, request, teamRoles,session);
    }

    private Session getSessionFromToken(String jwt){
        String sessionValue = getSessionValueFromJwt(jwt);
        Long sessionId = getSessionId(sessionValue);
        return Session.builder().id(sessionId).sessionValue(sessionValue).build();
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        logger.info("Authorization Header is missing or in incorrect format.");
        return null;
    }

    private Long getSessionId(String sessionValue){
        Long sessionId = sessionDao.findIdBySessionValue(sessionValue);
        if(sessionId == null){
            logger.error("Error in session id because it could be not be found in database. it is null");
            throw new UsernameNotFoundException(TWTR10007.getErrorMessage());
        }
        return sessionId;
    }

    private String getSessionValueFromJwt(String jwt){
        Object sessionValue = tokenProvider.getSessionIdFromToken(jwt);
        if(sessionValue == null){
            logger.error("Error in session value. Something is wrong. It should not happen. it is null");
            throw new UsernameNotFoundException(TWTR10007.getErrorMessage());
        }
        return sessionValue.toString();
    }

    private void setDetailsInSecurityContext(UsernamePasswordAuthenticationToken authentication,
                                             HttpServletRequest request,
                                             List<String> roles,
                                             Session session){
        authentication.setDetails(UserAdditionalDetails.builder()
                .session(session)
                .ip(request.getRemoteAddr())
                .roles(roles)
                .build());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        logger.info("security context set correctly");
    }
}
