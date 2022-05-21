package Model;

public class Task {
    private int ajuns;
    private int procesat;
    private int tid;

    public Task(int ajuns, int procesat, int tid) {
        this.ajuns = ajuns;
        this.procesat = procesat;
        this.tid = tid;
    }

    public int getAjuns() {
        return ajuns;
    }

    public int getProcesat() {
        return procesat;
    }

    public void setProcesat(int procesat) {
        this.procesat = procesat;
    }

    @Override
    public String toString()
    {
        return "("+this.tid+","+this.ajuns+","+this.procesat+")";
    }
}
