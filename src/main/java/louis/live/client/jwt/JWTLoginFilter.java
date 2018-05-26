package louis.live.client.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import louis.live.client.config.domain.AuthenticationTokenImpl;
import louis.live.client.config.domain.SessionUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private TokenAuthenticationService tokenAuthenticationService;

    public JWTLoginFilter(String url, AuthenticationManager authenticationManager, TokenAuthenticationService service) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
        tokenAuthenticationService = service;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse hsr1) throws AuthenticationException, IOException, ServletException {
        SessionUser credentials = new ObjectMapper().readValue(httpServletRequest.getInputStream(), SessionUser.class);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication)
            throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin",request.getHeader("origin"));
        response.setHeader("Access-Control-Allow-Origin","*");  //允许跨域访问的域
        response.setHeader("Access-Control-Allow-Methods","*");  //允许使用的请求方法
        response.setHeader("Access-Control-Expose-Headers","*");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Cache-Control,Pragma,Content-Type,Authorization");  //允许使用的请求方法
        response.setHeader("Access-Control-Allow-Credentials","true");//是否允许请求带有验证信息
        AuthenticationTokenImpl auth = (AuthenticationTokenImpl) authentication;
        tokenAuthenticationService.addAuthentication(response, auth); 
    }
}
