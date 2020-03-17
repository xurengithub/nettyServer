import com.xuren.dao.entity.UserInfoEntity;
import com.xuren.dao.mapper.UserMapper;
import com.xuren.gameserver.ServerApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class Test {
    @Autowired
    private UserMapper userMapper;

    @org.junit.Test
    public void test1() {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId("100001");
        userInfoEntity.setNickName("xuren");
        userInfoEntity.setAccount("123");
        userInfoEntity.setPassword("123456");

        userMapper.saveOne(userInfoEntity);
    }
}
