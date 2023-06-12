package com.douk.muses.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 关注表
 * </p>
 *
 * @author douk
 * @since 2023-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TAttention implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 粉丝用户 ID
     */
    private Integer attentionId;

    /**
     * 被关注用户 ID
     */
    private Integer uId;

    /**
     * 是否互相关注
     */
    private Integer isAttention;


}
