package org.cronkeeper.server;

import java.io.IOException;
import java.net.*;
import java.nio.channels.ServerSocketChannel;

public class RpcServer {

    private final ServerSocket serverSocket;
    private final InetSocketAddress address;

    /**
     * init rpc server with fixed port
     * @param address - address provided by conf files
     */
    public RpcServer(InetSocketAddress address) throws IOException {

        // bind the address, make sure it available.
        this.serverSocket = ServerSocketChannel.open().socket();
        this.serverSocket.bind(address, 0);
        this.address = new InetSocketAddress(
                serverSocket.getInetAddress().getHostAddress(),
                serverSocket.getLocalPort());
    }

    /**
     * get the address which has been bound
     * @return address
     */
    public InetSocketAddress getAddress() {
        return address;
    }
}
