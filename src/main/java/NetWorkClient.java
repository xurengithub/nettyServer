import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

public class NetWorkClient {
    private Bootstrap bootstrap;
    public void runClient() {
        EventLoopGroup worker = new NioEventLoopGroup(1);
        bootstrap.group(worker);
//        bootstrap
    }
}
