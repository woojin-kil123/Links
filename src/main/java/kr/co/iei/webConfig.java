package kr.co.iei;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//스프링 부트 설정파일임을 나타내는 어노테이션
@Configuration
@EnableWebMvc
public class webConfig implements WebMvcConfigurer{
	@Value("${file.root}")
	private String root;
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")  // '/'를 계속타고 가도 모든 subdirectory 가 모두 해당됨
				.addResourceLocations("classpath:/templates/","classpath:/static/");	//스프링 부트의 기본설정값
	}
}
