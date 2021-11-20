package com.twitter.security;

import com.twitter.dto.Session;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAdditionalDetails {

    private String ip;
    private Session session;
    private List<String> roles;

}
