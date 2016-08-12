package com.yeguo.server.base.redis;


import org.apache.log4j.Logger;

import com.yeguo.server.log.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisBuilder {
	
	private JedisPool pool = null;
	
	private String host;
	
	private int port;
	
	private int timeout;

	private boolean flag;
	
	private static Logger loggerError = LoggerFactory.getServerErrorLogger(JedisBuilder.class);
	
	public JedisBuilder(String host, int port, int timeout, int maxactive, int maxidle, boolean flag){
		this.host = host;
		this.port = port;
		this.timeout = timeout;	
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(maxactive);
		config.setMaxIdle(maxidle); 
		config.setTestOnBorrow(flag);
		config.setTimeBetweenEvictionRunsMillis(-1);
		pool = new JedisPool(config, host, port, timeout);
		this.flag = flag;
	}
	
	public void initialize() {
		pool = new JedisPool(new JedisPoolConfig(), host, port, timeout);
	}
	
	private final int maxTry = 3;
	public Jedis buildJedis(int db){
		return buildJedis(db, 1);
	}
	
	public Jedis buildJedis(int db, int tryNum){
		Jedis jedis = null;
		try{
			jedis = pool.getResource();	
			if (!jedis.isConnected()){
				jedis.connect();
			}
			if(flag){
				jedis.select(db);
			} else { //主动测试ping， 最多尝试maxTry次
				try {
					if (!jedis.ping().equals("PONG")){
						throw new Exception(" ping failure");
					}
				} catch (Exception e){
					loggerError.error("catch Exception in buildJedis when test jedis live ...ThreadId=" + Thread.currentThread().getId() + ", redishost=" + host + ", port=" + port, e);
					returnBrokenResource(jedis);
					
					if (tryNum < maxTry){
						tryNum +=1;
						loggerError.error("catch Exception retry buildJedis.......time= " + tryNum + " ...ThreadId=" + Thread.currentThread().getId() + ", redishost=" + host + ", port=" + port);
						return buildJedis(db, tryNum);
					} else
						return null;
				}	
			}
		}catch(Exception e){
			loggerError.error("catch Exception in buildJedis " + ", redishost=" + host + ", port=" + port, e);
			pool.returnBrokenResource(jedis);
			return null;
		}
		return jedis;
	}
	
	public void returnResource(Jedis jedis){
		pool.returnResource(jedis);
	}
	
	public void returnBrokenResource(Jedis jedis){
		pool.returnBrokenResource(jedis);
	}
	
	public void stop() {
		pool.destroy();
	}
}
