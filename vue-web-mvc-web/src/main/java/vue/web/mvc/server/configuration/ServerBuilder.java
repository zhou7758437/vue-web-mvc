package vue.web.mvc.server.configuration;

import vue.web.mvc.commom.config.AppServerConfig;
import vue.web.mvc.server.common.AbstractNettyServer;
import vue.web.mvc.server.common.NettyConfig;
import vue.web.mvc.server.common.NettyTCPServer;
import vue.web.mvc.server.common.NettyUDPServer;
import vue.web.mvc.server.common.channels.TcpChannelInitializer;
import vue.web.mvc.server.common.channels.UDPChannelInitializer;
import vue.web.mvc.server.common.channels.WsChannelInitalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Author     : zh_zhou
 * Create at  : 2017/11/26 11:49
 * Description:
 */
@Component
public class ServerBuilder {


    @Qualifier("tcpConfig")
    @Autowired
    NettyConfig tcpConfig;


    @Qualifier("udpConfig")
    @Autowired
    NettyConfig udpConfig;

    public SBuilder getTcpBuilder() {
        return new TcpBuilder();
    }

    public SBuilder getWsBuilder() {
        return new WsBuilder();
    }

    public SBuilder getUdpBuilder() {
        return new UdpBuilder();
    }

    public interface SBuilder {

        AbstractNettyServer build();
    }

    class TcpBuilder implements SBuilder {

        @Override
        public AbstractNettyServer build() {

            NettyTCPServer tcpServer = new NettyTCPServer(tcpConfig, new TcpChannelInitializer());
            return tcpServer;
        }
    }

    class WsBuilder implements SBuilder {

        @Override
        public AbstractNettyServer build() {
            NettyTCPServer wsServer = new NettyTCPServer(tcpConfig, new WsChannelInitalizer(AppServerConfig.WEB_SOCKET_PORT));
            return wsServer;
        }
    }

    class UdpBuilder implements SBuilder {

        @Override
        public AbstractNettyServer build() {
            NettyUDPServer wsServer = new NettyUDPServer(udpConfig, new UDPChannelInitializer());
            return wsServer;
        }
    }
}
