package plus.feifei.feiblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import plus.feifei.feiblog.entity.Type;
import plus.feifei.feiblog.mapper.TypeMapper;
import plus.feifei.feiblog.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author feifei
 * @since 2020-10-22
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public List<Type> selectAll() {
        return typeMapper.selectAll();
    }

    @Override
    public int deleteById(Integer typeId) {
        return typeMapper.deleteById(typeId);
    }
}
