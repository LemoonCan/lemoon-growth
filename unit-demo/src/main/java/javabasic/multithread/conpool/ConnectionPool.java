package javabasic.multithread.conpool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author lee
 * @date 2022/12/8
 */
public class ConnectionPool {
    LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.add(ConnectionDriver.createConnection());
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.pollFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = future - System.currentTimeMillis();
                while (pool.isEmpty()&&remaining>=0){
                    pool.wait(remaining);
                    remaining = future-System.currentTimeMillis();
                }
                if(!pool.isEmpty()){
                    return pool.pollFirst();
                }
                return null;
            }
        }
    }

    public void releaseConnection(Connection connection){
        if(connection==null) return;
        synchronized (pool){
            pool.offerLast(connection);
            pool.notifyAll();
        }
    }
}
