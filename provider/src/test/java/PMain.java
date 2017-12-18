import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by kaiba on 2016/5/6.
 */
public class PMain {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:provider.xml");
        System.out.println("started");
//        UserService userService =ctx.getBean(UserService.class);
//        System.out.println(userService.hello("222"));
        while(true){
            Thread.sleep(100l);
        }
    }
}
