package plus.feifei.feiblog.controller;


import cn.hutool.core.lang.Assert;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plus.feifei.feiblog.common.exception.BlogException;
import plus.feifei.feiblog.common.lang.ResultMsg;
import plus.feifei.feiblog.entity.Type;
import plus.feifei.feiblog.service.TypeService;

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
public class TypeController {
    @Autowired
    private TypeService typeService;

    /**
     * 获取所有分类
     */
    @GetMapping("/types")
    public ResultMsg types() {
        List<Type> list = typeService.selectAll();
        return ResultMsg.success().setData(list);
    }
    /**
     * 根据id删除分类
     */
    @RequiresRoles(value = {"admin"})
    @DeleteMapping("/type/delete/{typeId}")
    public ResultMsg deleteType(@PathVariable("typeId") Integer typeId){
        int count= 0;
        try {
            count = typeService.deleteById(typeId);
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
    @PutMapping("/type/add/{typeName}")
    public ResultMsg addType(@PathVariable("typeName")String typeName){
        Type type = new Type();
        type.setTypeName(typeName);
        try {
            boolean save = typeService.save(type);
            Assert.isTrue(save,"添加失败1");
            return ResultMsg.success().setMsg("添加成功");
        }catch (Exception e){
            return ResultMsg.fail().setMsg("添加失败");
        }


    }

}
