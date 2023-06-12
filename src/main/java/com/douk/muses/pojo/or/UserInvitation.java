package com.douk.muses.pojo.or;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserInvitation {
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
     * 粉丝人数
     */
    private int attentionCount;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 图片or视频
     */
    private String iOrM;

    /**
     * 赞
     */
    private int good;

    private int no_good;
    /**
     * 查看
     */
    private int look;

    private Date createdAt;

}
