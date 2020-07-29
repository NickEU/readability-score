package readability;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {
    public static Double truncDouble(double toBeTruncated) {
        return BigDecimal.valueOf(toBeTruncated)
                .setScale(2, RoundingMode.FLOOR).doubleValue();
    }
}
