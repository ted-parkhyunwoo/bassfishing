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
    
    public static void printConvert(double weight, String unit) {
        final String resUnit = unit == "g"? "ho" : "g";
        double res = convert(weight, unit);
        // 보기싫은 소수점 두자리까지만 반올림처리
        final String resStr = String.format("%.2f", res);
        System.out.println(weight + unit + ": " + resStr + resUnit);
    }
}
