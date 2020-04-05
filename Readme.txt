Executor
Executors
ExecutorService
ThreadPoolExecutor => 线程池底层 

ScheduleExecutorService

线程池的重要参数
public ThreadPoolExecutor(
	int corePoolSize, //线程池中的常驻核心线程数
	int maximumPoolSize,// 最大能够同时处理任务的线程数
	long keepAliveTime, //多余的空闲线程等待时间
	TimeUnit unit, //等待单位
	BlockingQueue<Runnable> workQueue,//相当与银行的等待区
	ThreadFactory threadFactory,
	RejectedExecutionHandler handler// 当侯客区满以后的策略(默认是放弃)
	) {
}

线程池底层工作原理
1, 当正在执行的线程数少于核心线程数(corePoolSize)时，立即执行。
2, 如果大于运行数的时候，放在队列
3, 如果队列也满了，就增加到最大线程数字(maximumPoolSize)
4, 如果队列依然已经满了，那么就执行拒绝策略
5, 当线程池大于核心数字，而且空闲时间超过额定时间keepAliveTime / TimeUnit 的时候，就销毁线程

线程池拒绝策略
当等待队列满了，并且执行线程也等于最大线程的时候，就执行拒绝策略
默认是 AbortPolicy 阻止系统执行
CallerRunsPolicy 返回给调用者本身（也就是main 主线程亲自运行）
DiscardOldestPolicy 丢弃等待最久的任务
DiscardPolicy 丢弃任务

实际上用哪种线程池呢？newFixedThreadPool / newCachedThreadPool / NewSingleThreadExecutor
答案是一个都不用。因为里面都使用到 SynchronousQueue / LinkedBlockingQueue 作为等待区。因此造成能够 submit Integer.MAX_VALUE 个任务，造成OOM
因此实际操作里面，应该直接创建 ThreadPoolExecutor


怎么配置maximumPoolSize
要看是CPU 密集型或者 IO 密集型的

如果是按照全套算法去做，实打实的CPU的，那么就是 CPU 核数 + 1
如果按照IO 密集性的，那么就是 CPU核心数 * 10