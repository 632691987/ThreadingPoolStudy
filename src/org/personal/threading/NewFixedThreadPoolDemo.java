package org.personal.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class NewFixedThreadPoolDemo {

	public static void main(String[] args) {
		ExecutorService threadPool = null;

		// 模拟10个用户来办理业务
		threadPool = Executors.newFixedThreadPool(5);
		try {
			for (int i = 0; i < 20; i++) {
				threadPool.execute(() -> {
					System.out.println(Thread.currentThread().getName() + "\t 办理业务");
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	}

}
