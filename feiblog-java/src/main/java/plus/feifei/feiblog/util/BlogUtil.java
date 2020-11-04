package plus.feifei.feiblog.util;

import org.springframework.beans.factory.annotation.Autowired;

public class BlogUtil {
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 从redis获取访问次数
     */
    public static int getReadCount(int blogId){

        return 0;
    }
}
