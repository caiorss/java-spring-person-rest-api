package com.mycompany.people.api_person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/** Application Entry Point - main()
 *
 * Replace the annoation @SpringBootApplicatin(exclude = ... )
 * for @SpringApplication in order to enable automatic security auto
 * configuration. Reference: https://www.baeldung.com/spring-boot-security-autoconfiguration
 *
 *--------------------------------------------------*/
@SpringBootApplication // (exclude = { SecurityAutoConfiguration.class })
public class ApiPersonApplication
{

	public static void main(String[] args)
	{
		System.err.println(" [INFO] Spring boot stated. Ok. ");

		// Blocks current thread.
		SpringApplication.run(ApiPersonApplication.class, args);

	}

}
