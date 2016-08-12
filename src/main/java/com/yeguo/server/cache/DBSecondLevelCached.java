package com.yeguo.server.cache;

import com.yeguo.server.base.redis.RedisOperator;
import com.yeguo.server.util.AppContextUtil;

public class DBSecondLevelCached {
    
	public static final String USER_VALIDATE_CODE= "user:id:%s";
	public static final String PHONE_CODE = "phone:num:%s";
	
	public static RedisOperator getRedisOperator() {
		return (RedisOperator) AppContextUtil.getBean("activityRedisOperator");
	}
}