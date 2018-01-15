package org.cronkeeper.common;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URI;

public class Utils {

    /**
     * Create the instance of InetSocketAddress by ip:port/ip string.
     * @param address - ip:port or ip
     * @return
     */
    public static InetSocketAddress createAddrByStr(String address) throws Exception{
        try {
            URI uri = URI.create("dummyscheme://" + address);
            return createAddr(uri.getHost(), uri.getPort());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("The address should contain valid ip and port, currently " +
                    "the string of the address is " +  address);
        }
    }


    /**
     *
     * @param host - host name
     * @param port - port if port is  -1, then randomly pick an available port.
     * @return
     */
    public static InetSocketAddress createAddr(String host, int port) throws Exception {
        if (port == -1) {
            port = getFreeSocketPort();
        }
        InetAddress addr = InetAddress.getByName(host);
        return new InetSocketAddress(addr, port);
    }


    /**
     * Return a free port number. There is no guarantee it will remain free, so
     * it should be used immediately.
     *
     * @returns A free port for binding a local socket
     */
    public static int getFreeSocketPort() {
        int port = 0;
        try {
            ServerSocket s = new ServerSocket(0);
            port = s.getLocalPort();
            s.close();
            return port;
        } catch (IOException e) {
            // Could not get a free port. Return default port 0.
        }
        return port;
    }
}
