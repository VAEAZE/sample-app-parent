package com.senvon.service;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.senvon.sample.dao.GoodsInfoDAO;
import com.senvon.sample.model.GoodsInfo;
import com.senvon.sample.service.MiaoshaDBService;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-test.xml"})
public class MiaoshaTest {

	@Autowired
	private MiaoshaDBService service;
	@Autowired
	private GoodsInfoDAO dao;
	
	private String goodCode = "senvon";
	private Integer id = 0;
	@Before
	public void init() throws Exception{
		GoodsInfo info = new GoodsInfo();
		info.setAmount(100L);
		info.setGoodCode(goodCode);
		info.setSingleAmount(100L);
		id = dao.insert(info);
	}
	
	@After
	public void after() throws Exception{
		dao.deleteByPrimaryKey(id);
	}
	
	@Test
	public void test1 () throws Exception{
		for(int i = 0;i<100 ; i++){
			Thread t = new Thread(new ExecuteThread(i % 9==0 ? 1000L:3));
			t.start();
			latch.countDown();
		}
		
		Thread.currentThread().sleep(3000);
		System.out.println(total+"======================");
		System.out.println(totalCount+"======================");
		
		/*boolean result = service.modifyAmount(goodCode, 3L);
		System.out.println(result+"======================");*/
	}
	
	private CountDownLatch latch = new CountDownLatch(100);
	//总共卖出去多少
	private Long total = 0L;
	private Integer totalCount = 0;
	
	private class ExecuteThread implements Runnable {
		private Long amount;
		public ExecuteThread(Long amount){
			this.amount = amount;
		}
		public void run() {
			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			boolean result = service.modifyAmount(goodCode, amount);
			if(result){
				synchronized (total) {
					total = total + amount;
					totalCount += 1;
				}
			}
		}
	}
}
