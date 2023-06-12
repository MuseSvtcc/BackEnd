package com.douk.muses.pojo.or;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class InvitationComment {
    private Integer commentsId;
    /**
     * id
     */
    private Integer uId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String portrait;

    /**
     * 评论
     */
    private String commentText;
    /**
     * 点赞
     */
    private Integer good;
    /**
     * 子评论条数
     */
    private Integer counts;
    /**
     * 子评论
     */
    private List<InvitationComment> comments;
    private Integer isGood;
}
