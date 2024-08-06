package hhplus.e_commerce.config;

import hhplus.e_commerce.filter.LogFilter;
import hhplus.e_commerce.filter.LoginCheckFilter;
import hhplus.e_commerce.interceptor.LoggerInterceptor;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/api/*");

        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean loginCheckFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();

        // 로그인 체크 필터 등록하기
        filterFilterRegistrationBean.setFilter(new LoginCheckFilter());
        // 필터 순서 지정
        filterFilterRegistrationBean.setOrder(2);
        // 필터와 매핑되는 URL 패턴 지정
        filterFilterRegistrationBean.addUrlPatterns("/api/*");

        return  filterFilterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/css/**", "/images/**", "/js/**");
    }
}
