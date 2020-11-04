package plus.feifei.feiblog.controller;


import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plus.feifei.feiblog.common.lang.ResultMsg;
import plus.feifei.feiblog.entity.Comment;
import plus.feifei.feiblog.service.CommentService;
import plus.feifei.feiblog.util.ShiroUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author feifei
 * @since 2020-10-22
 */
@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/comment/submit")
    @RequiresAuthentication
    public ResultMsg submitComment(@Validated @RequestBody Comment comment){
        // 获取当前用户
        Long userId = ShiroUtil.getProfile().getId();
        comment.setUserId(userId);
        comment.setCreateTime(LocalDateTime.now());
        System.out.println(comment);
        boolean save = commentService.save(comment);
        if(!save){
            return ResultMsg.fail().setMsg("评论失败");
        }

        return ResultMsg.success().setMsg("评论成功");
    }

    /**
     * 根据blogId查询评论列表
     */
    @GetMapping("/comment/list/{blogId}")
    public ResultMsg commentList(@PathVariable("blogId")Long blogId){
        List<Comment> comments=commentService.selectByBlogId(blogId);
        return ResultMsg.success().setData(comments);
    }
}
