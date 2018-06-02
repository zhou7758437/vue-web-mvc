package vue.web.mvc.service.user;

import vue.web.mvc.db.user.UserInfo;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/21 11:16
 * Description:
 */
public class WebAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 421030078419243246L;

    UserInfo userInfo;
    String userName;
    String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public WebAuthenticationToken(String userName, String password) {
        super(null);
        this.userName = userName;
        this.password = password;
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public WebAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
