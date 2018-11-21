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
        System.out.println("�̳߳��н�Ҫִ�е��߳�" + t.getName());
        System.out.println("�����߳�" + r.toString());
        System.out.println("�̳߳�����ɵ���������:" + getCompletedTaskCount() + "/" + getTaskCount());
        System.out.println("����߳�����" + getLargestPoolSize());
        System.out.println("����߳���" + getActiveCount());
//t �C �����̳߳�����Ҫִ�е��̡߳�
//r �C ��Ҫִ������̵߳��̳߳�����Ĺ����̡߳�
    }

    protected void terminated() {
        System.out.println("terminated getCorePoolSize:" + this.getCorePoolSize()
                + "��getPoolSize:" + this.getPoolSize() + "�� getTaskCount:"
                + this.getTaskCount() + "��getCompletedTaskCount: " + this.getCompletedTaskCount()
                + "��getLargestPoolSize:" + this.getLargestPoolSize() + "��getActiveCount:"
                + this.getActiveCount());
        System.out.println("ThreadPoolExecutor terminated:");
    }

}
