package vue.web.mvc.server.gameserver;

import vue.web.mvc.server.common.NettyConfig;
import vue.web.mvc.server.common.NettyUDPServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * Author     : zh_zhou
 * Create at  : 2017/11/26 11:41
 * Description:
 */
public class UdpServer extends NettyUDPServer {
    public UdpServer(NettyConfig nettyConfig, ChannelInitializer<? extends Channel> channelInitializer) {
        super(nettyConfig, channelInitializer);
    }
}
