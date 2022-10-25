package populatePhoneNumber;
import java.util.Random;
public class PopulatePhoneNumber {
    public String generatePhoneNumber() {
        Random random = new Random();
        Integer randomMobile;
        String number,formattedNumber;
        Integer secNumber;
        //Math.random() * (max - min + 1) + min
        randomMobile = random.nextInt(10000000) + 9999999;
        secNumber = random.nextInt(8 - 6 + 1) + 6;
        number = "0" +secNumber+ randomMobile.toString();
        /** Break the string to 3 groups using regular expression '(\\d{3})(\\d{3})(\\d+)'.
         First group contains 3 digits. Second contains 3 digits and last group contains all remaining digits.
         Create a formatted string using these groups in '$1 $2 $3' pattern. **/
        formattedNumber = number.replaceFirst ("(\\d{3})(\\d{3})(\\d+)", "$1 $2 $3");
        return formattedNumber;
    }
}
