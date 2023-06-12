package com.douk.muses.mapper;

import com.douk.muses.pojo.TComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.douk.muses.pojo.or.InvitationComment;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author douk
 * @since 2023-06-01
 */
public interface TCommentMapper extends BaseMapper<TComment> {

    List<InvitationComment> getCommentList(@Param("userId") Integer userId, @Param("iId") String iId, @Param("number") String number, @Param("size") String size);

    List<InvitationComment> getCommentSonList(@Param("commentsId")Integer commentsId, @Param("number") String number,@Param("size") String size);

    Integer getIsGood(@Param("userId") Integer userId, @Param("commentsId") Integer commentsId);
}
