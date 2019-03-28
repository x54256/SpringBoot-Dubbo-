package com.dist.base.utils;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.remoting.transport.netty.NettyClient;
import com.alibaba.dubbo.rpc.RpcContext;
import org.jboss.netty.channel.ChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * spring容器关闭前，先释放dubbo相关资源，以便优雅关闭
 */
@Component
public class DubboSpringContextClosedListener implements ApplicationListener<ContextClosedEvent> {

    private static Logger LOG = LoggerFactory.getLogger(DubboSpringContextClosedListener.class);

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {

        if (RpcContext.getContext().getRemoteHost() == null) {
            LOG.info("No resources to shut down");
            return;
        }
        LOG.info("before spring context is closed, dubbo config destroy all...");
        ProtocolConfig.destroyAll();
        // 用反射释放NettyClient所占用的资源, 以避免不能优雅shutdown的问题
        releaseNettyClientExternalResources();
    }

    private void releaseNettyClientExternalResources() {
        try {
            Field field = NettyClient.class.getDeclaredField("channelFactory");
            field.setAccessible(true);
            ChannelFactory channelFactory = (ChannelFactory) field.get(NettyClient.class);
            channelFactory.releaseExternalResources();
            field.setAccessible(false);
            LOG.info("Release NettyClient's external resources");
        } catch (Exception e) {
            LOG.error("Release NettyClient's external resources error", e);
        }
    }
}