package org.x.study.shirodemo.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.x.study.shirodemo.shiro.CustomRealm;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl如果不设置值，默认会自动寻找Web工程根目录下的/login.jsp页面或/login映射
        shiroFilterFactoryBean.setLoginUrl("/notLogin");
        // 设置无权限时跳转url
        shiroFilterFactoryBean.setUnauthorizedUrl("/notRole");

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //  xhy 添加的默认地址
        filterChainDefinitionMap.put("/", "anon");
        // 游客，开放权限
        filterChainDefinitionMap.put("/guest/**", "anon");
        // 用户，需要user角色
        filterChainDefinitionMap.put("/user/**", "roles[user]");
        // 管理员，需要admin角色
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        // 开放登录接口
        filterChainDefinitionMap.put("/login", "anon");
        // 其余接口一律拦截
        // 这行代码必须放在权限设置的最后，不然会导致所有url都被拦截
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * 注入securityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    /**
     * 自定义身份认证realm
     * 必须写这个类，并加上@Bean注解，目的是注入CustomRealm，
     * 否则会影响CustomRealm类中其他类的依赖注入
     * @return realm实体
     */
    @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }
}