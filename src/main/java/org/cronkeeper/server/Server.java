package org.cronkeeper.server;

import org.cronkeeper.exception.CronkeeperException;

public abstract class Server {

    /**
     * 初始化server
     * @param conf 配置信息
     * @throws CronkeeperException
     */
    public abstract void init(CronkeeperConf conf) throws CronkeeperException;

    /**
     * init 之后执行run，进程进入running状态
     * @throws CronkeeperException
     */
    public abstract void run() throws CronkeeperException;

    /**
     * 停止进程，此函数一般通过rpc调用，让进城停止运行
     * @throws CronkeeperException
     */
    public abstract void stop() throws CronkeeperException;

    /**
     * 程序退出running状态之后执行，主要用于释放一些资源
     * @throws CronkeeperException
     */
    public abstract void destroy() throws CronkeeperException;
}
