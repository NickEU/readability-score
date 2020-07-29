package readability;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static java.util.Map.entry;

public class Util {
    public final static Map<Integer, Integer> scoresToAges = Map.ofEntries(
            entry(1, 6),
            entry(2, 7),
            entry(3, 9),
            entry(4, 10),
            entry(5, 11),
            entry(6, 12),
            entry(7, 13),
            entry(8, 14),
            entry(9, 15),
            entry(10, 16),
            entry(11, 17),
            entry(12, 18),
            entry(13, 24),
            entry(14, 25)
    );
    public static Double truncDouble(double toBeTruncated) {
        return BigDecimal.valueOf(toBeTruncated)
                .setScale(2, RoundingMode.FLOOR).doubleValue();
    }
}
