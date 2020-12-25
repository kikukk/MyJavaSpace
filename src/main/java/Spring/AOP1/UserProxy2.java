package Spring.AOP1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author kikukk
 */

@Component
@Aspect
@Order(1)
public class UserProxy2 {
    //相同切入点抽取
    @Pointcut(value = "execution(* Spring.AOP1.User.add(..))")
    public void pointdemo() {}

    //前置通知
    //@Before注解表示作为前置通知
    @Before(value = "pointdemo()")//相同切入点抽取使用！
    public void before() {
        System.out.println("before2.........");
    }


}
