package vue.web.mvc.security;


import vue.web.mvc.commom.user.UserRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author     : zh_zhou
 * Create at  : 2017/12/24 13:16
 * Description:
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AppAccessible {
    UserRole[] AcceptRoles() default {};

    boolean allAccept() default true;
}
