package plus.feifei.feiblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plus.feifei.feiblog.entity.Auth;

public interface AuthService extends IService<Auth> {
    Auth selectById(Integer authId);
}
