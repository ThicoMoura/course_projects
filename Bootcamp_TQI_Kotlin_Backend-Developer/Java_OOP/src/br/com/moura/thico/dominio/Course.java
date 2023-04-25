package br.com.moura.thico.dominio;

public class Course extends Contents{
    private int workload;

    /**
     * @param title
     * @param describe
     * @param workload
     */
    public Course(String title, String describe, int workload) {
        setTitle(title);
        setDescribe(describe);
        setWorkload(workload);
    }

    public Course() { }

    /**
     * @return the workload
     */
    public int getWorkload() {
        return workload;
    }

    /**
     * @param workload the workload to set
     */
    public void setWorkload(int workload) {
        this.workload = workload;
    }

    @Override
    public String toString() {
        return "Course [title='" + getTitle() + "', describe='" + getDescribe() + "', workload=" + workload + "]";
    }

    @Override
    public double calcXp() {
        return XP_DEFAULT * workload;
    }
    
}
