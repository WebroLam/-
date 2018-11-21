import java.util.concurrent.*;

public class MyThreadPool extends ThreadPoolExecutor {
    public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public MyThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("线程池中将要执行的线程" + t.getName());
        System.out.println("工作线程" + r.toString());
        System.out.println("线程池以完成的任务数量:" + getCompletedTaskCount() + "/" + getTaskCount());
        System.out.println("最大线程数量" + getLargestPoolSize());
        System.out.println("活动的线程数" + getActiveCount());
//t – 放在线程池里面要执行的线程。
//r – 将要执行这个线程的线程池里面的工作线程。
    }

    protected void terminated() {
        System.out.println("terminated getCorePoolSize:" + this.getCorePoolSize()
                + "；getPoolSize:" + this.getPoolSize() + "； getTaskCount:"
                + this.getTaskCount() + "；getCompletedTaskCount: " + this.getCompletedTaskCount()
                + "；getLargestPoolSize:" + this.getLargestPoolSize() + "；getActiveCount:"
                + this.getActiveCount());
        System.out.println("ThreadPoolExecutor terminated:");
    }

}
