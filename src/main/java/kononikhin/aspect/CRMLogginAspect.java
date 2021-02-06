package kononikhin.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLogginAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* kononikhin.Controllers.*.*(..))")
    private void forControllerPackage() {

    }

    @Pointcut("execution(* kononikhin.DAO.*.*(..))")
    private void forDAOPackage() {

    }

    @Pointcut("execution(* kononikhin.Service.*.*(..))")
    private void forServicePackage() {

    }

    @Pointcut("forControllerPackage() || forDAOPackage() || forServicePackage()")
    private void forWorkFlow() {

    }

    @Pointcut("forWorkFlow()")
    public void before(JoinPoint joinPoint) {

        //display method calling
        String method = joinPoint.toShortString();
        logger.info(method);




    }

}
