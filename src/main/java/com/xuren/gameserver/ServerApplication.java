package com.xuren.gameserver;

import com.xuren.gameserver.net.NetWorkServer;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


//自动加载配置信息
@Configuration
@EnableAutoConfiguration
//使包路径下带有@Value的注解自动注入
//使包路径下带有@Autowired的类可以自动注入
@ComponentScan("com.xuren.gameserver")
@MapperScan("com.xuren.dao.mapper")
@SpringBootApplication
public class ServerApplication {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // 启动并初始化 Spring 环境及其各 Spring 组件
        ApplicationContext context =
                SpringApplication.run(ServerApplication.class, args);
        NetWorkServer netWorkServer = context.getBean(NetWorkServer.class);
        netWorkServer.runServer();
    }

}
