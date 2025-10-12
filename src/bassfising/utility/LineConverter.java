package bassfising.utility;

public class LineConverter {
    public static double convert(double power, String input_type) {
        String ho = "ho";
        String lb = "lb";
        if (input_type != ho && input_type != lb) {
            System.out.println("input_type Error.");
            return -1;
        }

        if (input_type == ho) return power * 4;
        else return power / 4;
    }

    public static void printConvert(float power, String input_type) {
        System.out.println(power + input_type + ": " + convert(power, input_type) + (input_type == "lb"? "호": "lb" ));
    }

}
