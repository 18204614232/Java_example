package main;

public class Main {

    /**
     * 主入口
     *
     * @param args 参数列表
     */
    public static void main(String[] args) {
        // 创建同步对象
        Object lock = new Object();

        // 创建线程
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 同步代码块
                synchronized (lock) {
                    // wait()方法调用前输出语句
                    System.out.println("我是" + Thread.currentThread().getName() + " --- wait前");
                    try {
                        // 调用同步对象的wait()方法
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // wait()方法调用后输出语句
                    System.out.println("我是" + Thread.currentThread().getName() + " --- wait后");
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 同步代码块
                synchronized (lock) {
                    // wait()方法调用前输出语句
                    System.out.println("我是" + Thread.currentThread().getName() + " --- wait前");

                    try {
                        // 调用同步对象的wait()方法
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // wait()方法调用后输出语句
                    System.out.println("我是" + Thread.currentThread().getName() + " --- wait后");
                }
            }
        });

        // 启动线程
        thread1.start();
        thread2.start();

        try {
            // 让主线程睡眠3秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 唤醒正在此对象监视器上等待的所有线程
        lock.notifyAll();
    }
}