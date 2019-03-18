package com.gwk;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootConfiguration
//WebMvcConfigurationSupport配置web拦截器，实现其addInterceptors方法添加拦截器
public class WebConfig extends WebMvcConfigurationSupport {
    @Value("${appKey}")
    private String appKey;
    @Value("${appSecret}")
    private String appSecret;
    @Value("${bucket}")
    private String bucket;
    @Value("${endPoint}")
    private String endPoint;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new ApiInterceptor());
    }
    //注册之后可以使用    @Autowired 自动注册对象。。。将注册权交给了bean/spring
    @Bean
    public ApiInterceptor interceptor(){
        return new ApiInterceptor();
    }


    @Bean
    public Aliyun aliyun(){
        //这里方法之后加上方法是因为前置方法返回了一个Aliyun对象。
        return Aliyun.options()
                .setAppKey(appKey)
                .setAppSecret(appSecret)
                .setBucket(bucket)
                .setEndPoint(endPoint)
                .build();
    }



}
