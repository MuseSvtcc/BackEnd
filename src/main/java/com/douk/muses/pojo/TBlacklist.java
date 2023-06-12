package com.douk.muses.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 黑名单
 * </p>
 *
 * @author douk
 * @since 2023-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TBlacklist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uId;

    /**
     * 黑名单id
     */
    private Integer blacklistUid;

    /**
     * 黑名单原因
     */
    private String blacklistCause;

    private Date blacklistTime;


}
