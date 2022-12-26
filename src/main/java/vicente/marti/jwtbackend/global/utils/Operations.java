package vicente.marti.jwtbackend.global.utils;

import java.util.Comparator;
import java.util.List;

public class Operations {

    public static String trimBrackets(String message) {
        return message.replaceAll("[\\[\\]]", "");
    }

}
