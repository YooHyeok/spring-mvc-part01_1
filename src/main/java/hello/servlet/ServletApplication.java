package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;

@ServletComponentScan // 서블릿 자동 등록
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

	/**
	 * ViewResolver 설정 직접 Bean으로 등록
	 * InternalResourceViewResolver 는 InternalResourceView를 반환한다. ( buildView() )
	 * InternalResourceView는 jsp처럼 forward()를 호출하는 경우에 사용한다.
	 * (만약 JSTL 라이브러리가 있으면 InternalView를 상속받은 JstlView를 반환한다. - jstl사용시 약간의 부가기능 추가)
	 * view.render가 호출되고 InternalResourceView는 forward를 사용해서 JSP를 실행한다. ( renderMergedOutputModel() )
	 * @return
	 */
	@Bean
	InternalResourceViewResolver internalResourceViewResolver() {
		return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
	}
}