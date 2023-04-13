import br.com.moura.thico.dominio.Course;

public final class Main {
    public static void main(String[] args) {
        Course firstCourse = new Course("Java", "Basic to intermediate java", 60);
        Course secondCourse = new Course("Scrum", "Scrum master", 40);

        System.out.println(firstCourse);
        System.out.println(secondCourse);
    }
}
