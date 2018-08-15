package com.oocl.business.aspect;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.oocl.business.model.Business;
import com.oocl.business.model.RespondResult;

@Component
@Aspect
public class EncryptAspect {

    @Pointcut("execution(public * login(..)) || execution(public * register(..))")
    public void pointcut(){

    }

    /**
     * 
     * 登录注册前对密码进行加密
     * @param pjp
     * @return  respondResult
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint  pjp) throws Throwable {
        Object args[] = pjp.getArgs();
        Business business = (Business) args[0];
        String password = business.getPassword();
        business.setPassword(DigestUtils.md5Hex(password));
        RespondResult respondResult = new RespondResult();
        respondResult = (RespondResult)pjp.proceed(args);
        return respondResult;
    }
}
