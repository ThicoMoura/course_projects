import java.time.LocalDate;

import br.com.moura.thico.dominio.Bootcamp;
import br.com.moura.thico.dominio.Contents;
import br.com.moura.thico.dominio.Course;
import br.com.moura.thico.dominio.Dev;
import br.com.moura.thico.dominio.Mentoring;

public final class Main {
    public static void main(String[] args) {
        Contents firstCourse = new Course("Java", "Basic to intermediate java", 60);
        Contents secondCourse = new Course("Scrum", "Scrum master", 40);

        Contents mentoring = new Mentoring("Mentoring java", "java introduction", LocalDate.now());

        Bootcamp java = new Bootcamp("Intensive Java", "A new bootcamp of java, zero to master");

        java.getContents().add(firstCourse);
        java.getContents().add(secondCourse);
        java.getContents().add(mentoring);

        System.out.println("*-------------*");

        Dev juninho = new Dev("Juninho do Pneu Galanteador");
        juninho.subscribeBootcamp(java);
        System.out.println("**");
        System.out.println("Subscribe contents: " + juninho.getSubscribedContents());
        juninho.progressBootcamp();
        System.out.println("**");
        System.out.println("Finished contents:" + juninho.getFinishedContents());
        System.out.println("**");
        System.out.println("XP:" + juninho.calcTotalXp());

        System.out.println("*-------------*");

        Dev clarinho = new Dev("Clarinho Falcão da Silva Rabanetes");
        clarinho.subscribeBootcamp(java);
        System.out.println("**");
        System.out.println("Subscribe contents: " + clarinho.getSubscribedContents());
        clarinho.progressBootcamp();
        System.out.println("**");
        System.out.println("Finished contents:" + clarinho.getFinishedContents());
        System.out.println("**");
        System.out.println("XP:" + clarinho.calcTotalXp());

        System.out.println("*-------------*");
    }
}
