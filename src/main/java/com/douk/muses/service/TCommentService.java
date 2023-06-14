package com.douk.muses.service;

import com.douk.muses.pojo.TComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.douk.muses.pojo.or.InvitationComment;

import java.util.List;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author douk
 * @since 2023-06-01
 */
public interface TCommentService extends IService<TComment> {

    List<InvitationComment> getCommentList(Integer userId, String iId, String number, String size, String znumber, String zsize);

    int inComment(TComment comment);
}
