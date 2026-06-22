package com.selection.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.selection.entity.MarketDataSnapshot;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MarketDataSnapshotMapper extends BaseMapper<MarketDataSnapshot> {
}
