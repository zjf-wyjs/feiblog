package plus.feifei.feiblog.service.impl;

import plus.feifei.feiblog.entity.User;
import plus.feifei.feiblog.mapper.UserMapper;
import plus.feifei.feiblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author feifei
 * @since 2020-10-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
