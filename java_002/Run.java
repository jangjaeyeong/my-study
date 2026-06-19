package java002.space;

public class Run {
    public static void main(String[] args) {
        Space s1 = new Space();
        Space s2 = new Space();
        s1.setTargetPlanet("달");
        s1.setSpaceship("아폴로 11호");
        s1.setFuel(100);
        s1.info();
        s1.start();
        s1.setFuel(20);
        s1.finishLine(s1.getFuel());

        s2.setTargetPlanet("화성");
        s2.setSpaceship("마스");
        s2.setFuel(80);
        s2.info();
        s2.start();
        s2.setFuel(0);
        s2.finishLine(s2.getFuel());
    }
}
