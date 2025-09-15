//package com.salonio.modules.user.internal;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//import java.util.UUID;
//
//public class CustomUserDetails extends User {
//
//    @Getter
//    @Setter
//    private final Long id;
//
//    public CustomUserDetails(Long id, String username, String password,
//                             Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//        this.id = id;
//    }
//
//}
//
