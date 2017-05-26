package com.senvon.sample.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.istock.base.ibatis.IbatisDaoAnnotation;

@Repository
public class ExtGoodsInfoDAOImpl extends IbatisDaoAnnotation implements ExtGoodsInfoDAO {

	@Override
	public Integer modifyAmount(String code, Long amount) {
		Map<String , Object> paramMap = new HashMap<String , Object>();
		paramMap.put("code", code);
		paramMap.put("amount", amount);
		return this.getSqlMapClientTemplate().update("TB_GOODS_INFO.modifyAmount", paramMap);
	}

}
