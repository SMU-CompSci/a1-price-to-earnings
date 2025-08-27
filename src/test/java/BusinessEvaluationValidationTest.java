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

public class BusinessEvaluationValidationTest {

    @Test
    void reprompts_until_valid_inputs_then_produces_expected_results() {
        // First earning is invalid (too low), then valid. Others valid.
        // First ratio is invalid (too high), then valid. Others valid.
        String input = String.join("\n",
                // Earnings (5 total): invalid, then valid, then 3 more valid
                "199999",   // invalid (below 200,000) -> should reprompt
                "200000",   // valid
                "230000",
                "250000",
                "300000",
                "325000",
                // Ratios (5 total): invalid, then valid, then 3 more valid
                "10.01",    // invalid (above 10.0) -> should reprompt
                "6.0",      // valid
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

        String all = out.toString().replace("\r\n", "\n");

        // --- Verify re-prompt counts ---
        String E_PROMPT = "Enter a value of earnings (200,000 - 500,000) :";
        String R_PROMPT = "Enter a value of ratio (0.0 - 10.0) :";

        int earningsPromptCount = countOccurrences(all, E_PROMPT);
        int ratioPromptCount    = countOccurrences(all, R_PROMPT);

        // Expect 6 prompts each: 5 valid entries + 1 extra due to the invalid attempt
        assertEquals(6, earningsPromptCount, "\nFull output:\n" + all);
        assertEquals(6, ratioPromptCount,    "\nFull output:\n" + all);

        // --- Verify final computed lines ---
        Pattern p = Pattern.compile("Earnings\\[\\d+]\\s*=.*");
        Matcher m = p.matcher(all);
        List<String> actualLines = new ArrayList<>();
        while (m.find()) {
            actualLines.add(m.group().trim().replaceAll("\\s+", " "));
        }

        List<String> expectedLines = List.of(
                "Earnings[0] = 200000.0 Ratio[0] = 6.0 Market value[0] = 1200000.0",
                "Earnings[1] = 230000.0 Ratio[1] = 6.5 Market value[1] = 1495000.0",
                "Earnings[2] = 250000.0 Ratio[2] = 6.75 Market value[2] = 1687500.0",
                "Earnings[3] = 300000.0 Ratio[3] = 7.0 Market value[3] = 2100000.0",
                "Earnings[4] = 325000.0 Ratio[4] = 7.25 Market value[4] = 2356250.0"
        );

        assertEquals(expectedLines, actualLines, "\nFull output:\n" + all);
    }

    private static int countOccurrences(String haystack, String needle) {
        int count = 0, from = 0;
        while (true) {
            int idx = haystack.indexOf(needle, from);
            if (idx < 0) break;
            count++;
            from = idx + needle.length();
        }
        return count;
    }
}
