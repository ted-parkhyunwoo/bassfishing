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
        System.out.println(Sinker.convert(3, ho));     // 11.25
        Sinker.printConvert(14, g);                    // 14.0g: 3.73ho

        // 라인 호수<->lb 상호 변환기
        System.out.println(Line.convert(4, ho));       // 16.0
        Line.printConvert(12, lb);                     // 12.0lb: 3.0호

        // 릴 기본제원 권사량을 베이스로 다른파워의 라인 권사량 추정.
        // myZillion21 은 14lb 80m 감기는 릴.
        ReelLineCapa myZillion21 = new ReelLineCapa(14, lb, 80);
        // myZillion21의 12lb는 몇미터 감기는지 확인
        double res = myZillion21.getOtherLineCapa(12, lb);
        System.out.println(res);                                // 93.33333...

    }
}
