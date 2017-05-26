package com.senvon.sample.serviceTest;

import java.math.BigDecimal;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.istock.base.ibatis.model.Page;
import com.senvon.sample.dao.OrderApplyDAO;
import com.senvon.sample.dao.OrderInfoDAO;
import com.senvon.sample.model.OrderApplyExample;
import com.senvon.sample.model.OrderInfo;
import com.senvon.sample.model.OrderInfoExample;
import com.senvon.sample.service.BankServiceImpl2;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-test.xml"})
public class BankServiceImpl3Test {

	@Autowired
	private BankServiceImpl2 service;
	@Autowired
	private OrderInfoDAO orderInfoDao;
	@Autowired
	private OrderApplyDAO orderApplyDAO;
	
	private String senvonId = "$senvonIDDDDD$";
	@Before
	public void init() throws Exception{
		OrderInfo order = new OrderInfo();
		order.setAmount(BigDecimal.TEN);
		order.setName("senvon");
		order.setStatus(1);
		order.setId(senvonId);
		orderInfoDao.insertSelective(order);
	}
	
	@After
	public void after() throws Exception{
		orderInfoDao.deleteByPrimaryKey(senvonId);
		
		
		OrderApplyExample example = new OrderApplyExample();
		example.createCriteria().andOrderIdEqualTo(senvonId);
		orderApplyDAO.deleteByExample(example);
	}
	
	@Test
	public void test1() throws Exception{
		OrderInfo order = orderInfoDao.selectByPrimaryKey(senvonId);
		service.tradeOut(order);
		
		OrderInfo order11 = orderInfoDao.selectByPrimaryKey(senvonId);
		Assert.assertTrue(order11 != null);
		Assert.assertTrue(order11.getStatus() == 2);
	}
}
