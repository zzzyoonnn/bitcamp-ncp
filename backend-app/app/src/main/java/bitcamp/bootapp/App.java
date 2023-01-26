package bitcamp.bootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import bitcamp.bootapp.config.WebConfig;

@SpringBootApplication
public class App {

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
  
  // 스프링부트가 클래스를 자동 생성하지 않는다면,
  // 개발자가 직접 객체를 생성해서 리턴해야 한다.
  // 단, 스프링 부트가 아래 메서드를 호출하게 하려면 표시를 해야 한다.
  @Bean
  public WebMvcConfigurer corsConfigurer() {
	  return new WebMvcConfigurer() {
		  @Override
			public void addCorsMappings(CorsRegistry registry) {
				/*
				CorsRegistration r = registry.addMapping("/**")
		        r.allowedOrigins("http://localhost:5500", "http://127.0.0.1:5500");
		        */
				
				// 체인방식 위와 동일
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:5500", "http://127.0.0.1:5500")
				.allowedMethods("*");
			}
	  };
  }
}
