package vue.web.mvc.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

/**
 * Created by Administrator on 2017/12/21.
 */
@EnableJdbcHttpSession(maxInactiveIntervalInSeconds= 3600*24)
public class SessionConfig {
}
