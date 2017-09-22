import com.luliru.springboot.Main
import com.luliru.springboot.beans.Dao
import com.luliru.springboot.beans.MyService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import javax.annotation.Resource

/**
 * Created by luliru on 2017/9/22.
 */

@ContextConfiguration
@SpringBootTest(classes = Main.class)
class SpringBootSpec extends Specification{

    @Resource
    MyService myService

    @Resource
    Dao dao

    def "第一个测试"(){
        expect:
        myService.hello() == "world"
    }

    def "dao测试"(){
        expect:
        dao.get() == null
    }

    def "dao测试2"(){
        when:
        dao.insert(new Object())

        then:
        dao.get() != null
    }
}
