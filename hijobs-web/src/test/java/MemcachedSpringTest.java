import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-*.xml"})
public class MemcachedSpringTest {

	@Autowired
	private MemcachedClient memcachedClient;

	@Test
	public void test() {
		try {
			// 设置/获取
			memcachedClient.set("zlex", 36000, "set/get");
			assertEquals("set/get", memcachedClient.get("zlex"));

			// 替换
			memcachedClient.replace("zlex", 36000, "replace");
			assertEquals("replace", memcachedClient.get("zlex"));

			// 移除
			memcachedClient.delete("zlex");
			assertNull(memcachedClient.get("zlex"));
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
