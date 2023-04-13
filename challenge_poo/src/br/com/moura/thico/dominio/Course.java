package br.com.moura.thico.dominio;

/**
 * Course
 */
public class Course {
    private String title, describe;
    private int workload;

    /**
     * @param title
     * @param describe
     * @param workload
     */
    public Course(String title, String describe, int workload) {
        this.title = title;
        this.describe = describe;
        this.workload = workload;
    }

    public Course() { }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the describe
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * @param describe the describe to set
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

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
        return "Course [title='" + title + "', describe='" + describe + "', workload=" + workload + "]";
    }
    
}
