package com.douk.muses.pojo.or;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class InvitationContent {
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
     * 标题
     */
    private String title;

    private String iOrM;

    /**
     * 内容
     */
    private String content;
    /**
     * 价格
     */
    private Integer price;

    private Date createdAt;
    /**
     * 查看
     */
    private int look;
    /**
     * 是否关注
     */
    private int isAttention;
    /**
     * 是否点赞
     */
    private int isGood;
    /**
     * 是否踩
     */
    private int isNoGood;
    /**
     * 是否收藏
     */
    private int isCollect;
    /**
     * 是否购买
     */
    private int isBuy;

}
