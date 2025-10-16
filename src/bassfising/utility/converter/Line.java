package bassfising.utility.converter;

public class Line {
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
}
