import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.xuren.common.utils.RedisOperator;
import com.xuren.dao.entity.ItemEntity;
import com.xuren.dao.entity.UserInfoEntity;
import com.xuren.dao.mapper.UserMapper;
import com.xuren.gameserver.ServerApplication;
import com.xuren.gameserver.proto.ProtoMsg3;
import com.xuren.service.ItemService;
import com.xuren.service.PlayerService;
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
    private ItemService itemService;
    @Autowired
    private PlayerService playerService;
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

    @org.junit.Test
    public void testBoom() {
        BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                1500,
                0.01);
// 判断指定元素是否存在
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
// 将元素添加进布隆过滤器
        filter.put(1);
        filter.put(2);
        System.out.println(filter.mightContain(1));
        System.out.println(filter.mightContain(2));
    }

    @org.junit.Test
    public void testUpdate() {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setId("100001");
        userInfoEntity.setNickName("xuren002");
        userMapper.updateOne(userInfoEntity);
    }

    @org.junit.Test
    public void testItemAndPlayer() {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setPlayerId(100001);
        itemEntity.setAttribute("dsds");
        itemEntity.setItemId(1);
        itemEntity.setNum(1);
        itemService.insert(itemEntity);
//        itemService.deleteItems(100001L, 1);
        itemService.findItemsByItemId(1);
    }
}
