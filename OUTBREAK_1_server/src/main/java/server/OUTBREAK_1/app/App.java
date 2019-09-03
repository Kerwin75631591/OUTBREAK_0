package server.OUTBREAK_1.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages= "server.OUTBREAK_1")//添加扫包@ComponentScan(basePackages= "")
@EnableAutoConfiguration
public class App{

	//启动springboot
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}