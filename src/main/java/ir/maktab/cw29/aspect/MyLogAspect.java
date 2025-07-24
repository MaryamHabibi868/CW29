package ir.maktab.cw29.aspect;

import ir.maktab.cw29.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Aspect
@Slf4j
public class MyLogAspect {


    @Pointcut("@annotation(LogAspect)")
    public void logPointCut(){

    }

    @Around("logPointCut()")
    public Object logAdvice(ProceedingJoinPoint pjp) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String actionId = UUID.randomUUID().toString();
        log.info("user [{}] – request for {} with id {}",user.getId(), pjp.getSignature().getName(), actionId);
        Object result;
        try {
            result = pjp.proceed();
            log.info("user [{}] – request for {} with id {} Done",user.getId(), pjp.getSignature().getName(), actionId);
        } catch (Throwable e) {
            log.error("user [{}] – request for {} with id {} Fail",user.getId(), pjp.getSignature().getName(), actionId);
            throw new RuntimeException(e);
        }
        return result;

    }

}
