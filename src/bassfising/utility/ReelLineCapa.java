package bassfising.utility;


//          합사는 lb 사용이 거의 불가능할정도로 조구사마다 스펙 상이하므로 호수만 고려합니다.

/*  LineUnitChecker
DESC: 입력된 라인의 유닛검증 Class. String이 그저 "ho" 혹은 "lb" 인지만 검증합니다.
e.g: 
    LineUnitChecker.check(라인단위:String) -> return true(or false) 합니다.
*/
class LineUnitChecker {
    public static boolean check(String token)   { return token == "lb" || token == "ho"; }
}


/*  ReelLineCapa
DESC: 기본 권사량 정보를 입력하면 다른라인파워의 권사량을 보여줍니다. 매개변수가 있는 생성자 사용을 권장.
dependency: LineUnitChecker(내부), LineConverter(외부)

e.g: 
    초기화: ReelLineCapa myReel1 = new ReelLineCapa(14, "lb", 80);  - 기본정보 14lb 80m 감기는 릴이라는 뜻
    사용: myReel1.getOtherLineCapa(12, "lb")  -> 93.33333           - 12lb 라인 변경시 93.33333m 감을수 있다는 뜻
*/
public class ReelLineCapa {
    private float basePower;
    private float baseLength;
    private float basePowerLb;
    private float basePowerHo;
    
    public void setLength(float baseLength)     { this.baseLength = baseLength; }
    protected float getLength()                 { return this.baseLength; }

    // desc:    init all member values method.
    // e.g:     14lb 80m 라 치면 setBaseInfo(14, "lb", 80) 으로 입력.
    public void setBaseInfo(float basePower, String lineUnit, float baseLength) {
        // Todo. error control.
        if (!LineUnitChecker.check(lineUnit)) {
            System.out.println("[ERR]Line Type Error.");
            return;
        }
        this.basePower = basePower;
        this.baseLength = baseLength;
        boolean isLbUnit = lineUnit == "lb"? true: false;
        this.basePowerLb = (isLbUnit)? this.basePower : LineConverter.convert(this.basePower, "ho", "lb");
        this.basePowerHo = (isLbUnit)? LineConverter.convert(this.basePower, "ho", "lb") : this.basePower;        
    }

    // Constructor. setBaseInfo를 호출
    public ReelLineCapa(float basePower, String lineUnit, float baseLength) {
        setBaseInfo(basePower, lineUnit, baseLength);
    }

    // targetPower:float 는 변경할 라인의 파워. lineUnit은 단위이며, "ho" 혹은 "lb".
    public float getOtherLineCapa(float tergetPower, String lineUnit) {
        if (this.basePower == 0f || this.baseLength == 0f) {
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
}


