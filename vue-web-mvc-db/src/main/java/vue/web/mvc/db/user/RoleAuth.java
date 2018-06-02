package vue.web.mvc.db.user;

import vue.web.mvc.db.CommonTableItem;

/**
 * Author     : zh_zhou
 * Create at  : 2018/1/15 12:30
 * Description:
 */
public class RoleAuth extends CommonTableItem {
    String roleName;
    String authName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }
}
