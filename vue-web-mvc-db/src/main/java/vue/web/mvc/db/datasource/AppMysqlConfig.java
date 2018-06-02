package vue.web.mvc.db.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class AppMysqlConfig {

    @Primary            //this indicates this is the default DataSource
    @Bean
    @ConfigurationProperties("spring.datasource.druid.main")
    public DataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }

}
