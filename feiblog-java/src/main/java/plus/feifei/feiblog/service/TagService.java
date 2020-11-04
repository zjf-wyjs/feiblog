package plus.feifei.feiblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import plus.feifei.feiblog.entity.Tag;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author feifei
 * @since 2020-10-22
 */
public interface TagService extends IService<Tag> {


    List<Tag> selectAll();

    int deleteById(Integer tagId);
}
