package bassfising.utility.converter;

public class Sinker {    
    public static double convert(double weight, String unit) {
        double res;
        // 예외처리 삽입할 것.
        if (unit != "g" && unit != "ho") { return -1; }
        if (unit == "g") { res = weight / 3.75; }
        else res = weight * 3.75;
        return res;
    }
}
