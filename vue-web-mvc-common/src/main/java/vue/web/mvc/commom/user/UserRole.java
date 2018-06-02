package vue.web.mvc.commom.user;

/**
 * Author     : zh_zhou
 * Create at  : 2018/1/15 11:15
 * Description:
 */
public enum UserRole {
    Admin("超级管理员");


    final String displayName;


    public String getDisplayName() {
        return displayName;
    }

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public static UserRole getByName(String roleName) {
        for (UserRole role : UserRole.values()) {
            if(role.name().equalsIgnoreCase(roleName)){
                return role;
            }
        }
        return null;
    }
}
