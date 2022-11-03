package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wby
 * @version 1.0
 * @date 2022/11/3 16:06
 */
@Setter
@Getter
public class LoginUser implements UserDetails {

    private String token;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public LoginUser(String username, String password,List<String> expressions) {
        this.username = username;
        this.password = password;
        if (!CollectionUtils.isEmpty(expressions)) {
            this.authorities = expressions.stream()
                    .map(SimpleGrantedAuthority::new).
                    collect(Collectors.toList());
        }
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
