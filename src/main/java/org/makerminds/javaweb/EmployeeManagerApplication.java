package org.makerminds.javaweb;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EmployeeManagerApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(EmployeeManagerApplication.class, args);
		
		for(String bean: applicationContext.getBeanDefinitionNames()) {
			System.out.println(bean);
		}
	}

}
