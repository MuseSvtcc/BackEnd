package com.douk.muses.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.douk.muses.pojo.TPartitions;
import com.douk.muses.mapper.TPartitionsMapper;
import com.douk.muses.service.TPartitionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分区表 服务实现类
 * </p>
 *
 * @author douk
 * @since 2023-07-01
 */
@Service
public class TPartitionsServiceImpl extends ServiceImpl<TPartitionsMapper, TPartitions> implements TPartitionsService {

    @Override
    public List<TPartitions> getList() {
        return baseMapper.selectList(null);
    }
}
