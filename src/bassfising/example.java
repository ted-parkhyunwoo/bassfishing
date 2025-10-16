package bassfising;
import bassfising.utility.ReelLineCapa;
import bassfising.utility.converter.Line;
import bassfising.utility.converter.Sinker;
import bassfising.utility.SimplePrint;

public class example {
    // UNIT. 직접 String으로 사용해도 됨.
    static String lb = "lb";
    static String ho = "ho";
    static String g = "g";

    public static void main(String[] args) {
        // !주의: Sinker,Line convert의 두번 째 매개변수는 현재 단위를 넣음. (3호: 3, "ho") -> 자동으로 gram, lb로 전환
        // convert(double, String)  타입
        // 싱커 호수<->gram 상호 변환기
        System.out.println(Sinker.convert(3, ho));      // 직접출력: 11.25 (return type:double)
        // 라인 호수<->lb 상호 변환기
        System.out.println(Line.convert(4, ho));        // 직접출력: 16.0  (return type:double)


        // SimplePrint 쉬운출력 (주의: 마찬가지로 현재 단위를 넣는다.)
        SimplePrint.printSinkerConvert(14, g);          // 14.0g: 3.73호        ("g" 를 넣으면 호수로 변환)
        SimplePrint.printSinkerConvert(4.5, ho);        // 4.5ho: 16.88g        ("ho" 를 넣으면 그램으로 변환)
        SimplePrint.printLineConvert(12, lb);           // 12.0lb: 3.0호        ("lb"를 넣으면 호수로 변환)
        SimplePrint.printLineConvert(1.5, ho);          // 1.5ho: 6.0lb         ("ho"를 넣으면 lb로 변환)


        // 릴 기본제원 권사량을 베이스로 다른파워의 라인 권사량 추정.
        // 미터-야드는 전환되지 않음. 하나만 고정하여 사용. (ReelLineCapa 클래스변수(전역상수) LENGTH_UNIT 로 수정)
        ReelLineCapa myZillion21 = new ReelLineCapa(14, lb, 90);                // 초기화: myZillion21 은 14 lb 90m 감기는 릴
        myZillion21.setName("21질리언");                                        // 초기화 전까지는 "Unknown" 으로 기본설정됨.
        ReelLineCapa iWant = new ReelLineCapa("21SteezeLtd", 3.5, ho, 85);      // 생성자 오버로딩으로 이름 초기화 가능.

        // myZillion21의 12lb는 몇미터 감기는지 return.
        double res = myZillion21.getOtherLineCapa(12, lb);
        System.out.println(res);                        // 105.0


        // SimplePrint를 이용한 출력. printOtherLine메서드는 매개변수로 ReelLineCapa 객체는 필수로 받음.
        // 쉬운출력 - 단일 특정라인 권사량 추정 출력.
        SimplePrint.printOtherLine(myZillion21, 16, lb);        // 4.0ho-16lb 78.8m (새로감을 라인 "(a)호-(b)lb" 자동변환 기입)
        SimplePrint.printOtherLine(iWant, 3, ho);               // 3.0ho-12lb 99.2m

        // 쉬운출력2 - 자주 쓰이는 라인 전체출력 (4lb ~ 20lb)
        SimplePrint.printOtherLine(myZillion21);
        /* print.
        21질리언:
            1.0ho-4lb 315.0m (오차주의)
            1.5ho-6lb 210.0m (오차주의)
            2.0ho-8lb 157.5m (오차주의)
            2.5ho-10lb 126.0m
            3.0ho-12lb 105.0m
            3.5ho-14lb 90.0m (Base)
            4.0ho-16lb 78.8m
            4.5ho-18lb 70.0m
            5.0ho-20lb 63.0m (오차주의)
         */
        // 쉬운출력2 - iWant 릴 출력
        SimplePrint.printOtherLine(iWant);
    }
}
