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
 * 金币
 * </p>
 *
 * @author douk
 * @since 2023-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TGold implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "gold_id", type = IdType.AUTO)
    private Integer goldId;

    private Integer uId;

    /**
     * 权限
     */
    private String jurisdiction;

    /**
     * 操作
     */
    private String operation;

    private Integer count;

    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    @TableLogic
    private Integer deleted;


}
