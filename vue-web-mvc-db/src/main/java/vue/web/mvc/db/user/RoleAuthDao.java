package vue.web.mvc.db.user;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Author     : zh_zhou
 * Create at  : 2018/1/15 12:31
 * Description:
 */
public interface RoleAuthDao {
    List<RoleAuth> getByRole(@Param("roleName") String roleName);
}
