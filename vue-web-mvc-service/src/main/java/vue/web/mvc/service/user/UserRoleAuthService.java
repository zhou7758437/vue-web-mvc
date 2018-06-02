package vue.web.mvc.service.user;

import vue.web.mvc.commom.user.UserRole;
import vue.web.mvc.db.user.UserDbRole;
import vue.web.mvc.db.user.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/24 12:48
 * Description:
 */
@Service
public class UserRoleAuthService {
    @Autowired
    UserRoleDao roleDao;

    public Map<String, Boolean> userRoles(String userId) {
        List<UserDbRole> dbRoles=roleDao.getByUserId(userId);
        Map<String,Boolean> roles=new HashMap<>();
        for (UserRole userRole : UserRole.values()) {
            roles.put(userRole.name(),false);
        }
        if(dbRoles!=null){
            for (UserDbRole role : dbRoles) {
                String roleName=role.getRoleName();
                UserRole healthRole=UserRole.getByName(roleName);
                if(healthRole==null){
                    continue;
                }
                roles.put(healthRole.name(),true);
            }
        }
        return roles;
    }





}
