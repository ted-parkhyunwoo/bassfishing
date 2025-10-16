package bassfising.utility;
import bassfising.utility.converter.*;

public class SimplePrint {
    //! 싱글톤 디자인. 4개의 멤버메서드는 모두 초기화 없이 static으로 작동한다.


    // Line.convert
    public static void printLineConvert(double power, String input_type) {
        System.out.println(power + input_type + ": " + Line.convert(power, input_type) + (input_type == "lb"? "호": "lb" ));
    }

    // Sinker.convert
    public static void printSinkerConvert(double weight, String unit) {
        final String resUnit = unit == "g"? "ho" : "g";
        double res = Sinker.convert(weight, unit);
        // 보기싫은 소수점 두자리까지만 반올림처리
        final String resStr = String.format("%.2f", res);
        System.out.println(weight + unit + ": " + resStr + (resUnit == "g"? "g":"호"));
    }    


    // simple print other power line
    // public void printOtherLine(ReelLineCapa obj, double newPower, String lineUnit) {
    public static void printOtherLine(ReelLineCapa obj, double newPower, String lineUnit) {

        // double -> rounded Strings. 필요에 맞춰 모두 반올림처리됨.
        String newCapaStr = String.format("%.1f",  obj.getOtherLineCapa(newPower, lineUnit));
        String newPowerLbStr = lineUnit == "lb"? String.format("%.0f", newPower) + "lb" : String.format("%.0f", Line.convert(newPower, "ho")) + "lb";
        String newPowerHoStr = lineUnit == "lb"? String.format("%.1f", Line.convert(newPower, "lb")) + "ho" : String.format("%.1f", newPower) + "ho";

        // string stream for result string
        StringBuilder sb = new StringBuilder();             
        sb.append(newPowerHoStr + "-" + newPowerLbStr  + " " + newCapaStr + ReelLineCapa.LENGTH_UNIT);

        // conditionally added text (lb 기준으로 작동함. ho로 입력받은 인스턴스도 상관없이 lb기준으로 작동.)
        boolean isDefaultPower = obj.getBasePowerLb() == newPower;
        boolean isDefferenceOver = obj.getBasePowerLb() >= newPower + ReelLineCapa.DEFFERENCE_OVER || obj.getBasePowerLb() <= newPower - ReelLineCapa.DEFFERENCE_OVER;
        if (isDefferenceOver)       { sb.append(" (오차주의)"); }
        if (isDefaultPower)         { sb.append(" (Base)"); }

        // print result.
        System.out.println(sb.toString());
    }

    // printOtherLine() overloading. print 4lb ~ 20lb (step 2lb)
    public static void printOtherLine(ReelLineCapa obj) {
        int startPoint = 4;
        int endPoint = 20;
        System.out.println(obj.getName() + ":");
        for (int i = startPoint; i <= endPoint; i += 2) {
            System.out.print("  ");
            printOtherLine(obj, i, "lb");
        }
    }
}
