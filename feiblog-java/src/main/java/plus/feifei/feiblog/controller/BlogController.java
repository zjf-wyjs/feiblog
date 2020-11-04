package plus.feifei.feiblog.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plus.feifei.feiblog.common.lang.ResultMsg;
import plus.feifei.feiblog.entity.Blog;
import plus.feifei.feiblog.entity.BlogTags;
import plus.feifei.feiblog.service.BlogService;
import plus.feifei.feiblog.service.BlogTagsService;
import plus.feifei.feiblog.util.ShiroUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author feifei
 * @since 2020-10-22
 */
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogTagsService blogTagsService;

    /**
     * 查询博客列表
     */
    @GetMapping("/blogs")
    public ResultMsg blogs(@RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                           @RequestParam(name="pageSize",defaultValue = "5")Integer pageSize,
                           HttpServletRequest request) {
        Page<Blog> blogPage = new Page<>(currentPage, pageSize);
//        Page<Blog> page = blogService.page(blogPage, new QueryWrapper<Blog>().orderByDesc("create_time"));
        Page<Blog> blogPage1 = blogService.selectBlogsNotContent(blogPage);
//        Page<Blog> page = blogService.page(blogPage);
        return ResultMsg.success().setData(blogPage1);
    }
    /**
     * 查询管理界面的博客列表
     */
    @GetMapping("/admin/blogList")
    public ResultMsg adminBlogs(@RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
                                @RequestParam(name="pageSize",defaultValue = "5")Integer pageSize){
        Long id = ShiroUtil.getProfile().getId();
        Page<Blog> blogPage = new Page<>(currentPage, pageSize);
        blogService.selectBlogsByUserName(blogPage,id);
        return ResultMsg.success().setData(blogPage);
    }

    /**
     * 根据分类或者标签的id查询
     */
    @GetMapping("/blogs/condition")
    public ResultMsg condition(Integer typeId,
                               Integer tagId,
                               Integer blogId,
                               String years) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("tb.create_time","tb.description","tb.first_picture","tb.title","tt.`type_id`","tb.user_id","tt.`type_name`","tag.`tag_name`","tu.`avatar`");
        if (typeId != null) {
            queryWrapper.eq("tb.type_id", typeId);
        }
        if (tagId != null) {
            queryWrapper.eq("tbt.tags_id", tagId);
        }
        if (blogId != null) {
            queryWrapper.eq("tb.id", blogId);
        }
        if (years != null) {
            queryWrapper.eq("DATE_FORMAT(create_time,'%Y年%m月')", years);
        }
//        queryWrapper.select("content");
        System.out.println(queryWrapper.getCustomSqlSegment());
        System.out.println(queryWrapper.getSqlSegment());
        List<Blog> blogs = blogService.selectCondition(queryWrapper);
        return ResultMsg.success().setData(blogs);
    }

    /**
     * 查询出所有发布年份
     */
    @GetMapping("/blogs/sort/years")
    public ResultMsg years() {
        List<String> years = blogService.selectBlogCreateYearsAll();
        return ResultMsg.success().setData(years);
    }

    /**
     * 添加或者修改一篇博客
     */
    @RequiresRoles(value = {"admin"})
    @PostMapping("/admin/blog/edit")
    public ResultMsg edit(@Validated @RequestBody Blog blog) {
        System.out.println(blog);
        Blog temp = null;
        // 如果blog的id为空，说明是添加一篇博客
        Assert.notEmpty(blog.getTags(),"标签不能为空");
        Assert.notNull(blog.getTypeId(),"分类不能为空");
        Assert.notNull(blog.getTitle(),"标题不能为空");
        if (blog.getId() == null) {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreateTime(LocalDateTime.now());
            temp.setStatus(0);
        } else {
            // 编辑状态
            temp=blogService.getById(blog.getId());
            // 删除原来的标签
            blogTagsService.remove(new QueryWrapper<BlogTags>().eq("blogs_id",blog.getId()));
            // 只能编辑自己的文章
            Assert.isTrue(temp.getUserId().longValue()==ShiroUtil.getProfile().getId().longValue(),"没有权限编辑");
        }

        BeanUtils.copyProperties(blog,temp,"id","userId","createTime","status");
        blogService.saveOrUpdate(temp);
        // 保存标签
        blogTagsService.saveBlogTags(temp);
        return ResultMsg.success().setMsg("保存成功");
    }
    @RequiresRoles(value = {"admin"})
    @DeleteMapping("/admin/blog/delete/{blogId}")
    public ResultMsg delete(@PathVariable("blogId")Long blogId){
        blogService.logicDeleteById(blogId);

        return ResultMsg.success();
    }

}
