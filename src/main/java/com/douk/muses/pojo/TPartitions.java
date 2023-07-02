package com.douk.muses.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 分区表
 * </p>
 *
 * @author douk
 * @since 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TPartitions implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分区名
     */
    private String partitions;


}
