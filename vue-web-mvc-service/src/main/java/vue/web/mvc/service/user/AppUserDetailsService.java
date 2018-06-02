package vue.web.mvc.service.user;

import vue.web.mvc.commom.user.UserException;
import vue.web.mvc.db.user.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/20 16:59
 * Description:
 */
public interface AppUserDetailsService extends UserDetailsService {

    UserInfo createUser(UserInfo userInfo) throws UserException;

    
    UserInfo getByUserName(String userName);

    UserInfo getByUserId(String userId);

    void registerUser(String userId, String password);

    UserInfo getByUserNamePass(String username, String password);

}
