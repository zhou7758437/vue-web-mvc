package vue.web.mvc.commom.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Author     : zh_zhou
 * Create at  : 2017/11/25 17:50
 * Description:
 */
public class ServerConfig {

    String wsPath;
    String env;

    static ServerConfig instance=null;


    public String getWsPath() {
        return wsPath;
    }

    public void setWsPath(String wsPath) {
        this.wsPath = wsPath;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
        instance=this;
    }


}
