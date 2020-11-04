package plus.feifei.feiblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plus.feifei.feiblog.entity.Blog;
import plus.feifei.feiblog.entity.BlogTags;

public interface BlogTagsService extends IService<BlogTags> {
    void saveBlogTags(Blog blog);
}
