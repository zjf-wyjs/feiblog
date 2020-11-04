package plus.feifei.feiblog.service;

import plus.feifei.feiblog.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author feifei
 * @since 2020-10-22
 */
public interface CommentService extends IService<Comment> {

    List<Comment> selectByBlogId(Long blogId);
}
