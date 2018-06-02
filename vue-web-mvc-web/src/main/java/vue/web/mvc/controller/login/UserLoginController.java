package vue.web.mvc.controller.login;

import vue.web.mvc.commom.util.http.ResponseDomain;
import vue.web.mvc.db.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import vue.web.mvc.service.user.AppUserDetailsService;
import vue.web.mvc.service.user.SecurityService;
import vue.web.mvc.commom.user.UserException;
import vue.web.mvc.service.user.WebAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/20 16:45
 * Description:
 */
@RestController
@RequestMapping("api/web")
public class UserLoginController {

    @Autowired
    SecurityService securityService;

    @Autowired
    private AppUserDetailsService userDetailsService;

    @Autowired
    SecurityContextRepository securityContextRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    @RequestMapping(value = "logIn", method = RequestMethod.POST)
    public ResponseDomain<UserInfo> logIn(@RequestBody LoginParam loginParam,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        //this is must
        HttpRequestResponseHolder holder = new HttpRequestResponseHolder(request,
                response);
        //创建Session
        request.getSession();
        ResponseDomain<UserInfo> responseDomain = new ResponseDomain<>();

        Authentication authentication = new WebAuthenticationToken(loginParam.getUserName(), loginParam.getPassword());
        try {
            authentication = authenticationManager.authenticate(authentication);
            SecurityContext context = securityContextRepository.loadContext(holder);
            context.setAuthentication(authentication);
            securityContextRepository.saveContext(context, holder.getRequest(),
                    holder.getResponse());
            UserInfo info = ((WebAuthenticationToken) authentication).getUserInfo();
            responseDomain.setData(info);
            return responseDomain;
        } catch (Exception e) {
            responseDomain.fail("用户名密码错误");
            return responseDomain;
        }

    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseDomain<UserInfo> register(@RequestBody RegisterParam param,
                                          HttpServletRequest request,
                                          HttpServletResponse response
    ) {
        ResponseDomain<UserInfo> resp = new ResponseDomain<>();
        UserInfo info = new UserInfo();
        info.setUserName(param.getUserName());
        info.setNickName(param.getNickName());
        try {
            info=  userDetailsService.createUser(info);
            String uid=info.getUserId();
            userDetailsService.registerUser(uid,param.getPassword());
            resp= logIn(param,request,response);
        } catch (UserException e) {
            return resp.fail(e.getMessage());
        }
        return resp;
    }


}
