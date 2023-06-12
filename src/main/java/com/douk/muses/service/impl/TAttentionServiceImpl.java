package com.douk.muses.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.douk.muses.pojo.TAttention;
import com.douk.muses.mapper.TAttentionMapper;
import com.douk.muses.service.TAttentionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.douk.muses.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 关注表 服务实现类
 * </p>
 *
 * @author douk
 * @since 2023-06-01
 */
@Service
public class TAttentionServiceImpl extends ServiceImpl<TAttentionMapper, TAttention> implements TAttentionService {
    @Autowired
    private TUserService tUserService;

    @Override
    public int setOne(TAttention tAttention) {
        Integer uId = tAttention.getUId();
        int insert = baseMapper.insert(tAttention);
        if(insert>0){
            int i=tUserService.setAttentionCountAdd(uId);
            if(i>0){
                return 1;
            }
        }

        return 0;
    }

    @Override
    public int insertAttention(Integer userId, Integer id) {
        TAttention attention=new TAttention();
        attention.setUId(id);
        attention.setAttentionId(userId);


        return baseMapper.insert(attention);
    }
}
