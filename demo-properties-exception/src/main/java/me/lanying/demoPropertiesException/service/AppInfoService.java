package me.lanying.demoPropertiesException.service;

import me.lanying.demoPropertiesException.config.AppProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;

@Service
public class AppInfoService {
    private final AppProperties appProperties;

    // Tiêm 1 giá trị thuộc tính duy nhất bằng @Value
    @Value("${app.welcome-message}")
    private String welcomeMessage;

    public AppInfoService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public Map<String, String> getAppDetails() {
        Map<String, String> details = new HashMap<>();
        details.put("welcomeMessage", welcomeMessage);
        details.put("appName", appProperties.getNamel());
        details.put("appVersion", appProperties.getVersion());
        details.put("appAuthor", appProperties.getAuthor());
        return details;
    }
}
