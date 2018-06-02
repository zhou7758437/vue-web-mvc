package vue.web.mvc.db.user;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/24 12:42
 * Description:
 */
public interface UserRoleDao {
    List<UserDbRole> getByUserId(@Param("userId") String userId);
    void insertRoles(@Param("roles") List<UserDbRole> roles);
    void deleteRoles(String openId);
}
