package plus.feifei.feiblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.feifei.feiblog.entity.Blog;
import plus.feifei.feiblog.entity.BlogTags;
import plus.feifei.feiblog.entity.Tag;
import plus.feifei.feiblog.mapper.BlogMapper;
import plus.feifei.feiblog.service.BlogService;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author feifei
 * @since 2020-10-22
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    BlogMapper blogMapper;
    @Override
    public Page<Blog> selectBlogsNotContent(Page<Blog> blogPage) {
        // 由于自带的分页插件，数据不准确，这里手动分页
        long size = blogPage.getSize();
        long current = blogPage.getCurrent();

        Integer total = blogMapper.selectCount(null);
        // 获取总页码数
        long pages=(total+size-1)/size;
        // 如果页码超过总页码，将跳到最后一页
        if(current>pages){
            current=pages;
        }
        // 计算limit的开始
        long start= (current-1)*size;
        List<Blog> page = blogMapper.selectBlogsNotContent(start,size);
        blogPage.setRecords(page);
        blogPage.setCurrent(current);
        blogPage.setPages(pages);
        blogPage.setTotal(total);
        return blogPage;
    }

    /**
     * 附带条件联合查询
     * @param queryWrapper
     * @return
     */
    @Override
    public List<Blog> selectCondition(QueryWrapper<Blog> queryWrapper) {

        return blogMapper.selectCondition(queryWrapper);
    }

    @Override
    public List<String> selectBlogCreateYearsAll() {
        return blogMapper.selectBlogCreateYearsAll();
    }

    @Override
    public void saveBlogTag(List<Tag> tags) {

        BlogTags blogTags = new BlogTags();

    }

    @Override
    public void logicDeleteById(Long blogId) {
        blogMapper.logicDeleteById(blogId);
    }

    @Override
    public void selectBlogsByUserName(Page<Blog> blogPage, long id) {
        // 由于自带的分页插件，数据不准确，这里手动分页
        long size = blogPage.getSize();
        long current = blogPage.getCurrent();

        Integer total = blogMapper.selectCount(null);
        // 获取总页码数
        long pages=(total+size-1)/size;
        // 如果页码超过总页码，将跳到最后一页
        if(current>pages){
            current=pages;
        }
        // 计算limit的开始
        long start= (current-1)*size;
        List<Blog> page = blogMapper.selectBlogsByUserName(id,start,size);
        blogPage.setRecords(page);
        blogPage.setCurrent(current);
        blogPage.setPages(pages);
        blogPage.setTotal(total);
    }


}
