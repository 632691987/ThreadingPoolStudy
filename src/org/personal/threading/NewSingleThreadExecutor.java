package org.personal.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewSingleThreadExecutor {

	public static void main(String[] args) {
		ExecutorService threadPool = null;

		// 模拟10个用户来办理业务
		threadPool = Executors.newSingleThreadExecutor();
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
