import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.InstantMessageApplication;

/**
 * Description:
 *
 * @author zhongjunjie
 * @Date: 2021/10/5
 */
@SpringBootTest(classes = InstantMessageApplication.class)
@RunWith(SpringRunner.class)
public class Test1 {

    @Value("${injiangli.im.server.port:6668}")
    private Integer serverPort;

    @Test
    public void test(){
        System.out.println(serverPort);
    }

}
