package com.douk.muses.service;

import com.douk.muses.pojo.TPartitions;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 分区表 服务类
 * </p>
 *
 * @author douk
 * @since 2023-07-01
 */
public interface TPartitionsService extends IService<TPartitions> {

    List<TPartitions> getList();
}
