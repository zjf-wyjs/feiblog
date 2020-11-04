package plus.feifei.feiblog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.feifei.feiblog.entity.Blog;
import plus.feifei.feiblog.mapper.BlogMapper;

import java.util.List;

@SpringBootTest
class FeiblogApplicationTests {

    @Autowired
    BlogMapper blogMapper;
    @Test
    void contextLoads() {
        List<Blog> blogs = blogMapper.selectList(null);
        System.out.println(blogs);
    }

}
