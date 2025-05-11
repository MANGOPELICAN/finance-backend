package util;

import java.io.Console;
import java.util.Scanner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateBcrypt {

    public static void main(String[] args) {
        Console console = System.console();
        String plain;

        if (console != null) {
            char[] pwd = console.readPassword("Plain password: ");
            plain = new String(pwd);
        } else {
            // VS Code / IDE fallback
            try (Scanner in = new Scanner(System.in)) {   // <─ auto-close
                System.out.print("Plain password (echoed – IDE mode): ");
                plain = in.nextLine();
            }
        }

        String hash = new BCryptPasswordEncoder(12).encode(plain);

        System.out.println("\nAdd this line to application.properties:");
        System.out.println("spring.security.user.password={bcrypt}" + hash);
    }
}
