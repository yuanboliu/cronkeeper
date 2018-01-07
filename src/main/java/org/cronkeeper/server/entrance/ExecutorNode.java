package org.cronkeeper.server.entrance;

import org.cronkeeper.exception.CronkeeperException;
import org.cronkeeper.server.CronkeeperConf;
import org.cronkeeper.server.RpcServer;
import org.cronkeeper.server.Server;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ExecutorNode extends Server {

    private class ExecutorRpc extends RpcServer implements Runnable {

        /**
         * init rpc server with fixed port
         *
         * @param address - address provided by conf files
         */
        public ExecutorRpc(InetSocketAddress address) throws IOException {
            super(address);
        }

        @Override
        public void run() {

        }
    }

    private ExecutorNode() {
    }

    public static ExecutorNode createNode() throws Exception {
        return new ExecutorNode();
    }


    @Override
    public void init(CronkeeperConf conf) throws CronkeeperException {

    }

    @Override
    public void run() throws CronkeeperException {

    }

    @Override
    public void stop() throws CronkeeperException {

    }

    @Override
    public void destroy() throws CronkeeperException {

    }

    public static void main(String[] args) throws Exception {

        // create node for executor
        ExecutorNode node = ExecutorNode.createNode();

        // init node
        node.init(new CronkeeperConf());

        // run node
        node.run();

        // once node quits from start status, release resources
        node.destroy();

    }
}
