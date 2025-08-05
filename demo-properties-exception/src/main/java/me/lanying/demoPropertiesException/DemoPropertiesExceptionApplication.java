package me.lanying.demoPropertiesException;

import me.lanying.demoPropertiesException.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
// Kích hoạt việc sử dụng lớp AppProperties làm một bean cấu hình
@EnableConfigurationProperties(AppProperties.class)
public class DemoPropertiesExceptionApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoPropertiesExceptionApplication.class, args);
    }
}