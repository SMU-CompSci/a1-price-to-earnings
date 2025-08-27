import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.student_work.BusinessEvaluation;
import org.junit.jupiter.api.Test;

public class BusinessEvaluationTest {

    @Test
    void sampleInteraction_producesExpectedMarketValues() {
        String input = String.join("\n",
                "200000",
                "230000",
                "250000",
                "300000",
                "325000",
                "6.0",
                "6.5",
                "6.75",
                "7.0",
                "7.25"
        ) + "\n";

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PrintStream originalOut = System.out;
        java.io.InputStream originalIn = System.in;
        try {
            System.setIn(in);
            System.setOut(new PrintStream(out, true));
            BusinessEvaluation.main(new String[0]);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }

        String actualAll = out.toString().replace("\r\n", "\n");

        // Find every "Earnings[<idx>] ... (end of line)" anywhere in the output
        Pattern p = Pattern.compile("Earnings\\[\\d+]\\s*=.*");
        Matcher m = p.matcher(actualAll);
        List<String> actualLines = new ArrayList<>();
        while (m.find()) {
            String line = m.group().trim().replaceAll("\\s+", " ");
            actualLines.add(line);
        }

        List<String> expectedLines = List.of(
                "Earnings[0] = 200000.0 Ratio[0] = 6.0 Market value[0] = 1200000.0",
                "Earnings[1] = 230000.0 Ratio[1] = 6.5 Market value[1] = 1495000.0",
                "Earnings[2] = 250000.0 Ratio[2] = 6.75 Market value[2] = 1687500.0",
                "Earnings[3] = 300000.0 Ratio[3] = 7.0 Market value[3] = 2100000.0",
                "Earnings[4] = 325000.0 Ratio[4] = 7.25 Market value[4] = 2356250.0"
        );

        // Compare exactly 5 captured lines and exact order/content
        assertEquals(expectedLines, actualLines,
                "\n--- Full program output ---\n" + actualAll + "\n");
    }
}
