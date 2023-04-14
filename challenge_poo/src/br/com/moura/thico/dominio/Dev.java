package br.com.moura.thico.dominio;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Dev {
    private String name;
    private Set<Contents> subscribedContents = new LinkedHashSet<>();
    private Set<Contents> finishedContents = new LinkedHashSet<>();

    /**
     * @param name
     */
    public Dev(String name) {
        this.name = name;
    }

    public Dev() { }

    /**
     * @param bootcamp
     */
    public void subscribeBootcamp(Bootcamp bootcamp) {
        subscribedContents.addAll(bootcamp.getContents());
        bootcamp.getSubscribedDevs().add(this);
    }

    public void progressBootcamp() {
        Optional<Contents> content = this.subscribedContents.stream().findFirst();
        if (content.isPresent()) {
            this.finishedContents.add(content.get());
            this.subscribedContents.remove(content.get());
        } else {
            System.err.println("You are not subscribed to any content!");
        }
    }

    public double calcTotalXp() {
        return this.finishedContents
            .stream()
            .mapToDouble(content -> content.calcXp())
            .sum();   
    }

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
     * @return the subscribedContents
     */
    public Set<Contents> getSubscribedContents() {
        return subscribedContents;
    }

    /**
     * @param subscribedContents the subscribedContents to set
     */
    public void setSubscribedContents(Set<Contents> subscribedContents) {
        this.subscribedContents = subscribedContents;
    }

    /**
     * @return the finishedContents
     */
    public Set<Contents> getFinishedContents() {
        return finishedContents;
    }

    /**
     * @param finishedContents the finishedContents to set
     */
    public void setFinishedContents(Set<Contents> finishedContents) {
        this.finishedContents = finishedContents;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Dev other = (Dev) obj;
        return Objects.equals(name, other.name) && Objects.equals(subscribedContents, other.subscribedContents) && Objects.equals(finishedContents, other.finishedContents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subscribedContents, finishedContents);
    }
    
}
