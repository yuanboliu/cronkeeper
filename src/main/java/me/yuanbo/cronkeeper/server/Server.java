package me.yuanbo.cronkeeper.server;


import me.yuanbo.cronkeeper.exceptions.CronkeeperException;

public abstract class Server {

    /**
     *
     * @param context 进程运行所需的上下文环境，包含参数，配置等信息
     * @throws CronkeeperException exception
     */
    public abstract void init(Context context) throws CronkeeperException;

    /**
     *
     * @throws CronkeeperException exception
     */
    public abstract void start() throws CronkeeperException;

    /**
     *
     * @throws CronkeeperException exception
     */
    public abstract void stop() throws CronkeeperException;

    /**
     *
     * @throws CronkeeperException exception
     */
    public abstract void destroy() throws CronkeeperException;

}
