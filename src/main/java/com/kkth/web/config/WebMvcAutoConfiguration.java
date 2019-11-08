package com.kkth.web.config;

import com.kkth.framework.aspect.LogRecord;
import com.kkth.framework.spring.IEnumConverterFactory;
import com.kkth.framework.spring.LicheeHandlerExceptionResolver;
import com.kkth.framework.spring.validator.ValidatorCollectionImpl;
import com.kkth.framework.utils.JacksonUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <p>
 * Spring Boot 配置
 * </p>
 *
 * @author lichee
 */
@Configuration
public class WebMvcAutoConfiguration implements WebMvcConfigurer {

    @Value("${kkth.image.url}")
    private String imageUrl;

    @Value("${kkth.image.location}")
    private String imageLocation;

    @Value("${kkth.file.url}")
    private String fileUrl;

    @Value("${kkth.file.location}")
    private String fileLocation;

    @Bean
    public LogRecord logRecord() {

        return new LogRecord();
    }

    @Override
    public Validator getValidator() {

        return new SpringValidatorAdapter(new ValidatorCollectionImpl());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverterFactory(new IEnumConverterFactory());
    }

    @Bean
    @ConditionalOnMissingBean(RequestContextListener.class)
    public RequestContextListener requestContextListener() {

        return new RequestContextListener();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.forEach(JacksonUtils.wrapperObjectMapper());
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {

        exceptionResolvers.add(new LicheeHandlerExceptionResolver());
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 资源映射路径
         * addResourceHandler：访问映射路径
         * addResourceLocations：资源绝对路径
         */
        registry.addResourceHandler(imageUrl + "**").addResourceLocations("file:" + imageLocation);
        registry.addResourceHandler(fileUrl + "**").addResourceLocations("file:" + fileLocation);
    }

}
