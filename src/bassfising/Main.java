package bassfising;
import bassfising.utility.ReelLineCapa;
import bassfising.utility.converter.Line;
import bassfising.utility.converter.Sinker;

public class Main {
    static String lb = "lb";
    static String ho = "ho";
    static String g = "g";

    public static void main(String[] args) {
        // 싱커 호수<->gram 상호 변환기
        System.out.println(Sinker.convert(3, ho));     // 11.25 (double)
        Sinker.printConvert(14, g);                    // 14.0g: 3.73호
        Sinker.printConvert(5, ho);                    // 5.0ho: 18.75g

        // 라인 호수<->lb 상호 변환기
        System.out.println(Line.convert(4, ho));       // 16.0  (double)
        Line.printConvert(12, lb);                     // 12.0lb: 3.0호

        // 릴 기본제원 권사량을 베이스로 다른파워의 라인 권사량 추정.
        // myZillion21 은 14lb 80m 감기는 릴.
        ReelLineCapa myZillion21 = new ReelLineCapa(14, lb, 90);
        // myZillion21의 12lb는 몇미터 감기는지 확인
        double res = myZillion21.getOtherLineCapa(12, lb);
        System.out.println(res);                        // 93.33333...
        // 쉬운출력
        myZillion21.printOtherLine(16, lb);             // 16.0lb 70.0m

        // 이름변경: 기본생성-"empty"
        myZillion21.setName("21질리언");
        // 자주쓰는 라인 4lb~20lb 모두 출력
        myZillion21.printOtherLine();
        /* print.
            21질리언:
              4.0lb 315.00m  오차주의: 기본(14.00lb)보다 6lb 이상 차이.
              6.0lb 210.00m  오차주의: 기본(14.00lb)보다 6lb 이상 차이.
              8.0lb 157.50m  오차주의: 기본(14.00lb)보다 6lb 이상 차이.
              10.0lb 126.00m
              12.0lb 105.00m
              14.0lb 90.00m (기본값)
              16.0lb 78.75m
              18.0lb 70.00m
              20.0lb 63.00m  오차주의: 기본(14.00lb)보다 6lb 이상 차이.
         */

    }
}
