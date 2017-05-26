package com.senvon.bank;

import java.math.BigDecimal;

import com.senvon.bank.model.BankResponse;
import com.senvon.bank.model.OrderVO;

public class BankServiceMock implements BankService {

	@Override
	public BankResponse moneyOut(OrderVO orderInfo) {
		BankResponse response = new BankResponse();
		if(orderInfo.getAmount().compareTo(new BigDecimal("10") )==0)
		{
			response.setCode("S");
			response.setMessage("成功");
			return response;
		}else{
			response.setCode("F");
			response.setMessage("失败");
			return response;
		}
	}

}
