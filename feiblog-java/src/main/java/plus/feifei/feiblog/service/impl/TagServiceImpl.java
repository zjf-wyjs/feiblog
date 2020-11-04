package plus.feifei.feiblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plus.feifei.feiblog.entity.Tag;
import plus.feifei.feiblog.mapper.TagMapper;
import plus.feifei.feiblog.service.TagService;

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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> selectAll() {
        return tagMapper.selectAll();
    }

    @Override
    public int deleteById(Integer tagId) {
        return tagMapper.deleteById(tagId);
    }
}
