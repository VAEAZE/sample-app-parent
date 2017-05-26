package com.senvon.sample.service;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.schooner.MemCached.MemcachedItem;
import com.whalin.MemCached.MemCachedClient;

public class MiaoshaCacheService {

	@Autowired
	private MemCachedClient client;
	
	/**cache方式实现锁库存
	 * 
	 * decr有线程保证
	 * 但是,一旦失败,不知道之前是啥值.
	 * 
	 * 没有事务
	 * 
	 * 
	 * @param code
	 * @param amount
	 * @return
	 */
	public boolean modifyAmount(String code , Long amount , Long total){
		//原有>amount = 正常
		//原有<amount = 0
		
		//1.100 - 1000 = 0
		//read 缓存-----------不行
		//
		Long result = client.decr(code, amount);
		if(result>0){
			client.incr(code+"_value", amount);//已经买了多少
			return true;
		}else{
			
			boolean updateResult = false;
			do{
				MemcachedItem item = client.gets(code);
				MemcachedItem item2 = client.gets(code+"_value");
				Long restValue = Long.parseLong((item2.getValue()+"").trim());
				//cas确保code在没有变更过的情况下,设置产品剩余量
				updateResult = client.cas(code, total - restValue, item.casUnique);
			}while(updateResult);
			
			/*Long totalAmount = Long.parseLong((client.get(code+"_value")+"").trim());
			if(totalAmount>total){
				
			}else{
				client.set(code, total - totalAmount , DateUtils.addSeconds(new Date(), 10));
			}*/
			return false;
		}
	}
}
