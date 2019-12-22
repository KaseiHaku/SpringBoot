package kasei.boot.web.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component // 当有一个自定义的 AuthenticationProvider 类型的 bean 存在于 IOC 容器中时，会关闭 UserDetailsServiceAutoConfiguration 的自动配置
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();
        String credentials = (String) authentication.getCredentials();

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal);
        if (userDetails == null) {
            throw new UsernameNotFoundException("username not found");
        }

        if (credentials.equals(userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(principal, credentials, userDetails.getAuthorities());
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
