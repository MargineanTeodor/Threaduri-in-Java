package BussinesLogic;

import Model.Server;
import Model.Task;

import java.util.List;

public interface Strategy {
    public void addTask(List<Server> queuees, Task t);
}
