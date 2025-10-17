package bassfising.data;

public class Reel {
    // basic info
    private String name;
    private double power;
    private double lineLength;
    
    Reel(String name, double power, double lineLength) {
        this.name = name;
        this.power = power;
        this.lineLength = lineLength;
    }

    public String getName() { return this.name; }
    public double getPower() { return this.power; }
    public double getLength() { return this.lineLength; }
}
