package com.happy.live.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * 类名:HappyLiveApplication <tb>
 * 描述:请输入类描述  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2019/8/16 10:08 <tb>
 */
@SpringBootApplication(scanBasePackages = {"com.happy.live.module","com.happy.live.common"})
public class ModuleApplication {

    private static final Logger logger = LoggerFactory.getLogger(ModuleApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(ModuleApplication.class);
        Map<String, Object> defProperties =  new HashMap<>();
        defProperties.put("spring.profiles.default", "test");
        app.setDefaultProperties(defProperties);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        logger.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }
}
