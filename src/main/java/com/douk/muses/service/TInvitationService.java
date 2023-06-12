package com.douk.muses.service;

import com.douk.muses.pojo.TInvitation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.douk.muses.pojo.or.UserInvitation;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 帖子 服务类
 * </p>
 *
 * @author douk
 * @since 2023-06-02
 */
public interface TInvitationService extends IService<TInvitation> {

    List<TInvitation> getIIT(int i);

    List<UserInvitation> getUserInvitation(int x,int y);

    /**
     * 设置7天的最热内容
     * @param x
     * @param y
     * @return
     */
    List<UserInvitation> getHotUserInvitation(int x, int y);

    /**
     * 帖子内容+判断是否关注+是否点赞+是否收藏
     * @param userId
     * @return
     */
    Map<String, Object> getContent(Integer userId, Integer invitation);

    Map<String, Object> insertUserInvitation(String i, Integer invitation);

    int insertByUid(Integer userId, Map<String, String> map);
}