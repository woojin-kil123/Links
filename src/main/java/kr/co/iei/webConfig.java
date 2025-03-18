package kr.co.iei;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.iei.util.AdminInterceptor;
import kr.co.iei.util.LoginInterceptor;

@Configuration
@EnableWebMvc
public class webConfig implements WebMvcConfigurer{
	@Value("${file.root}")
	private String root;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")  // '/'를 계속타고 가도 모든 subdirectory 가 모두 해당됨
				.addResourceLocations("classpath:/templates/","classpath:/static/");	//스프링 부트의 기본설정값
		registry.addResourceHandler("/news/editor/**").addResourceLocations("file:///"+root+"/news/editor/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//로그인 인터셉터 경로 추가
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns(
								"/member/mypage",
								"/member/delete",
								"/member/changeinfo",
								"/member/inquiryFrm",
								"/member/ajaxcommNo",
								"/member/ajaxscoreNo",
								"member/inquiryFrm",
								"/report/**",
								"/comment/**",
								"/contents/insertRating",
								"/contents/insertContentLike",
								"/contents/deleteContentLike",
								"/contents/myContents",
								"/contents/selectMyContents",
								"/admin/**"
								
								)
				.excludePathPatterns("/admin/plusAdClick",
									"/admin/adUrl",
									"/admin/insertInquiry",
									"/admin/insertReport",
									"/comment/mCommentMemberList",
									"/comment/count",
									"/comment/newMovieComment",
									"/comment/oneMovieComments"
									);
		//관리자 페이지 기능 경로 추가
		registry.addInterceptor(new AdminInterceptor())
				.addPathPatterns("/admin/**",
								"/news//writeFrmEditor",
								"/news/write",
								"/news/delete",
								"/news/updateFrm",
								"/news/update",
								"/news/editorImage"
								)
				.excludePathPatterns(
								"/admin/plusAdClick",
								"/admin/adUrl",
								"/admin/insertInquiry",
								"/admin/insertReport",
								"/comment/mCommentMemberList",
								"/comment/count"
						);
	}
}
