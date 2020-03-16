import com.xuren.proto.PersonModel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

public class Test2 {
    @Test
    public void testHander() {
        final TestHander t = new TestHander();
        ChannelInitializer i = new ChannelInitializer() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(t);
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(i);
        ByteBuf buff = Unpooled.buffer();
        buff.writeInt(1);
        channel.writeInbound(buff);
        channel.flush();
    }

    @Test
    public void testProto() {
        PersonModel.Person.Builder builder = PersonModel.Person.newBuilder();
        builder.setId(1);
        PersonModel.Person person = builder.build();
        for (byte b : person.toByteArray()) {
            System.out.print(b);
        }

    }
}
