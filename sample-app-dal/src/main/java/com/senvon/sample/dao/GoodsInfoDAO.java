package com.senvon.sample.dao;

import com.istock.base.ibatis.model.Page;
import com.senvon.sample.model.GoodsInfo;
import com.senvon.sample.model.GoodsInfoExample;
import java.util.List;

public interface GoodsInfoDAO {
    int countByExample(GoodsInfoExample example);

    int deleteByExample(GoodsInfoExample example);

    int deleteByPrimaryKey(Integer id);

    Integer insert(GoodsInfo record);

    Integer insertSelective(GoodsInfo record);

    List<GoodsInfo> selectByExample(GoodsInfoExample example);

    GoodsInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(GoodsInfo record, GoodsInfoExample example);

    int updateByExample(GoodsInfo record, GoodsInfoExample example);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    List<GoodsInfo> selectByPage(GoodsInfoExample example, Page page);
}