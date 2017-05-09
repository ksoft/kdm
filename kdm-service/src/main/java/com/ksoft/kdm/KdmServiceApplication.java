package com.ksoft.kdm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class KdmServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KdmServiceApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Thread.currentThread().join();
	}
}
