package vue.web.mvc.controller.login;

/**
 * Author     : zh_zhou
 * Create at  : 2018/1/16 9:46
 * Description:
 */
public class RegisterParam extends LoginParam {
    String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
