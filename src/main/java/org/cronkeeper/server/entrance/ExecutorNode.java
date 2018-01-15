package org.cronkeeper.server.entrance;

import org.cronkeeper.common.Utils;
import org.cronkeeper.server.CronkeeperConf;
import org.cronkeeper.server.RpcServer;
import org.cronkeeper.server.Server;

import java.io.IOException;
import java.net.InetSocketAddress;

import static org.cronkeeper.common.CKConstants.EXECUTOR_IPC_ADDRESS;
import static org.cronkeeper.common.CKConstants.EXECUTOR_IPC_ADDRESS_DEFAULT;

public class ExecutorNode extends Server {

    private RpcServer rpc;

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
    public void init(CronkeeperConf conf) throws Exception {
        String address = conf.get(EXECUTOR_IPC_ADDRESS, EXECUTOR_IPC_ADDRESS_DEFAULT);
        rpc = new ExecutorRpc(Utils.createAddrByStr(address));

    }

    @Override
    public void run() throws Exception {
        
    }

    @Override
    public void stop() throws Exception {

    }

    @Override
    public void destroy() throws Exception {

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
