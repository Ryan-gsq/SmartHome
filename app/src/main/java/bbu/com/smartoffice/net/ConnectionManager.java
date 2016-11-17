package bbu.com.smartoffice.net;

import java.util.ArrayList;

/**
 * Created by G on 2016/6/4 0004.
 */

public class ConnectionManager {

    public static final int MAX_CONNECTIONS = 10;
    private static ConnectionManager instance;
    private ArrayList<Runnable> active = new ArrayList<>();
    private ArrayList<Runnable> queue = new ArrayList<>();

    public static ConnectionManager getInstance() {
        if (instance == null)
            instance = new ConnectionManager();
        return instance;
    }

    public void push(Runnable runnable) {
        queue.add(runnable);
        if (active.size() < MAX_CONNECTIONS)
            startNext();
    }

    private void startNext() {
        if (!queue.isEmpty()) {
            Runnable next = queue.get(0);
            queue.remove(0);
            active.add(next);

            Thread thread = new Thread(next);
            thread.start();
        }
    }

    public void didComplete(Runnable runnable) {
        active.remove(runnable);
        startNext();
    }

}