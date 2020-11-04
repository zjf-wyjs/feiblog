package plus.feifei.feiblog.mapper;

import plus.feifei.feiblog.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author feifei
 * @since 2020-10-22
 */
public interface CommentMapper extends BaseMapper<Comment> {
    List<Comment> selectByBlogId(Long blogId);
}
