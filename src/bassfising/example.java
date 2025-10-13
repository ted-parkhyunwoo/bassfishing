package bassfising;
import bassfising.utility.ReelLineCapa;
import bassfising.utility.converter.Line;
import bassfising.utility.converter.Sinker;

public class example {
    // UNIT. 직접 String으로 사용해도 됨.
    static String lb = "lb";
    static String ho = "ho";
    static String g = "g";

    public static void main(String[] args) {
        // !주의: Sinker,Line convert의 두번 째 매개변수는 현재 값을 넣습니다. (3호: 3, ho) -> 자동으로 gram, lb로 전환
        // 싱커 호수<->gram 상호 변환기
        System.out.println(Sinker.convert(3, ho));     // 11.25 (return type:double)
        Sinker.printConvert(14, g);                    // 14.0g: 3.73호
        Sinker.printConvert(5, ho);                    // 5.0ho: 18.75g


        // 라인 호수<->lb 상호 변환기
        System.out.println(Line.convert(4, ho));       // 16.0  (return type:double)
        Line.printConvert(12, lb);                     // 12.0lb: 3.0호


        // 릴 기본제원 권사량을 베이스로 다른파워의 라인 권사량 추정.
        // 초기화: myZillion21 은 14lb 90m 감기는 릴.
        ReelLineCapa myZillion21 = new ReelLineCapa(14, lb, 90);
        ReelLineCapa iWant = new ReelLineCapa("21SteezeLtd", 3.5, ho, 85);   //  이름 초기화 가능. 기본값:"Unknown";
        
        // myZillion21의 12lb는 몇미터 감기는지 return.
        double res = myZillion21.getOtherLineCapa(12, lb);
        System.out.println(res);                        // 105.0
        
        // 쉬운출력 - 단일 특정라인 권사량 추정 출력.
        myZillion21.printOtherLine(16, lb);             // 4.0ho-16lb 78.8m
        iWant.printOtherLine(3, ho);                    // 3.0ho-12lb 99.2m

        // 쉬운출력2 - 자주 쓰이는 라인 전체출력 (4lb ~ 20lb)
        myZillion21.setName("21질리언");
        myZillion21.printOtherLine();
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
        iWant.printOtherLine();
    }
}
