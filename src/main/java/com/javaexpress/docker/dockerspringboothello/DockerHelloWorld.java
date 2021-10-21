package com.javaexpress.docker.dockerspringboothello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
@RequestMapping("/v1/docker")
@SpringBootApplication
public class DockerHelloWorld {

	public static void main(String[] args) {
		SpringApplication.run(DockerHelloWorld.class, args);
	}
}
