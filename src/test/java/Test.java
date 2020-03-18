import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.xuren.common.utils.RedisOperator;
import com.xuren.dao.entity.UserInfoEntity;
import com.xuren.dao.mapper.UserMapper;
import com.xuren.gameserver.ServerApplication;
import com.xuren.gameserver.proto.ProtoMsg3;
import org.junit.runner.RunWith;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServerApplication.class)
public class Test {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Sid sid;
    @Autowired
    private RedisOperator redisOperator;
    @org.junit.Test
    public void test1() {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId(sid.nextShort());
        userInfoEntity.setNickName("xuren");
        userInfoEntity.setAccount("12345679");
        userInfoEntity.setPassword("123456");

        int num = userMapper.saveOne(userInfoEntity);
        System.out.println(num);

        List<UserInfoEntity> list = userMapper.queryAllUsers();
        for (UserInfoEntity l : list) {
            System.out.println(l.getId());
        }
    }

    @org.junit.Test
    public void testRedis() {
        redisOperator.set("user_name_name", "xuren");
    }

    @org.junit.Test
    public void testProto() throws InvalidProtocolBufferException {

    }
}
