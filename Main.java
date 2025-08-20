public class Main {
    public static void main(String[] args) {
        Resource resource1 = new Resource("Recurso 1", 0);
        Resource resource2 = new Resource("Recurso 2", 1);

        Thread process1 = new Process1(0, resource1, resource2);
        Thread process2 = new Process2(1, resource1, resource2);

        process1.start();
        process2.start();
    }
}