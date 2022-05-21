package Model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server implements Runnable{
    private int timpAsteptare;
    private BlockingQueue<Task> queue;
    private int sid;

    public Server(int sid) {
        this.sid = sid;
        this.timpAsteptare=0;
        this.queue= new ArrayBlockingQueue<>(9999);
    }

    public int getTimpAsteptare() {
        return timpAsteptare;
    }

    public BlockingQueue<Task> getQueue() {
        return queue;
    }

    @Override
    public String toString()
    {
        String s;
        s="Coada "+this.sid+": ";
        for(Task e : this.queue)
        {
            s=s+" "+ e.toString();
        }
        return s;
    }
    public void addTask(final Task t)
    {
        this.queue.add(t);
        this.timpAsteptare+=t.getProcesat();
    }

    @Override
    public void run() {
        while(true)
        {
            if(!queue.isEmpty())
            {
                int time=queue.peek().getProcesat();
                for(int j=0;j<time;j++)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.peek().setProcesat(queue.peek().getProcesat()-1);
                    if(this.timpAsteptare>0)
                        this.timpAsteptare--;
                }
                try {
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
