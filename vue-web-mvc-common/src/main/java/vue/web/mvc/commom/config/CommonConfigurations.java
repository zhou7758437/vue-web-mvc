package vue.web.mvc.commom.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class CommonConfigurations {

    @Primary            //this indicates this is the default DataSource
    @Bean
    @ConfigurationProperties("app.server")
    public ServerConfig serverConfig(){
        return new ServerConfig();
    }
}
