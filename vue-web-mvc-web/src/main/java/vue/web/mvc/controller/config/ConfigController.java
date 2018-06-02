package vue.web.mvc.controller.config;

import vue.web.mvc.commom.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author     : zh_zhou
 * Create at  : 2017/11/25 17:49
 * Description:
 */
@RestController
@RequestMapping("api")
public class ConfigController {
    @Autowired
    ServerConfig config;


    @RequestMapping(value = "getServerConfig", method = RequestMethod.GET)
    public ServerConfig getServerConfig() {
        return config;
    }

}
