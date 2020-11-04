package plus.feifei.feiblog.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import plus.feifei.feiblog.common.lang.ResultMsg;
import plus.feifei.feiblog.entity.About;
import plus.feifei.feiblog.mapper.AboutMapper;

@RestController
public class AboutController {
    @Autowired
    private AboutMapper aboutMapper;
    @GetMapping("/about/content")
    public ResultMsg about(){
        About about = aboutMapper.selectById(1);
        return ResultMsg.success().setData(about);
    }
    @RequiresRoles(value = {"admin"})
    @PostMapping("/about/edit")
    public ResultMsg edit(@RequestBody About about){
        about.setId(1);
        aboutMapper.updateById(about);
        return ResultMsg.success().setMsg("保存成功");
    }
}
