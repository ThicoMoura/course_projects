package br.com.moura.thico.dominio;

import java.time.LocalDate;

/**
 * Mentoring
 */
public class Mentoring {
    private String title, describe;
    private LocalDate date;

    /**
     * @param title
     * @param describe
     * @param date
     */
    public Mentoring(String title, String describe, LocalDate date) {
        this.title = title;
        this.describe = describe;
        this.date = date;
    }

    public Mentoring() { }

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
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Mentoring [title='" + title + "', describe='" + describe + "', date=" + date + "]";
    }

}