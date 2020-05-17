package cn.melon.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Order(1)  // 值小先执行
@Aspect
@Component("loggerAspect")
public class LoggerAspect {

    @Pointcut("execution(public int cn.melon.aop.AopTestImpl.*(int,int))")
    public void joinPointExpression(){}


    @Before("joinPointExpression()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();  // 拿到方法名
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println(methodName + " 方法执行之前，方法参数" + args);
    }

    @After("joinPointExpression()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();  // 拿到方法名
        List<Object> args = Arrays.asList(joinPoint.getArgs());

        System.out.println(methodName + " 方法执行后，方法参数" + args);
    }

    @AfterReturning(value = "joinPointExpression()", returning = "result")
    public void afterReMethod(JoinPoint joinPoint, Object result) {
        System.out.println("方法执行结果" + (int)result);
    }

    @AfterThrowing(value = "joinPointExpression()", throwing = "e")
    public void throwMethod(JoinPoint joinPoint, Exception e) {
        System.out.println("异常：" + e.getMessage());
    }


    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {
        Object rs = null;
        String methodName = proceedingJoinPoint.getSignature().getName();

        try {
            // 前置通知
            rs = proceedingJoinPoint.proceed(); // 方法执行
            // 后置通知
        } catch (Throwable throwable){
            // 异常通知
            throw  new RuntimeException(throwable);
        }
        // 返回通知
        return rs;
    }

}
