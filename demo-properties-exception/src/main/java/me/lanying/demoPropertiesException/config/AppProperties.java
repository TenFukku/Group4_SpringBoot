package me.lanying.demoPropertiesException.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data // Annotation của Lombok để tự tạo getter, setter, toString, ...
@ConfigurationProperties(prefix = "app.info")
public class AppProperties {
    private String namel;
    private String version;
    private String author;
}
