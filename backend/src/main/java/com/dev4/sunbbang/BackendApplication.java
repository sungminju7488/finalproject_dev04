package com.dev4.sunbbang;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		if (!new File("C://images/").exists()) {
			try{
				new File("C://images/").mkdir();
			}
			catch(Exception e){
				e.getStackTrace();
			}
		}
		if (!new File("C://images/bakery/").exists()) {
			try{
				new File("C://images/bakery/").mkdir();
			}
			catch(Exception e){
				e.getStackTrace();
			}
		}
		if (!new File("C://images/food/").exists()) {
			try{
				new File("C://images/food/").mkdir();
			}
			catch(Exception e){
				e.getStackTrace();
			}
		}
		if (!new File("C://images/article/").exists()) {
			try{
				new File("C://images/article/").mkdir();
			}
			catch(Exception e){
				e.getStackTrace();
			}
		}
		SpringApplication.run(BackendApplication.class, args);
	}

}
