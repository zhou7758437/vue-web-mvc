package vue.web.mvc.controller.user;

import vue.web.mvc.db.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vue.web.mvc.service.user.SecurityService;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/24 13:03
 * Description:
 */
@RestController
@RequestMapping("spring/user")
public class UserInfoController {

    @Autowired
    SecurityService securityService;

    @RequestMapping(value = "curUser", method = RequestMethod.GET)
    public UserInfo getCurrentUser( ) {
        return securityService.currentUser();
    }
}
