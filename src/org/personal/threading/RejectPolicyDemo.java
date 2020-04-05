package org.personal.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;

public class RejectPolicyDemo {

	public static void main(String[] args) {
		ExecutorService threadPool = new ThreadPoolExecutor(
					2,//coreSize
					5,//maxTheadSize
					1L,//Max waiting time
					TimeUnit.SECONDS,
					new LinkedBlockingQueue<Runnable>(3),//waiting queue
					Executors.defaultThreadFactory(),//default thread factory
					new ThreadPoolExecutor.DiscardPolicy()
				);
		
		try {
			for (int i = 0; i < 10; i++) {
				threadPool.execute(() -> {
					try {
						System.out.println(Thread.currentThread().getName() + "\t 办理业务");
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	}

}
