package com.mall.admin.base.component;

import com.mall.common.base.response.CodeMsg;
import com.mall.common.base.response.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


/**
 * program: chengjie-ts->BindingResultAspect
 * description: HibernateValidator错误结果处理切面
 * author: gerry
 * created: 2019-08-06 10:50
 * <p>
 * 【SpringBoot】SpingBoot整合AOP
 * https://blog.csdn.net/lmb55/article/details/82470388
 **/
@Aspect
@Component
@Order(2) // 加载顺序
public class BindingResultAspect {

    /**
     * 日志记录
     */
    private Logger logger = LoggerFactory.getLogger(BindingResultAspect.class);

    /**
     * 定义切入点，切入点为com.example.aop下的所有函数
     */
    @Pointcut("execution(public * com.mall.admin.*.controller.*.*(..))")
    public void BindingResult() {
    }

//    @Before("BindingResult()")
//    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//        // 记录下请求内容
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
//    }

    /**
     * https://blog.csdn.net/lichuangcsdn/article/details/87741811
     * Around是可以同时在所拦截方法的前后执行一段逻辑
     * 比如我们想在执行controller中方法前打印出请求参数，并在方法执行结束后来打印出响应值，这个时候，
     * 我们就可以借助于@Around注解来实现；
     * 再比如我们想在执行方法时动态修改参数值等
     * 类似功能的注解还有@Before等等，用到了Spring AOP切面思想，Spring AOP常用于拦截器、事务、日志、权限验证等方面。
     *
     *
     * 如果我们还想利用其进行参数的修改，则调用时必须用joinPoint.proceed(Object[] args)方法，
     * 将修改后的参数进行回传。如果用joinPoint.proceed()方法，则修改后的参数并不会真正被使用。
     *
     * @param joinPoint 连接点（Joinpoint）：在程序执行过程中某个特定的点，比如某方法调用的时候或者处理异常的时候。在Spring AOP中，一个连接点总是表示一个方法的执行。
     *                  SpringBoot配置Aop笔记【例子】 https://www.cnblogs.com/zhaww/p/8674657.html
     * @return
     * @throws Throwable
     */
    @Around("BindingResult()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    FieldError fieldError = result.getFieldError();
                    if (fieldError != null) {
                        return Result.error(new CodeMsg(401,fieldError.getDefaultMessage()));
                    } else {
                        return Result.error(CodeMsg.UNAUTHORIZED);
                    }
                }
            }
        }
        logger.info("请求参数为{}",args);
        // 动态修改其参数
        // 注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
        Object result =  joinPoint.proceed();
        logger.info("响应结果为{}",result);
        // 如果这里不返回result，则目标对象实际返回值会被置为null
        return result;
    }


}
