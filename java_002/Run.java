package java002.space;

public class Run {
    public static void main(String[] args) {
        Space s1 = new Space("달", "아폴로 11호", 100);
        Space s2 = new Space("화성", "마스", 80);
        s1.info();
        s1.start();
        s1.setFuel(20);
        s1.finishLine(s1.getFuel());

        s2.info();
        s2.start();
        s2.setFuel(0);
        s2.finishLine(s2.getFuel());
    }
}
