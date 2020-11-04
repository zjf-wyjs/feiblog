package plus.feifei.feiblog.aspect;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import plus.feifei.feiblog.shiro.AccountProfile;
import plus.feifei.feiblog.util.ShiroUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志打印的aop
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class LogAspect {



    @Pointcut("execution(* plus.feifei.feiblog.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        Object[] args = joinPoint.getArgs();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        String role="";
        AccountProfile profile = ShiroUtil.getProfile();
        if(profile!=null){
            role=profile.getUsername();
        }
        RequestLog requestLog = new RequestLog(url, ip, classMethod, role,args);
        log.info("Request : {}", requestLog);

    }

    @AllArgsConstructor
    @ToString
    private class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private String role;
        private Object[] args;


    }
}
