package com.nvke.restfulcrud.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**如何自己写区域信息解析器：可在链接上携带区域信息
 * @author nvke
 * @create 2020-07-16-22:05
 */
public class MyLocaleResovler implements LocaleResolver {
    @Override//解析区域信息
    public Locale resolveLocale(HttpServletRequest request) {
        //从请求中获取参数对应的值，有值就用参数的值
        String l = request.getParameter("l");
        Locale locale = Locale.getDefault();//new一个请求头，用于接收页面返回的国际化值;没有值，就用参数系统默认的
        //检查参数是否有值
        if (!StringUtils.isEmpty(l)){//如果l有值，不为空
            String[] split = l.split("_");//将接收到的字符串，通过_拆分为string数组
            locale = new Locale(split[0],split[1]);//将值赋给请求头
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
