package com.douk.muses.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 帖子
 * </p>
 *
 * @author douk
 * @since 2023-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TInvitation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "invitation_id", type = IdType.AUTO)
    private Integer invitationId;

    private Integer uId;

    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 附件
     */
    private String attachment;

    /**
     * 点赞
     */
    private Integer good;

    /**
     * 踩
     */
    private Integer noGood;

    /**
     * 收藏
     */
    private Integer collect;

    /**
     * 转发
     */
    private Integer transmit;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 分区
     */
    private Integer partitionsId;

    /**
     * 置顶
     */
    private Integer uTop;

    /**
     * 私有
     */
    private Integer uPrivate;

    /**
     * 帖子标签
     */
    private String tag;
    /**
     * 查看
     */
    private int look;

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    private Date updatedSt;

    @TableLogic
    private Integer deleted;

    private String iOrM;

}
