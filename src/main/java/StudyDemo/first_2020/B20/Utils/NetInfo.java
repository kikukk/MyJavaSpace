package StudyDemo.first_2020.B20.Utils;

import java.net.InetAddress;

/**
 * @author kikukk
 */
public class NetInfo {
    private InetAddress address;
    private int port;
    public NetInfo(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "NetInfo{" +
                "address=" + address +
                ", port=" + port +
                '}';
    }
}
