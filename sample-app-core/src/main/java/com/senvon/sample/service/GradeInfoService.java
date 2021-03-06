package com.senvon.sample.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.whalin.MemCached.MemCachedClient;

@Repository
public class GradeInfoService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MemCachedClient client;
	
	private Integer count = 0;
	
	public Integer getCount(){
		return count;
	}
	@Autowired
	private CacheTemplateService cacheTemplate;
	
	public Map<String ,Object> query1(){
		String key = "senvon";
		Date expire = DateUtils.addSeconds(new Date(), 3); 
		return cacheTemplate.findCache(key, expire, new TypeReference<Map<String ,Object>>(){}	, new LoadCallback<Map<String ,Object>>(){
			@Override
			public Map<String, Object> load() {
				//缓存没找到,假设查询数据库花费的时间 
				try {
					logger.info("========================no cache");
					++count;
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Map<String , Object> result = new HashMap<String , Object>();
				result.put("name", "senvon");
				result.put("age", 12);
				return result;
			}
			
		});
	}
	
	public /*synchronized*/ Map<String ,Object> query(){
		String key = "senvon";
		String json = client.get(key)+"";
		if(StringUtils.isBlank(json)||json.equalsIgnoreCase("null")){
			//所有的线程,只要是等待在这边,都会去访问数据库
			synchronized(this){
				json = client.get(key)+"";
				if(StringUtils.isBlank(json)||json.equalsIgnoreCase("null")){
					//缓存没找到,假设查询数据库花费的时间 
					//抽象begin
					try {
						logger.info("========================no cache");
						++count;
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Map<String , Object> result = new HashMap<String , Object>();
					result.put("name", "senvon");
					result.put("age", 12);
					//抽象end
					
					json = JSON.toJSONString(result);
					client.set(key, json,DateUtils.addSeconds(new Date(), 3));
					return result;
				}else{
					return JSON.parseObject(json);
				}
			}
		}else{
			return JSON.parseObject(json);
		}
	}
}
