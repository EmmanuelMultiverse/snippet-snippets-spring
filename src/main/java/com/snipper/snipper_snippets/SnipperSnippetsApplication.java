package com.snipper.snipper_snippets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SnipperSnippetsApplication {

	public static int nextId = 9;
	
	public static void main(String[] args) {

		SpringApplication.run(SnipperSnippetsApplication.class, args);
	}

}
