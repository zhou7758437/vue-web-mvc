package vue.web.mvc.db.user;

import org.apache.ibatis.annotations.Param;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/10 9:46
 * Description:
 */
public interface UserInfoDao {

    int countByUserName(@Param("userName") String userName);

    void updateUser(@Param("userId") String userId, @Param("userInfo") UserInfo userInfo);

    void insertUser(UserInfo userInfo);

    UserInfo getByUserName(@Param("userName") String userName);

    UserInfo getByUserId(@Param("userId") String userId);

    void updateUserPass(@Param("userId") String userId, @Param("password") String password, @Param("salt") String salt);

    UserWithCriticalInfo getCriticalByUserName(@Param("userName") String userName);

}
