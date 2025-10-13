package bassfising.utility;
import bassfising.utility.converter.Line;


/*  class LineUnitChecker
    DESC: 입력된 라인의 유닛검증 Class. String이 그저 "ho" 혹은 "lb" 인지만 검증합니다.
    e.g: 
        LineUnitChecker.check(라인단위:String) -> return true(or false) 합니다.
*/
class LineUnitChecker {
    public static boolean check(String token)   { return token == "lb" || token == "ho"; }
}


/*  class ReelLineCapa
    DESC: 기본 권사량 정보를 입력하면 다른라인파워의 권사량을 보여줍니다. 매개변수가 있는 생성자 사용을 권장.
    dependency: LineUnitChecker(내부), LineConverter(외부)
    e.g: 
        초기화: ReelLineCapa myReel1 = new ReelLineCapa(14, "lb", 80);  - 기본정보 14lb 80m 권사량을 지정.
        사용: myReel1.getOtherLineCapa(12, "lb")  -> 93.33333(double)   - 12lb 라인 변경시 93.33333m 권사량
        Print: myReel1.printOtherLine(12, "lb")   -> 3.0ho-12lb 93.3m(void print)   -  3호, 12파운드는 93.3m 권사량
*/
public class ReelLineCapa {
    // 기본 길이 단위 상수.
    public static final String LENGTH_UNIT = "m";
    // 오차보정(printOtherLine 메서드가 기본프로필의 lb보다 새로감을 라인의 lb가 얼마나 차이나면 "(오차주의)"경고문을 띄울지 결정). 
    public static final int DEFFERENCE_OVER = 6;    // 6lb 기본값.

    private String name = "Unknown";
    private double baseLength;

    // 한쪽만 입력해도 생성자(정확히는 setBaseInfo)에서 자동으로 채워넣음.
    private double basePowerLb;
    private double basePowerHo;

    // getter setter.
    private double getLength()                       { return this.baseLength; }
    private double getBasePowerLb()                  { return this.basePowerLb; }
    private double getBasePowerHo()                  { return this.basePowerHo; }
    public void setName(String name)                 { this.name = name; }
    public String getName()                          { return this.name; }
    

    // desc:    init all member values method. 생성자에 쓰일 기본 초기화메서드. 몇몇 멤버변수를 초기화함.
    // e.g:     14lb 80m 라 치면 setBaseInfo(14, "lb", 80) 으로 입력.
    public void setBaseInfo(double basePower, String lineUnit, double baseLength) {
        // Todo. error control.
        if (!LineUnitChecker.check(lineUnit)) {
            System.out.println("[ERR]Line Type Error.");
            return;
        }
        this.baseLength = baseLength;
        boolean isLbUnit = lineUnit == "lb"? true: false;
        this.basePowerLb = (isLbUnit)? basePower : Line.convert(basePower, "ho");
        this.basePowerHo = (isLbUnit)? Line.convert(basePower, "lb") : basePower;   // debug-251013: 논리오류 수정. "ho" -> "lb" 로 교체
    }

    // Constructor. setBaseInfo를 호출
    public ReelLineCapa(double basePower, String lineUnit, double baseLength) {
        setBaseInfo(basePower, lineUnit, baseLength);
    }
    // 이름을 추가한 생성자 오버로딩
    public ReelLineCapa(String name, double basePower, String lineUnit, double baseLength) {
        setBaseInfo(basePower, lineUnit, baseLength);
        setName(name);
    }

    
    // targetPower:double 는 변경할 라인의 파워. lineUnit은 단위이며, "ho" 혹은 "lb".
    public double getOtherLineCapa(double tergetPower, String lineUnit) {
        // TODO 예외 컨트롤.
        if (this.basePowerLb == 0 || this.baseLength == 0) {
            System.out.println("[ERR]initialize was wrong.");
            return -1;
        }

        if (!LineUnitChecker.check(lineUnit))
            System.out.println("[ERR]Wrong line type");

        switch (lineUnit) {
            case "ho": { return getLength() * (basePowerHo / tergetPower); }
            case "lb": { return getLength() * (basePowerLb / tergetPower); }
            default: System.out.println("[Error] Line Type was wrong.");
        }
            
        return -1f;
    }
    

    // 이하 멤버메서드: 쉬운 출력문. 
    // simple print other power line
    public void printOtherLine(double newPower, String lineUnit) {
        // double -> rounded Strings. 필요에 맞춰 모두 반올림처리됨.
        String newCapaStr = String.format("%.1f",  getOtherLineCapa(newPower, lineUnit));
        String newPowerLbStr = lineUnit == "lb"? String.format("%.0f", newPower) + "lb" : String.format("%.0f", Line.convert(newPower, "ho")) + "lb";
        String newPowerHoStr = lineUnit == "lb"? String.format("%.1f", Line.convert(newPower, "lb")) + "ho" : String.format("%.1f", newPower) + "ho";

        // string stream for result string
        StringBuilder sb = new StringBuilder();             
        sb.append(newPowerHoStr + "-" + newPowerLbStr  + " " + newCapaStr + LENGTH_UNIT);

        // conditionally added text (lb 기준으로 작동함. ho로 입력받은 인스턴스도 상관없이 lb기준으로 작동.)
        boolean isDefaultPower = getBasePowerLb() == newPower;
        boolean isDefferenceOver = getBasePowerLb() >= newPower + DEFFERENCE_OVER || getBasePowerLb() <= newPower - DEFFERENCE_OVER;
        if (isDefferenceOver)       { sb.append(" (오차주의)"); }
        if (isDefaultPower)         { sb.append(" (Base)"); }

        // print result.
        System.out.println(sb.toString());
    }

    // printOtherLine() overloading. print 4lb ~ 20lb (step 2lb)
    public void printOtherLine() {
        int startPoint = 4;
        int endPoint = 20;
        System.out.println(getName() + ":");
        for (int i = startPoint; i <= endPoint; i += 2) {
            System.out.print("  ");
            printOtherLine(i, "lb");
        }
    }
}


