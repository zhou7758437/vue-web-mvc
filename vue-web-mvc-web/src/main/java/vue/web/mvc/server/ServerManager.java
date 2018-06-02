package vue.web.mvc.server;

/**
 * Author     : zh_zhou
 * Create at  : 2017/11/26 11:39
 * Description:
 */
public interface ServerManager {

     void startServers(int tcpPort, int websocketPort, int udpPort) throws Exception;


     void stopServers() throws Exception;
}
