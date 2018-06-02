package vue.web.mvc.main;

import vue.web.mvc.commom.config.AppServerConfig;
import vue.web.mvc.server.ServerManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Author     : zh_zhou
 * Create at  : 2017/11/25 11:31
 * Description:
 */

@ComponentScan(basePackages = {"vue.web.mvc"})
@MapperScan(basePackages = {"vue.web.mvc.db"})
@SpringBootApplication
public class AppWebMain {
    public static void main(String[] args) throws Exception {
        SpringApplication application=new SpringApplication();
        Properties properties=new Properties();
        properties.setProperty("server.session.timeout", TimeUnit.DAYS.toSeconds(1)+"");
        application.setDefaultProperties(properties);
        ApplicationContext context=application.run(AppWebMain.class, args);

        ServerManager manager=  context.getBean(ServerManager.class);
        manager.startServers(AppServerConfig.TCP_PORT, AppServerConfig.WEB_SOCKET_PORT, AppServerConfig.UDP_PORT);

    }
}
