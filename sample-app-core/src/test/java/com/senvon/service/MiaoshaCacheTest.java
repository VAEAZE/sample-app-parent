package com.senvon.service;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.senvon.sample.service.MiaoshaCacheService;
import com.whalin.MemCached.MemCachedClient;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-cache.xml"})
public class MiaoshaCacheTest {

	@Autowired
	private MiaoshaCacheService service;
	@Autowired
	private MemCachedClient client;
	
	private String goodCode = "senvon";
	private Integer id = 0;
	private Long productAmount = 100L;
	@Before
	public void init() throws Exception{
		client.setPrimitiveAsString(true);
		
		client.set(goodCode+"_value", 0L, DateUtils.addSeconds(new Date(), 20));
		client.set(goodCode, productAmount , DateUtils.addSeconds(new Date(), 20));
	}
	
	@After
	public void after() throws Exception{
		
		System.out.println(client.get(goodCode)+"======剩下======");
		
		client.delete(goodCode);
		client.delete(goodCode+"_value");
	}
	
	@Test
	public void test1 () throws Exception{
		for(int i = 0;i<100 ; i++){
			Thread t = new Thread(new ExecuteThread(i % 20==0 ? 1000L:3));
			t.start();
			latch.countDown();
		}
		
		Thread.currentThread().sleep(3000);
		System.out.println(total+"==========已销售============");
		System.out.println(totalCount+"==========人数============");
		
		for(int i = 0;i<100 ; i++){
			Thread t = new Thread(new ExecuteThread(i % 20==0 ? 1000L:3));
			t.start();
			latch.countDown();
		}
		
		Thread.currentThread().sleep(3000);
		System.out.println(total+"==========已销售============");
		System.out.println(totalCount+"========人数==============");
		
		for(int i = 0;i<100 ; i++){
			Thread t = new Thread(new ExecuteThread(i % 20==0 ? 1000L:3));
			t.start();
			latch.countDown();
		}
		
		Thread.currentThread().sleep(3000);
		System.out.println(total+"===========已销售===========");
		System.out.println(totalCount+"==========人数============");
		
		for(int i = 0;i<100 ; i++){
			Thread t = new Thread(new ExecuteThread(i % 20==0 ? 1000L:3));
			t.start();
			latch.countDown();
		}
		
		Thread.currentThread().sleep(3000);
		System.out.println(total+"==========已销售============");
		System.out.println(totalCount+"==========人数============");
		
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
			
			boolean result = service.modifyAmount(goodCode, amount , productAmount);
			if(result){
				synchronized (total) {
					total = total + amount;
					totalCount += 1;
				}
			}
		}
	}
}
