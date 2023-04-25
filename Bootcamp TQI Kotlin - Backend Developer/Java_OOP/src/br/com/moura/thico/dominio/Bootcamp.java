package br.com.moura.thico.dominio;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Bootcamp {
    private String name, describe;

    private final LocalDate initialDate = LocalDate.now();
    private final LocalDate finalDate = initialDate.plusDays(45);

    private Set<Dev> subscribedDevs = new HashSet<>();
    private Set<Contents> contents = new LinkedHashSet<>();

    /**
     * @param name
     * @param describe
     */
    public Bootcamp(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }

    public Bootcamp() {}

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the initialDate
     */
    public LocalDate getInitialDate() {
        return initialDate;
    }

    /**
     * @return the finalDate
     */
    public LocalDate getFinalDate() {
        return finalDate;
    }

    /**
     * @return the subscribedDevs
     */
    public Set<Dev> getSubscribedDevs() {
        return subscribedDevs;
    }

    /**
     * @param subscribedDevs the subscribedDevs to set
     */
    public void setSubscribedDevs(Set<Dev> subscribedDevs) {
        this.subscribedDevs = subscribedDevs;
    }

    /**
     * @return the contents
     */
    public Set<Contents> getContents() {
        return contents;
    }

    /**
     * @param contents the contents to set
     */
    public void setContents(Set<Contents> contents) {
        this.contents = contents;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bootcamp other = (Bootcamp) obj;
        return Objects.equals(name, other.name) && Objects.equals(describe, other.describe) && Objects.equals(initialDate, other.initialDate) && Objects.equals(finalDate, other.finalDate) && Objects.equals(subscribedDevs, other.subscribedDevs) && Objects.equals(contents, other.contents);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, describe, initialDate, finalDate, subscribedDevs, contents);
    }
}