package java002.space;

public class Space {
    private String targetPlanet;
    private String spaceship;
    private int fuel;
    private boolean isPoweredOn;

    public Space() {

    }

    public String getTargetPlanet() {
        return targetPlanet;
    }
    public void setTargetPlanet(String targetPlanet) {
        this.targetPlanet = targetPlanet;
    }
    public String getSpaceship() {
        return spaceship;
    }
    public void setSpaceship(String spaceship) {
        this.spaceship = spaceship;
    }
    public boolean isPoweredOn() {
        return isPoweredOn;
    }
    public void setPoweredOn(boolean poweredOn) {
        isPoweredOn = poweredOn;
    }
    public int getFuel() {
        return fuel;
    }
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void info() {
        System.out.println("목표 행성: " + targetPlanet);
        System.out.println("우주선: " + spaceship);
        System.out.println("연료 총량: " + fuel + "%");
        System.out.println("===============");
    }
    public void start() {
        System.out.println("출발");
        isPoweredOn = true;
        System.out.println("전원: " + isPoweredOn);
        System.out.println("===============");
    }
    public void finishLine(int fuel) {
        isPoweredOn = false;
        System.out.println("도착");
        System.out.println("현재 위치: " + targetPlanet);
        System.out.println("우주선: " + spaceship);
        System.out.println("전원: " + isPoweredOn);
        System.out.println("남은 연료: " + fuel + "%");
        System.out.println();

    }
}
