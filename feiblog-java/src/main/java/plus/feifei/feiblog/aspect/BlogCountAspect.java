package plus.feifei.feiblog.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import plus.feifei.feiblog.util.RedisUtil;

/**
 * 统计文章访问次数
 */
@Aspect
@Component
@Slf4j
@Order(2)
public class BlogCountAspect {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 对blogs请求进行统计
     */
    @Pointcut("execution(* plus.feifei.feiblog.controller.BlogController.condition(..))")
    public void count() {
    }

    @Before("count()")
    public void countBefore(JoinPoint joinPoint) {
        System.out.println("进来了");
        Object[] args = joinPoint.getArgs();
        if (!(args != null && args.length > 2 && args[2] != null)) {
            return;
        }

        // 格式：    key:  blog:1   value: 浏览次数
        // 请求一次加一
        redisUtil.incrBy("blog:"+String.valueOf(args[2]),1);
    }

    /**
     * 配置后置通知，在查询文章返回之前，将浏览次数从缓冲中查询出来
     */
//    @AfterReturning(value = "count()", returning = "resultMsg")
//    public void setBlogCount(JoinPoint joinPoint, ResultMsg resultMsg) {
//        Object data = resultMsg.getData();
//        List<Blog> list = null;
//        if (data instanceof List) {
//            list = (List<Blog>) data;
//        }
//        if (list == null) {
//            return;
//        }
//        Blog blog = list.get(0);
//        String s = redisUtil.get("blog:" + blog.getId());
//        if(s==null){
//            s="0";
//        }
//        blog.setCount(s);
//
//
//    }
}
