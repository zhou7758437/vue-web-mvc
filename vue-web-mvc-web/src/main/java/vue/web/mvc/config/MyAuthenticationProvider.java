package vue.web.mvc.config;

import vue.web.mvc.db.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import vue.web.mvc.service.user.AppUserDetailsService;
import vue.web.mvc.service.user.UserRoleAuthService;
import vue.web.mvc.service.user.WebAuthenticationToken;

import java.util.Map;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/21 10:30
 * Description:
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AppUserDetailsService userService;

    @Autowired
    UserRoleAuthService roleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication.isAuthenticated()){
            return authentication;
        }
        if(authentication instanceof WebAuthenticationToken){
            WebAuthenticationToken auth=(WebAuthenticationToken)authentication;
            String username = auth.getUserName();
            String password = auth.getPassword();
            UserInfo user =userService.getByUserNamePass(username,password);
            if(user == null){
                throw new BadCredentialsException("Auth Failed");
            }
            auth.setUserInfo(user);
            Map<String,Boolean> roles=roleService.userRoles(user.getUserId());
            user.setRoles(roles);
            auth.setAuthenticated(true);
            return auth;

        }else {
            throw new BadCredentialsException("Bad authentication");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return WebAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
