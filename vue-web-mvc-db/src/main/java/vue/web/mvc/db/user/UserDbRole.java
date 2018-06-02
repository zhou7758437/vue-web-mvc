package vue.web.mvc.db.user;

import vue.web.mvc.db.CommonTableItem;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/24 12:40
 * Description:
 */
public class UserDbRole extends CommonTableItem {
    String userId;
    String roleName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
