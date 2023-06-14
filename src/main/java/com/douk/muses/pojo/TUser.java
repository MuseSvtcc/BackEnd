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
 * 用户表
 * </p>
 *
 * @author douk
 * @since 2023-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "u_id", type = IdType.AUTO)
    private Integer uId;

    /**
     * 账号
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String portrait;

    /**
     * 个人简介/描述
     */
    private String describeText;

    /**
     * 性别
     */
    private Integer sex;

    private Integer vip;

    /**
     * 浏览记录
     */
    private String history;

    /**
     * 金币总数
     */
    private Integer goldCount;

    /**
     * 离线时间
     */
    private Date offlineTime;

    /**
     * 收藏夹
     */
    private String favorite;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    @TableLogic
    private Integer deleted;

    /**
     * 电话
     */
    private String phone;

    /**
     * 爱好
     */
    private String hobby;

    /**
     *关注人数
     */
    private Integer attentionCount;


}
