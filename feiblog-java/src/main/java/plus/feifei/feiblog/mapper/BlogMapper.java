package plus.feifei.feiblog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import plus.feifei.feiblog.entity.Blog;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author feifei
 * @since 2020-10-22
 */
public interface BlogMapper extends BaseMapper<Blog> {
    /**
     * 查询带有标签，分类的博客
     */
    List<Blog> selectBlogsNotContent(@Param("start") Long start, @Param("pageSize") Long pageSize);

    List<Blog> selectCondition(@Param(Constants.WRAPPER) QueryWrapper<Blog> queryWrapper);

    @Select("SELECT DATE_FORMAT(create_time,'%Y年%m月') years FROM t_blog GROUP BY (years) ORDER BY create_time DESC")
    List<String> selectBlogCreateYearsAll();

    @Select("UPDATE t_blog SET `status`=-1 WHERE id=#{blogId}")
    void logicDeleteById(Long blogId);

    List<Blog> selectBlogsByUserName(@Param("id")long id,@Param("start") Long start, @Param("pageSize") Long pageSize);
}
