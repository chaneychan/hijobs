import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dream.hijobs.dao.domain.User;
import com.dream.hijobs.service.dto.Result;
import com.dream.hijobs.service.enums.AccountType;
import com.dream.hijobs.service.sms.SmsService;
import com.dream.hijobs.service.token.TokenHelper;
import com.dream.hijobs.service.user.UserService;
import com.dream.hijobs.util.Coder;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SmsService smsService;
	
	@Test
	public void smsReg(){
		smsService.sendRegCode("18657110312", 6);
	}
	
	@Test
	public void reg() throws Exception{
		String code = "827215";
		User user = new User();
		user.setEmail("chaney.chan-company");
		user.setPwd(Coder.Md5Encode("chen_chen1314".getBytes()));
		user.setLatitude(12.13);
		user.setLongitude(35.65);
		user.setMobile("18657110312");
//		userService.register(user, code);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void load() throws Exception{
//		Result result = userService.load("chaney.chan-company", Coder.encryptBASE64(Coder.encryptMD5("chen_chen1314".getBytes())) ,"bc8b3c858a917116",12.12,34.56);
//		System.out.println(result);
	}
	
	@Test
	public void aload() throws Exception{
//		Result<String> result = userService.aload(new TokenHelper().parseUserToken("b1/M72/NAqQLCPRjaybxlozHXGRouz08zz1Pa5jdn8rrXXjV7APRTvVuOBlsxB+XXl4jXu7nVwpQdaZbQF+c1w=="), 23.43, 54.89);
//		System.out.println(result);
	}
}
