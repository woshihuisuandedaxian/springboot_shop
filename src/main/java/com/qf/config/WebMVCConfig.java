package com.qf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ZouXianTao
 * @Date2019/12/24
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    /** 映射页面路径*/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
           registry.addViewController("toLogin").setViewName("login");
           registry.addViewController("toRegister").setViewName("register");
           registry.addViewController("toAddAddress").setViewName("addAddress");
           registry.addViewController("backLogin").setViewName("back/backLogin");
           registry.addViewController("backMain").setViewName("back/main");
           registry.addViewController("toTop").setViewName("back/top");
           registry.addViewController("toLeft").setViewName("back/left");
           registry.addViewController("toIndex").setViewName("back/index");
           registry.addViewController("goodsAdd").setViewName("back/goodstype/goodsadd");
           registry.addViewController("addUser").setViewName("back/user/addUser");
           registry.addViewController("addGoods").setViewName("back/goods/addGoods");

    }

  /** 图片地址在系统盘符的映射*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/picture/**").addResourceLocations("file:D:/picture/");
    }
}
