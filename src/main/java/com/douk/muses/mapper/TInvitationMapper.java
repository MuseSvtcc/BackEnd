package com.douk.muses.mapper;

import com.douk.muses.pojo.TInvitation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.douk.muses.pojo.or.UserInvitation;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 帖子 Mapper 接口
 * </p>
 *
 * @author douk
 * @since 2023-06-02
 */
@SuppressWarnings("ALL")
public interface TInvitationMapper extends BaseMapper<TInvitation> {

    List<UserInvitation> getUserInvitationList(@Param("x")int x,@Param("y")int y);

    List<UserInvitation> getHotUserInvitation(@Param("date") Date date,@Param("x") int x,@Param("y") int y);

    Map<String, Object> relation(@Param("userId") Integer userId,@Param("invitation") Integer invitation);

    Map<String, Object> insertUserInvitation(Integer valueOf, Integer invitationId);

    Map<String, Object> invitationContest(@Param("invitation") Integer invitationId);
}
