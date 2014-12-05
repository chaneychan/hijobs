import org.junit.Test;

import com.dream.hijobs.util.AESCoder;

/**
 * @author chaney.chan
 * 2014年9月5日
 */
public class DESCoderTest {

	@Test
	public void test() throws Exception {
		String inputStr = "12121231212424-jfjdks-jfksjkfa-jksjkf";
		System.err.println("原文:\t" + inputStr);

		String encryptBASE64 = AESCoder.encrypt(inputStr);
		System.err.println("加密后:\t" + encryptBASE64);

	}
	
	@Test
	public void test1() throws Exception{

		String outputStr = AESCoder.decrypt("nt277nLN6TWmLFqXi9/+tU3Sc71HYdWpTwDXaLo1y0K+blvMut5nIYQK6G3GLeQo");

		System.err.println("解密后:\t" + outputStr);

	}
}
