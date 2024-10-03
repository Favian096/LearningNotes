package com.example.Cfg;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//设置为配置文件, 才会被SpringBoot加载
@Configuration
public class MPConfig {
    //    同样MybatisPlusInterceptor作为bean, 需要使用@Bean注解才会被扫描加入到容器中
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        生成拦截器并加入 分页拦截器(PaginationInnerInterceptor)
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
