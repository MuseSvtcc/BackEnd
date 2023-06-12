package com.douk.muses.service;

import com.douk.muses.pojo.TAttention;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 关注表 服务类
 * </p>
 *
 * @author douk
 * @since 2023-06-01
 */
public interface TAttentionService extends IService<TAttention> {


    int setOne(TAttention tAttention);

    /**
     * 关注
     * @param userId
     * @param id
     * @return
     */
    int insertAttention(Integer userId, Integer id);
}
