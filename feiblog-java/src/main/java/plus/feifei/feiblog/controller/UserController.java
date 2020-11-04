package plus.feifei.feiblog.controller;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import plus.feifei.feiblog.common.lang.ResultMsg;
import plus.feifei.feiblog.entity.User;
import plus.feifei.feiblog.qiniu.UploadFileUtil;
import plus.feifei.feiblog.service.UserService;
import plus.feifei.feiblog.util.ShiroUtil;

import java.time.LocalDateTime;
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
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UploadFileUtil uploadFileUtil;
    /**
     * 获取用户列表
     */
    @RequiresRoles(value = {"admin"})
    @GetMapping("/admin/userList")
    public ResultMsg userList(){
        List<User> userList = userService.list(new QueryWrapper<User>().ne("auth_id", 1));
        return ResultMsg.success().setData(userList);
    }
    /**
     * 获取当前登录的用户信息
     */
    @GetMapping("/admin/user")
    public ResultMsg user(){
        Long id = ShiroUtil.getProfile().getId();
        User user = userService.getById(id);
        Assert.notNull(user,"用户不能存在");
        return ResultMsg.success().setData(user);
    }
    /**
     * 修改用户信息
     */
    @PostMapping("/admin/updateUser")
    public ResultMsg userEditByUsername(@Validated @RequestBody User user){
        // 获取当前登录的用户
        Long id = ShiroUtil.getProfile().getId();
        user.setUsername(ShiroUtil.getProfile().getUsername());
        user.setUpdateTime(LocalDateTime.now());
        boolean id1 = userService.update(user, new UpdateWrapper<User>().eq("id", id));
        if(!id1){
            return ResultMsg.fail();
        }

        return ResultMsg.success();
    }
    /**
     * 注册用户
     */
    @PostMapping("/registerUser")
    public ResultMsg register(@Validated @RequestBody User user){
        User username = userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        Assert.isNull(username,"用户名已存在");
        user.setCreateTime(LocalDateTime.now());
        userService.save(user);
        return ResultMsg.success();
    }

    /**
     * 头像上传接口
     */
    @PostMapping("/user/avatar/up")
    public ResultMsg upload(@RequestParam("file") MultipartFile file){
        try {
            String fileName = uploadFileUtil.save(file);
            return ResultMsg.success().setMsg(fileName).setData(uploadFileUtil.getBucketUrl()+fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMsg.fail().setMsg("文件上传失败");
        }


    }

}
