package vue.web.mvc.server.gameserver;

import vue.web.mvc.server.common.NettyConfig;
import vue.web.mvc.server.common.NettyTCPServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * Author     : zh_zhou
 * Create at  : 2017/11/26 11:37
 * Description:
 */
public class WsServer extends NettyTCPServer {
    public WsServer(NettyConfig nettyConfig, ChannelInitializer<? extends Channel> channelInitializer) {
        super(nettyConfig, channelInitializer);
    }
}
