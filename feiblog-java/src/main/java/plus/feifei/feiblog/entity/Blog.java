package plus.feifei.feiblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author feifei
 * @since 2020-10-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // 是否可评论
    private Boolean commentabled;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    // 描述
    private String description;

    // 首图
    private String firstPicture;

    // 标签
    private String flag;

    // 是否发布
    private Boolean published;

    // 是否上推荐
    private Boolean recommend;


    private String title;

    private LocalDateTime updateTime;

    private Integer views;

    private Long typeId;

    private Long userId;

    /**
     * 状态 0 代表正常，-1代表已删除
     */
    private Integer status;

    @TableField(exist = false)
    private Type type;

    @TableField(exist = false)
    private List<Tag> tags;

    /**
     * 浏览次数
     */
    private Long count;

    /**
     * 发布者
     */
    @TableField(exist = false)
    private User user;
}
