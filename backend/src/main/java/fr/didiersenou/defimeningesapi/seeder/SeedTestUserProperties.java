package fr.didiersenou.defimeningesapi.seeder;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

@ConfigurationProperties(prefix = "app.seed.test-user")
public record SeedTestUserProperties(String username, String email, String password) {

    public boolean isConfigured() {
        return StringUtils.hasText(username) && StringUtils.hasText(email) && StringUtils.hasText(password);
    }
}
