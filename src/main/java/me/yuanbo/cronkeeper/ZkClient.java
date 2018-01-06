package me.yuanbo.cronkeeper;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZkClient {
    public static void main(String[] args) throws Exception {
        String hosts = "111.231.221.191:2181,111.231.221.191:2181,111.231.221.191:2181";
        ZooKeeper zooKeeper = new ZooKeeper(hosts, 1000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        });
        System.out.println(zooKeeper.getState().isAlive());
    }
}
