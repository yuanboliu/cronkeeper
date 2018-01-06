package org.cronkeeper.server;

import java.util.Properties;

public class CronkeeperConf {

    private Properties properties;

    public CronkeeperConf(String[] args) {
        // 终端传入的配置信息优先级最高
        if (args != null) {

        }

        // 加载conf下的cronkeeper-site.xml


        // 加载resource下的cronkeeper-default.xml
    }

    public CronkeeperConf() {
        new CronkeeperConf(null);
    }
}
