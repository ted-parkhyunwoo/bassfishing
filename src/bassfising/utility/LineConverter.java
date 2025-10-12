package bassfising.utility;

public class LineConverter {
    public static double convert(double power, String input_type, String return_type) {
        String ho = "ho";
        String lb = "lb";
        if (input_type != ho && input_type != lb) {
            System.out.println("input_type Error.");
            return -1;
        }

        if (return_type != ho && return_type != lb) {
            System.out.println("return_type Error.");
            return -1;
        }

        if (input_type == return_type) {
            System.out.println("[WARNING] return_type is input_type.");
            return power;
        }

        if (input_type == ho) return power * 4;
        else return power / 4;
    }

    public static void printConvert(float power, String input_type, String return_type) {
        System.out.println(power + input_type + ": " + convert(power, input_type, return_type) + (return_type == "ho"? "호": "lb" ));
    }

}
