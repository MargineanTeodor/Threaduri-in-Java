package BussinesLogic;
import Model.Server;
import Model.Task;
import java.util.Comparator;
import java.util.List;
public class ShortestQueueStrategy  implements Strategy{
    @Override
    public void addTask(List<Server> queues, Task t) {
        queues.get(0).addTask(t);
        queues.sort( new manevra());
    }
    public static class manevra implements Comparator<Server>
    {
        @Override
        public int compare(Server s1, Server s2)
        {
            return s1.getQueue().size()-s2.getQueue().size();
        }
    }
}
