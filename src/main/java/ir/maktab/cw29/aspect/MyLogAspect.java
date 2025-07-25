package ir.maktab.cw29.aspect;

import ir.maktab.cw29.document.ActionStatus;
import ir.maktab.cw29.domain.User;
import ir.maktab.cw29.dto.LogDto;
import ir.maktab.cw29.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Aspect
@Slf4j
public class MyLogAspect {
    private final LogService logService;

    public MyLogAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("@annotation(LogAspect)")
    public void logPointCut(){

    }

    @Around("logPointCut()")
    public Object logAdvice(ProceedingJoinPoint pjp) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String actionId = UUID.randomUUID().toString();
        log.info("user [{}] – request for {} with id {}",user.getId(), pjp.getSignature().getName(), actionId);
        saveLog(pjp, user, actionId, ActionStatus.START);
        Object result;
        try {
            result = pjp.proceed();
            log.info("user [{}] – request for {} with id {} Done",user.getId(), pjp.getSignature().getName(), actionId);
            saveLog(pjp, user, actionId,ActionStatus.DONE);
        } catch (Throwable e) {
            log.error("user [{}] – request for {} with id {} Fail",user.getId(), pjp.getSignature().getName(), actionId);
            saveLog(pjp, user, actionId, ActionStatus.FAILED);
            throw new RuntimeException(e);
        }
        return result;

    }

    private void saveLog(ProceedingJoinPoint pjp, User user, String actionId, ActionStatus actionStatus) {
        LogDto logDto = LogDto.builder()
                .userId(user.getId())
                .action(pjp.getSourceLocation().getClass() + " " + pjp.getSignature().getName())
                .actionId(actionId)
                .status(actionStatus)
                .datetime(LocalDateTime.now()).build();
        logService.create(logDto);
    }

}
