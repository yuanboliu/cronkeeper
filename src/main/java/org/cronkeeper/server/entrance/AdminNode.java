package org.cronkeeper.server.entrance;

import org.cronkeeper.server.CronkeeperConf;
import org.cronkeeper.server.Server;

public class AdminNode extends Server {

    private AdminNode() {

    }

    public static AdminNode createNode() {
        return new AdminNode();
    }

    @Override
    public void init(CronkeeperConf conf) throws Exception {

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
        AdminNode node = AdminNode.createNode();

        // init node
        node.init(new CronkeeperConf());

        // run node
        node.run();

        // once node quits from start status, release resources
        node.destroy();
    }
}
