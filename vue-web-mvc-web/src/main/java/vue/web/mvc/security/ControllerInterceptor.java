package vue.web.mvc.security;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vue.web.mvc.commom.user.UserRole;
import vue.web.mvc.service.user.SecurityService;

import java.lang.reflect.Method;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/24 13:06
 * Description:
 */
@Aspect
@Component
public class ControllerInterceptor {

    @Autowired
    SecurityService securityService;

    static JSONObject ACCESS_FAILED=new JSONObject();

    static {
        ACCESS_FAILED.put("status",-1);
        ACCESS_FAILED.put("message","权限不足");
    }

    /**
     * 定义拦截规则：拦截com.xjj.web.controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Around("execution(* vue.web.mvc..*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object controllerMethodPointcut(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        Class controllerClass = pjp.getTarget().getClass();
        if(accept(controllerClass,method)){
            Object result = pjp.proceed();
            return result;
        }else{
            return ACCESS_FAILED;
        }

    }

    boolean accept( Class<?> controllerClass,Method controllerMethod) {
        AppAccessible methodAccessable = controllerMethod.getAnnotation(AppAccessible.class);
        AppAccessible classAccessable = controllerClass.getAnnotation(AppAccessible.class);
        boolean accept=accept(classAccessable)&accept(methodAccessable);
        return accept;

    }

    boolean accept(AppAccessible accessible) {
        if (accessible == null) {
            return true;
        }
        UserRole[] authorities = accessible.AcceptRoles();
        if (authorities == null || authorities.length == 0) {
            return true;
        }
        boolean accept;
        if(accessible.allAccept()){
            accept=securityService.hasAllRole(authorities);
        }else{
            accept=securityService.hasAnyRole(authorities);
        }
        return accept;
    }
}
