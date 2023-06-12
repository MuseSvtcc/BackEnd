package com.douk.muses.mapper;

import com.douk.muses.pojo.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author douk
 * @since 2023-06-04
 */
public interface TUserMapper extends BaseMapper<TUser> {

    int setAttentionCountAdd(@Param("uId") Integer uId);
}
