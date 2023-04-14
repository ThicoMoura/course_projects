package br.com.moura.thico.dominio;

import java.time.LocalDate;

public class Mentoring extends Contents {
    private LocalDate date;

    /**
     * @param title
     * @param describe
     * @param date
     */
    public Mentoring(String title, String describe, LocalDate date) {
        setTitle(title);
        setDescribe(describe);
        setDate(date);
    }

    public Mentoring() { }

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
        return "Mentoring [title='" + getTitle() + "', describe='" + getDescribe() + "', date=" + date + "]";
    }

    @Override
    public double calcXp() {
        return XP_DEFAULT + 20d;
    }

}