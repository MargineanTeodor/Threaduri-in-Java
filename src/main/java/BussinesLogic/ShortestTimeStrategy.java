package BussinesLogic;
import Model.Server;
import Model.Task;
import java.util.Comparator;
import java.util.List;
public class ShortestTimeStrategy implements Strategy{

    @Override
    public void addTask(List<Server> queuees, Task t) {
        queuees.get(0).addTask(t);
        queuees.sort(new Manevra1());
    }
    public static class Manevra1 implements Comparator<Server>
    {
        @Override
        public int compare(Server s1, Server s2)
        {
            return s1.getTimpAsteptare()-s2.getTimpAsteptare();
        }
    }
}
