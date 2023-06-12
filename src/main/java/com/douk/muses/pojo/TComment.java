package com.douk.muses.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author douk
 * @since 2023-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "comments_id", type = IdType.AUTO)
    private Integer commentsId;

    /**
     * 帖子id
     */
    private Integer invitationId;

    private Integer uId;

    /**
     * 父id

     */
    private Integer fatherId;

    /**
     * 评论
     */
    private String commentText;

    private Integer good;

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    private Integer delectd;


}
