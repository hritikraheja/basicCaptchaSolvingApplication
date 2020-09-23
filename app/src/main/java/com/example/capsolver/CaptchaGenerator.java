import java.util.Scanner;

public class CaptchaGenerator {
    static int lengthOfCaptcha = 6;

    public static void main(String[] args) {
        System.out.println("Enter the length of Captcha To Be Generated : ");
        Scanner scanner = new Scanner(System.in);
        int lengthOfCaptcha = scanner.nextInt();
        scanner.close();
        System.out.println("The Generated Captcha Code Is : " + generateCaptcha(lengthOfCaptcha));
    }

    public static String generateCaptcha(int captchaLength) {
        String captcha = "";
        char[] allCharacters = new char[62];
        for (int characterAscii = 65; characterAscii < 91; characterAscii++) {
            allCharacters[characterAscii - 65] = (char) characterAscii;
        }
        for (int characterAscii = 97; characterAscii < 123; characterAscii++) {
            allCharacters[26 + characterAscii - 97] = (char) characterAscii;
        }
        for (int number = 0; number < 10; number++) {
            allCharacters[52 + number] = String.valueOf(number).charAt(0);
        }
        do {
            int randomCharacterIndex = (int) (Math.random() * 100);
            if (randomCharacterIndex < 62) {
                captcha += allCharacters[randomCharacterIndex];
            }
        }while (captcha.length() < captchaLength);
        return (captcha);
    }
}