package vue.web.mvc.service.user;

import com.github.binarywang.java.emoji.EmojiConverter;
import vue.web.mvc.commom.user.UserException;
import vue.web.mvc.commom.util.AppStringUtil;
import vue.web.mvc.db.user.UserInfo;
import vue.web.mvc.db.user.UserInfoDao;
import vue.web.mvc.db.user.UserWithCriticalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vue.web.mvc.service.util.EncodeUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/12 13:32
 * Description:
 */
@Service
public class UserDetailsServiceImpl implements AppUserDetailsService {
    @Autowired
    UserInfoDao userInfoDao;

    private EmojiConverter emojiConverter = EmojiConverter.getInstance();

    @Override
    public UserInfo createUser(UserInfo userInfo) throws UserException {
        String content=userInfo.getNickName();
        content=emojiConverter.toHtml(content);
        userInfo.setNickName(content);
        UserInfo db = userInfoDao.getByUserName(userInfo.getUserName());
        if (db != null) {
            throw new UserException("该用户已存在");
        } else {
            String userId=generateUserId();
            userInfo.setUserId(userId);
            userInfoDao.insertUser(userInfo);
            db=userInfoDao.getByUserId(userId);
            if(db==null){
                throw new UserException("创建用户失败");
            }
        }
        return db;
    }


    private String generateUserId() {
        return AppStringUtil.randomString(4,6,"-");
    }

    @Override
    public UserInfo getByUserName(String userName) {
        return userInfoDao.getByUserName(userName);
    }

    @Override
    public UserInfo getByUserId(String userId) {
        return userInfoDao.getByUserId(userId);
    }

    @Override
    public void registerUser(String userId, String password) {
        String salt = AppStringUtil.randomString(10);
        String encryptPassword = encrypt(password, salt);
        userInfoDao.updateUserPass(userId, encryptPassword, salt);
    }

    @Override
    public UserInfo getByUserNamePass(String username, String password) {
        UserWithCriticalInfo criticalInfo = userInfoDao.getCriticalByUserName(username);
        String salt = criticalInfo.getSalt();
        password = encrypt(password, salt);
        if (password.equals(criticalInfo.getPassword())) {
            criticalInfo.setSalt(null);
            criticalInfo.setPassword(null);
            return criticalInfo;
        } else {
            return null;
        }

    }




    String encrypt(String password, String salt) {
        password += salt;
        password = EncodeUtil.SHA256(password);
        return password;

    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserInfo info = userInfoDao.getByUserName(userName);
        if(info==null){
            return null;
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        WebUserDetails user = new WebUserDetails(userName, "password", grantedAuthorities);
        user.setUserInfo(info);
        return user;
    }


}
