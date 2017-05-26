package com.senvon.sample.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.senvon.sample.dao.ExtGoodsInfoDAO;

@Repository
public class MiaoshaDBService {

	@Autowired
//	private GoodsInfoDAO dao;
	private ExtGoodsInfoDAO dao;
	
	
	/**秒杀DB实现
	 * 
	 * int i = update xxx set status = 4 where id = xxx and status = 1;
	 * 
	 * int i = update GOODS set AMOUNT = AMOUNT - #amount# 
	 * where CODE = #code# and AMOUNT-#amount# >=0;
	 * 
	 * 300/700
	 * 
	 * @param code
	 * @param amount
	 * @return 扣减产品库存是否成功
	 */
	public boolean modifyAmount(String code , Long amount){
		return dao.modifyAmount(code, amount).equals(1);
	}
}
