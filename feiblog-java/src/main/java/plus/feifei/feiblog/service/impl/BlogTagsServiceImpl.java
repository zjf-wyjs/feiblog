package plus.feifei.feiblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import plus.feifei.feiblog.entity.Blog;
import plus.feifei.feiblog.entity.BlogTags;
import plus.feifei.feiblog.entity.Tag;
import plus.feifei.feiblog.mapper.BlogTagsMapper;
import plus.feifei.feiblog.service.BlogTagsService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogTagsServiceImpl extends ServiceImpl<BlogTagsMapper, BlogTags> implements BlogTagsService {

    @Override
    public void saveBlogTags(Blog blog) {
        List<BlogTags> bt=new ArrayList<>();
        for (Tag tag : blog.getTags()) {
            bt.add(new BlogTags(blog.getId(), tag.getTagId()));
        }
        System.out.println(bt);
        System.out.println(blog);
        this.saveBatch(bt);
    }
}
