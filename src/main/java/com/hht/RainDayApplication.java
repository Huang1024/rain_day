package com.hht;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@RestController
@Slf4j
public class RainDayApplication {

	@Value("${author.name}")
	private String authorName;

	@GetMapping("/hello")
	public String index(){
		return "Hello "+authorName;
	}

	public static void main(String[] args) throws UnknownHostException {
		ConfigurableApplicationContext context = SpringApplication.run(RainDayApplication.class, args);
		Environment env = context.getEnvironment();
		String port = env.getProperty("server.port", "8088");

		log.info("Access URLs:\n----------------------------------------------------------\n\t"
						+ "Local: \t\thttp://127.0.0.1:{}\n\t"
						+ "External: \thttp://{}:{}\n----------------------------------------------------------",
				port,
				InetAddress.getLocalHost().getHostAddress(),
				port);

	}
}
