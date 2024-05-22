package cn.hanamizu.campushelp.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "cos")
public class COSConfig {
    private String secretId;
    private String secretKey;
    private String region;

}
