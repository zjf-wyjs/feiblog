package plus.feifei.feiblog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import plus.feifei.feiblog.common.dto.LoginDto;
import plus.feifei.feiblog.common.lang.ResultMsg;
import plus.feifei.feiblog.entity.User;
import plus.feifei.feiblog.service.UserService;
import plus.feifei.feiblog.util.JwtUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class AccountHandler {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping("/doLogin")
    public ResultMsg doLogin(@Validated @RequestBody LoginDto loginDto,
                             HttpServletResponse response){
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user,"用户名不存在");
        if(!user.getPassword().equals(loginDto.getPassword())){
            return ResultMsg.fail().setMsg("密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-expose-Headers", "Authorization");
        Map<Object, Object> map = MapUtil.builder().put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .put("nickName",user.getNickname())
                .put("authId",user.getAuthId()).map();
        return ResultMsg.success().setData(map);
    }
}
