package plus.feifei.feiblog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import plus.feifei.feiblog.entity.Comment;
import plus.feifei.feiblog.mapper.CommentMapper;
import plus.feifei.feiblog.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    CommentMapper commentMapper;
    @Override
    public List<Comment> selectByBlogId(Long blogId) {
        return commentMapper.selectByBlogId(blogId);
    }
}
