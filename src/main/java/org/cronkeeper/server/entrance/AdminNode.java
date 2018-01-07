package org.cronkeeper.server.entrance;

import org.cronkeeper.exception.CronkeeperException;
import org.cronkeeper.server.CronkeeperConf;
import org.cronkeeper.server.Server;

public class AdminNode extends Server {

    private AdminNode() {

    }

    public static AdminNode createNode() {
        return new AdminNode();
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
        AdminNode node = AdminNode.createNode();

        // init node
        node.init(new CronkeeperConf());

        // run node
        node.run();

        // once node quits from start status, release resources
        node.destroy();
    }
}
