package com.tinqinacademy.bff.api.models.usertoken;

import com.tinqinacademy.bff.api.models.emuns.RoleType;
import lombok.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserToken {
    private String token;
    private String username;
    private List<RoleType> roles;
    private Date expiration;
}
