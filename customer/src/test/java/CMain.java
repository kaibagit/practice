import com.luliru.practice.api.provider.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by kaiba on 2016/5/6.
 */
public class CMain {

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:customer.xml");
        UserService userService =ctx.getBean(UserService.class);
        userService.fingByIds(1L,2L);
        System.out.println(userService.hello("222"));
    }
}
