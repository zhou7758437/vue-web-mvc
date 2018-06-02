package vue.web.mvc.service.user;

import vue.web.mvc.commom.user.UserRole;
import vue.web.mvc.db.user.UserInfo;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/20 17:01
 * Description:
 */
public interface SecurityService {
    UserInfo currentUser();

    boolean tryLogin(String username, String password);


    boolean hasAnyRole(UserRole... roles);

    boolean hasAllRole(UserRole... roles);

    boolean hasRole(UserRole auth);
}
