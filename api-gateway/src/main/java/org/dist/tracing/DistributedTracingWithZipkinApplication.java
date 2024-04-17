package org.dist.tracing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistributedTracingWithZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributedTracingWithZipkinApplication.class, args);
	}

}
