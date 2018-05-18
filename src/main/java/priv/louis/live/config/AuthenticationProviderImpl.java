package priv.louis.live.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import priv.louis.live.config.domain.AuthenticationTokenImpl;
import priv.louis.live.config.domain.SessionUser;
import priv.louis.live.service.RedisService;
import priv.louis.live.service.UserService;
import priv.louis.live.vo.User;

import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AuthenticationProviderImpl implements org.springframework.security.authentication.AuthenticationProvider {

    private UserService userService;

    private RedisService service;

    public AuthenticationProviderImpl(RedisService service,UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        if (username == null) {
            throw new BadCredentialsException("Username not found.");
        }
        if (password == null) {
            throw new BadCredentialsException("Password not found.");
        }

        User user = new User();
        user = userService.getByName(username);
        if (user != null && user.getId() != null) {
            SessionUser u = new SessionUser();
            u.setUsername(username);
            u.setCreated(new Date());
            AuthenticationTokenImpl auth = new AuthenticationTokenImpl(u.getUsername(), Collections.emptyList());
            auth.setAuthenticated(true);
            auth.setDetails(u);
            service.setValue(String.format("%s:%s", u.getUsername().toLowerCase(), auth.getHash()), u, TimeUnit.SECONDS, 3600L, true);
            return auth;
        } else {

        }
        return null;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

}
