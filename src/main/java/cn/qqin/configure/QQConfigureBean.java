package cn.qqin.configure;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author:cgz
 * @Description:
 * @Date: create in 22:09 2019/1/14
 * @Version:
 * @Modified by:
 */
@Configuration
public class QQConfigureBean {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean(destroyMethod = "close",initMethod = "init")
    public DruidDataSource getDataSource() {/*返回需要是DruidDataSource，因为DruidDataSource才有close ，init 方法，假如没有destroyMethod = "close",initMethod = "init"那么会出现(*) property for user to setup错误*/
        return new DruidDataSource();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
