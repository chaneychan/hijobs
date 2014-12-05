package com.dream.hijobs.service.memcached;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chaney.chan
 * 2014年9月3日
 */
public class MemcachedBase {

    private static final Logger logger = LoggerFactory.getLogger(MemcachedBase.class);
	
	/**
	 * 缓存时效 1天
	 */
	public static final int CACHE_EXP_DAY = 3600 * 24;

	/**
	 * 缓存时效 1周
	 */
	public static final int CACHE_EXP_WEEK = 3600 * 24 * 7;

	/**
	 * 缓存时效 1月
	 */
	public static final int CACHE_EXP_MONTH = 3600 * 24 * 30;

	/**
	 * 缓存时效 永久,
	 * <li>不要使用!!!
	 * <li>LRU驱逐不是全局的LRU，而是针对KEY-VALUE“合身的”SLAB的LRU
	 * <li>KEY的永远不过期，会加剧热点SLAB分布太散
	 */
	public static final int CACHE_EXP_FOREVER = 0;

	/**
	 * 冲突延时 1秒
	 */
	public static final int MUTEX_EXP = 1;
	
	/**
	 * 冲突键
	 */
	public static final String MUTEX_KEY_PREFIX = "MUTEX_";
	
	
	/**
	 * Memcached Client
	 */
	@Autowired
	private MemcachedClient memcachedClient;

	protected void deleteObject(String key) {
		try {
			memcachedClient.deleteWithNoReply(key);
		} catch (InterruptedException | MemcachedException e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("delete Object key: [" + key + "]");
	}
    
	protected void cacheObject(String key, Object value, int exp) {
		try {
			memcachedClient.set(key, exp, value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("Cache Object key: [" + key + "]");
	}

	/**
	 * Shut down the Memcached Cilent.
	 */
	protected void finalize() {
		if (memcachedClient != null) {
			try {
				if (!memcachedClient.isShutdown()) {
					memcachedClient.shutdown();
					logger.debug("Shutdown MemcachedManager...");
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	protected void flushObject(String key) {
		try {
			memcachedClient.deleteWithNoReply(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("Flush Object key: [" + key + "]");
	}

	/**
	 * 冲突判定
	 * @param key
	 */
	protected boolean isMutex(String key) {
		return isMutex(key, MUTEX_EXP);
	}

	/**
	 * 冲突判定
	 * @param key
	 * @param exp
	 * @return true 冲突
	 */
	protected boolean isMutex(String key, int exp) {
		boolean status = true;
		try {
			if (memcachedClient.add(MUTEX_KEY_PREFIX + key, exp, "true")) {
				status = false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return status;
	}

	protected <T> T loadObject(String key) {
		T object = null;
		try {
			object = memcachedClient.<T> get(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("Load Object key: [" + key + "]");
		return object;
	}

}