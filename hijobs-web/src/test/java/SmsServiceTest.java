import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dream.hijobs.service.dto.Result;
import com.dream.hijobs.service.sms.SmsService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class SmsServiceTest {

	@Autowired
	private SmsService service;
	
	@SuppressWarnings("rawtypes")
	@Test
	public void test(){
		Result result = service.sendRegCode("18657110312", 4);
		System.out.println(result);
	}
}
