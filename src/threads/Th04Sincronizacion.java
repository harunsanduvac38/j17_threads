package threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Th04Sincronizacion {
	
	public static void main(String[] args) {
//		m1();
		//m1() hilos desincronizados
//		Thread th1 = new Thread(() ->{
//			while(true) {
//				ThreadUtil.sleep();
//				m1();
//			}
//		});
//		Thread th2 = new Thread(() ->{
//			while(true) {
//				ThreadUtil.sleep();
//				m1();
//			}
//		});

		//		
//		Thread th1 = new Thread(() ->{
//			while(true) {
//				ThreadUtil.sleep();
//				m2();
//			}
//		});
//		Thread th2 = new Thread(() ->{
//			while(true) {
//				ThreadUtil.sleep();
//				m2();
//			}
//		});
		
		
//		Object lock = new Object();
//		
//		Thread th1 = new Thread(() ->{
//			while(true) {
//				ThreadUtil.sleep();
//				m3(lock);
//			}
//		});
//		Thread th2 = new Thread(() ->{
//			while(true) {
//				ThreadUtil.sleep();
//				m3(lock);
//			}
//		});
		
		
		Lock lock = new ReentrantLock();
		
		Thread th1 = new Thread(() ->{
			while(true) {
				ThreadUtil.sleep();
				m4(lock);
			}
		});
		Thread th2 = new Thread(() ->{
			while(true) {
				ThreadUtil.sleep();
				m4(lock);
			}
		});
		
		
		th1.start();
		th2.start();
		
		
	}

	
	public static void m1() {
		System.out.println(Thread.currentThread().getName() + " entrando");
		ThreadUtil.sleep();
		System.out.println(Thread.currentThread().getName() + " saliendo");
		System.out.println("------------------------------");
	}
	
	
	public static synchronized void m2() {
		System.out.println(Thread.currentThread().getName() + " entradndo");
		ThreadUtil.sleep();
		System.out.println(Thread.currentThread().getName() + " saliendo");
		System.out.println("------------------------------");
	}
	public static  void m3(Object lock) {
		System.out.println(Thread.currentThread().getName() + " hace cosas");

		synchronized (lock) {
			System.out.println(Thread.currentThread().getName() + " entrando");
			ThreadUtil.sleep();
			System.out.println(Thread.currentThread().getName() + " saliendo");
			System.out.println("------------------------------");
		}
	}
	public static  void m4(Lock lock) {
		System.out.println(Thread.currentThread().getName() + " hace cosas");
		
		lock.lock();
		System.out.println(Thread.currentThread().getName() + " entrando");
		ThreadUtil.sleep();
		System.out.println(Thread.currentThread().getName() + " saliendo");
		System.out.println("------------------------------");
		lock.unlock();
	}
}
