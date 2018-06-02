package vue.web.mvc.service.user;

import vue.web.mvc.commom.user.UserRole;
import vue.web.mvc.db.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;


/**
 * Author     : zh_zhou
 * Create at  : 2017/12/20 17:00
 * Description:
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserDetailsService userDetailsService;

    @Override
    public UserInfo currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        if (authentication instanceof WebAuthenticationToken) {
            WebAuthenticationToken auth = (WebAuthenticationToken) authentication;
            return auth.getUserInfo();
        }
        return null;
    }

    @Override
    public boolean tryLogin(String username, String password) {
        Authentication authentication = new WebAuthenticationToken(username, password);
        try {
            authentication = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean hasAnyRole(UserRole... roles) {
        return internalAccept(new AcceptHandler() {
            @Override
            public boolean accept(Map<String, Boolean> userAuthorities, UserRole... authorities) {
                for (UserRole auth : authorities) {
                    Boolean accept = userAuthorities.get(auth.name());
                    if (Boolean.TRUE.equals(accept)) {
                        return true;
                    }
                }
                return false;
            }
        }, roles);
    }


    @Override
    public boolean hasAllRole(UserRole... roles) {
        return internalAccept(new AcceptHandler() {
            @Override
            public boolean accept(Map<String, Boolean> userAuthorities, UserRole... authorities) {

                for (UserRole auth : authorities) {
                    Boolean accept = userAuthorities.get(auth.name());
                    if (Boolean.FALSE.equals(accept)) {
                        return false;
                    }
                }
                return true;
            }
        }, roles);
    }

    @Override
    public boolean hasRole(UserRole auth) {
        return hasAllRole(new UserRole[]{auth});
    }


    boolean internalAccept(AcceptHandler handler, UserRole... roles) {
        UserInfo current = currentUser();
        if (current == null) {
            return false;
        }
        Map<String, Boolean> userRoles = current.getRoles();
        if (userRoles == null) {
            return false;
        }
        return handler.accept(userRoles, roles);
    }

    interface AcceptHandler {
        boolean accept(Map<String, Boolean> userRoles, UserRole... roles);
    }


}
