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
    초기화: ReelLineCapa myReel1 = new ReelLineCapa(14, "lb", 80);  - 기본정보 14lb 80m 감기는 릴이라는 뜻
    사용: myReel1.getOtherLineCapa(12, "lb")  -> 93.33333           - 12lb 라인 변경시 93.33333m 감을수 있다는 뜻
*/
public class ReelLineCapa {
    // 기본 길이 단위 상수.
    public static final String LENGTH_UNIT = "m";

    private String name;
    private double baseLength;
    // 한쪽만 입력해도 생성자에서 자동으로 채워넣음.
    private double basePowerLb;
    private double basePowerHo;
    { this.name = "empty"; }

    public void setLength(double baseLength)        { this.baseLength = baseLength; }
    protected double getLength()                    { return this.baseLength; }
    public double getBasePowerLb()                  { return this.basePowerLb; }
    public void setName(String name)                { this.name = name; }
    public String getName()                         { return this.name; }


    // desc:    init all member values method.
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
        this.basePowerHo = (isLbUnit)? Line.convert(basePower, "ho") : basePower;        
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
            
        return 0f;
    }

    // simple print other power line.
    public void printOtherLine(double targetPower, String lineUnit) {
        double res = getOtherLineCapa(targetPower, lineUnit);
        String basePowerStr = String.format("%.2f", getBasePowerLb());
        String resStr = String.format("%.2f", res);
        System.out.print(targetPower + "lb " + resStr + LENGTH_UNIT);
        if (getBasePowerLb() >= targetPower + 6 || getBasePowerLb() <= targetPower - 6) {
            System.out.print("  오차주의: 기본(" + basePowerStr + "lb)보다 6lb 이상 차이.");
        }
        if (getBasePowerLb() == targetPower) {
            System.out.print(" (기본값)");
        }
        System.out.println();
    }

    // 자주쓰는 lb 4~20 까지 모두 출력
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


