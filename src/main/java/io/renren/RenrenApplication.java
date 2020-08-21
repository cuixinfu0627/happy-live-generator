package io.renren;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan("io.renren.dao")
public class RenrenApplication {

	private static final Logger logger = LoggerFactory.getLogger(RenrenApplication.class);

	/**
	 * 项目启动入口
	 * @param args 启动参数
	 * @throws UnknownHostException 端口异常
	 */
	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(RenrenApplication.class);
		Map<String, Object> defProperties =  new HashMap<>();
		defProperties.put("spring.profiles.default", "local");
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
