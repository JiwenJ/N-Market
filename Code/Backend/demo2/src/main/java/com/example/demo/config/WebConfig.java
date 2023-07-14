package com.example.demo.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("file:/home/jjw/Desktop/server/");
//        registry.addResourceHandler("/**").addResourceLocations("file:F:\\ALL\\demo2\\src\\main\\resources\\static\\");
//        registry.addResourceHandler("/images/**").addResourceLocations("file:F:\\ALL\\demo2\\src\\main\\resources\\static\\images\\allgoods\\");
    }

//    @Override
    /**
     * 争对跨域问题：
     * 1. 跨域路径
     * 2. 请求来源
     * 3. 跨域方法
     * 4. 最大响应时间
     * 5. 允许携带数据
     */

//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8081", "null")
//                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
//                .maxAge(3600)
//                .allowCredentials(true);
//    }
}
