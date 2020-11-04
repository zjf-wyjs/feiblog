package plus.feifei.feiblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.feifei.feiblog.entity.Auth;
import plus.feifei.feiblog.mapper.AuthMapper;
import plus.feifei.feiblog.service.AuthService;

@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements AuthService {
    @Autowired
    AuthMapper authMapper;
    @Override
    public Auth selectById(Integer authId) {
        return authMapper.selectById(authId);
    }
}
