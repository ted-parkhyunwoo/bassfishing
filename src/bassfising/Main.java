package bassfising;
import bassfising.utility.LineConverter;
import bassfising.utility.ReelLineCapa;
import bassfising.utility.SinkerConverter;

public class Main {
    static String lb = "lb";
    static String ho = "ho";
    static String g = "g";

    public static void main(String[] args) {
        // 싱커 호수<->gram 상호 변환기
        SinkerConverter.convertPrint(3, ho);        // 11.25g
        SinkerConverter.convertPrint(14, g);        // 3.73ho

        // 라인 호수<->lb 상호 변환기
        // 4호를 lb로 바꾸면            ->          16.0
        System.out.println(LineConverter.convert(4, ho, lb));
        // printConvert 사용 12파운드를 호로 바꾸면       ->          3.0
        LineConverter.printConvert(12, lb, ho);

        // 릴 기본제원 권사량을 베이스로 다른라인 권사량 추정하기.
        // myZillion21 은 14lb 80m 감기는 릴.
        ReelLineCapa myZillion21 = new ReelLineCapa(14, lb, 80);
        // myZillion21의 12lb는 몇미터 감기는지 확인
        double res = myZillion21.getOtherLineCapa(12, lb);
        System.out.println(res);                                    // 93.33...

    }
}
