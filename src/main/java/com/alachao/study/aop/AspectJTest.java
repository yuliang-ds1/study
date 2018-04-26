package com.alachao.study.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Author yuliang-ds1
 * @Date 9:50  2018/4/26.
 * @Desciption
 */
@Aspect
public class AspectJTest {

    @Pointcut("execution(* *.test(..))")
    public void test(){

    }

    @Before("test()")
    public void beforeTest(){
        System.out.println("beforeTest");
    }

    @After("test()")
    public void afterTest(){
        System.out.println("afterTest");

    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint p){
        System.out.println("环绕开始");
        Object o=null;
        try{
            o=p.proceed();
        }catch (Throwable t){
            t.printStackTrace();

        }
        System.out.println("环绕结束");
        return o;
    }

}
