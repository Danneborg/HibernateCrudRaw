package kononikhin.aspect;


import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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

    @Before("forWorkFlow()")
    public void before(JoinPoint joinPoint) {
        //display method calling
        String method = joinPoint.toShortString();
        logger.info(method);

        //getting arguments
        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args) {
            logger.info(tempArg.toString());
        }

    }

    @AfterReturning(pointcut = "forWorkFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        //display method calling
        String method = joinPoint.toShortString();
        logger.info("@After returning executin: " + method);

        //getting arguments
        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args) {
            logger.info(tempArg.toString());
        }
    }

}
