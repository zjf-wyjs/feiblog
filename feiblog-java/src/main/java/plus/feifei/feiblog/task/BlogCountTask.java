package plus.feifei.feiblog.task;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import plus.feifei.feiblog.entity.Blog;
import plus.feifei.feiblog.service.BlogService;
import plus.feifei.feiblog.util.RedisUtil;

import java.util.Set;

/**
 * 博客浏览次数定时保存
 */
@Configuration
@EnableScheduling
@Slf4j
public class BlogCountTask {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    BlogService blogService;
    @Scheduled(fixedDelay = 60000)
    public void saveBlogCount(){
        Set<String> keys = redisUtil.keys("blog:*");
        for(String key:keys){
            String blogId = key.split(":")[1];
            Blog blog = blogService.getById(blogId);
            if (blog == null) {
                return;
            }
            Long count = blog
                    .getCount();
            Long redisCount= Long.valueOf(redisUtil.get(key));
            if(!count.equals(redisCount)){

                boolean update = blogService.update(new UpdateWrapper<Blog>().eq("id", blogId).set("count", redisCount));
                log.info("更新阅读次数----blogId"+blogId+"----次数："+redisCount+"-------update:"+update);
            }
        }
    }
}
