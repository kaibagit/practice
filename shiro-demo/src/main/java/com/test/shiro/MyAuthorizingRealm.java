package com.test.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by luliru on 2018/5/27.
 */
public class MyAuthorizingRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "MyAuthorizingRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    //当我们调用subject.login进行认证的方法，用于身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken loginToken=(UsernamePasswordToken)token;
        String password = new String(loginToken.getPassword());
        if(loginToken.getUsername()=="zhang"&&password.equals("123")){

        }else{
            throw new IncorrectCredentialsException();
        }
        SimpleAuthenticationInfo info= new SimpleAuthenticationInfo(loginToken.getUsername(),password,getName());
        return info;
    }

    //授权获得用户权限信息的方法，用于权限认证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addRole("321");
        info.addRole("3332");
        info.addStringPermission("333");
        info.addStringPermission("555");
        info.addObjectPermission(new WildcardPermission("44"));
        return info;
    }
}
