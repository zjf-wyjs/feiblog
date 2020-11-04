package plus.feifei.feiblog.controller;


import cn.hutool.core.lang.Assert;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plus.feifei.feiblog.common.exception.BlogException;
import plus.feifei.feiblog.common.lang.ResultMsg;
import plus.feifei.feiblog.entity.Tag;
import plus.feifei.feiblog.service.TagService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author feifei
 * @since 2020-10-22
 */
@RestController
public class TagController {

    @Autowired
    private TagService tagService;
    /**
     * 查询所有标签
     */

    @GetMapping("/tags")
    public ResultMsg tags(){
        List<Tag> list = tagService.selectAll();
        return ResultMsg.success().setData(list);
    }
    /**
     * 根据id删除标签
     */
    @RequiresRoles(value = {"admin"})
    @DeleteMapping("/tag/delete/{tagId}")
    public ResultMsg deleteTag(@PathVariable("tagId") Integer tagId){
        int count= 0;
        try {
            count = tagService.deleteById(tagId);
        } catch (Exception e) {
            throw new BlogException("删除失败");
        }
        if(count==0){
            return ResultMsg.fail().setMsg("删除失败");
        }
        return ResultMsg.success().setMsg("删除成功");
    }
    /**
     * 添加一条分类
     */
    @RequiresRoles(value = {"admin"})
    @PutMapping("/tag/add/{tagName}")
    public ResultMsg addTag(@PathVariable("tagName")String tagName){
        Tag tag = new Tag();
        tag.setTagName(tagName);
        try {
            boolean save = tagService.save(tag);
            Assert.isTrue(save,"添加失败1");
            return ResultMsg.success().setMsg("添加成功");
        }catch (Exception e){
            return ResultMsg.fail().setMsg("添加失败");
        }


    }
}
