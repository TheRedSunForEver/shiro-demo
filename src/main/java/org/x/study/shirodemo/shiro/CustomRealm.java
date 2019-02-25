package org.x.study.shirodemo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.x.study.shirodemo.mapper.UserMapper;

import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    public CustomRealm() {
    }

    /**
     * 获取身份信息
     * Shiro中，最终是通过Realm来获取应用程序中的用户、角色及权限信息的
     *
     * @param authenticationToken 用户身份信息token
     * @return 返回封装了用户信息的AuthenticationInfo实例
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("----身份认证方法----");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String password = userMapper.getPassword(token.getUsername());
        if (null == password) {
            throw new AccountException("用户名不正确");
        } else if (!password.equalsIgnoreCase(new String((char[])token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }

        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.printf("----权限认证----");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String role = userMapper.getRole(username);
        Set<String> set = new HashSet<>();
        set.add(role);
        info.setRoles(set);
        return info;
    }
}