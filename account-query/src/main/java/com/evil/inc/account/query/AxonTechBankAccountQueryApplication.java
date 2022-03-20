package com.evil.inc.account.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AxonTechBankAccountQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AxonTechBankAccountQueryApplication.class, args);
	}

}
