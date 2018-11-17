package com.cs.riskit.rdsrockersbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.cs.riskit.rdsrockersbackend.fileupload.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
