package plus.feifei.feiblog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import plus.feifei.feiblog.entity.Blog;
import plus.feifei.feiblog.entity.Tag;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author feifei
 * @since 2020-10-22
 */
public interface BlogService extends IService<Blog> {
    Page<Blog> selectBlogsNotContent(Page<Blog> blogPage);


    List<Blog> selectCondition(QueryWrapper<Blog> queryWrapper);

    List<String> selectBlogCreateYearsAll();

    void saveBlogTag(List<Tag> tags);

    void logicDeleteById(Long blogId);

    void selectBlogsByUserName(Page<Blog> blogPage, long id);
}
