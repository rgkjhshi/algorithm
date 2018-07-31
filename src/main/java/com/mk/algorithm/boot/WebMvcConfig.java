package com.mk.algorithm.boot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 配置类, 目的是为了把公司的ServletWatcher注册到容器里, 等同于原来的web.xml
 *
 * @author song.shi
 * @since 2016-11-07
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // 禁用根据后缀推测类型功能
        configurer.favorPathExtension(false);
    }

    /**
     * 添加HTTP消息转换器 httpMessageConverter
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        Gson gson = new GsonBuilder().serializeNulls()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);
        converters.add(0, converter);
    }
}
