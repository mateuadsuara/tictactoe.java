package com.github.demonh3x.tictactoe.cli;

import java.io.*;
import java.util.List;

public class Asker {
    private final PrintStream output;
    private final InputStream input;

    public Asker(PrintStream output, InputStream input){
        this.output = output;
        this.input = input;
    }

    public String askForOneOption(String question, List<String> options){
        output.printf("%s (options: %s)\n", question, join("/", options));

        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
        try {
            String answer;
            boolean isAnswerValid;
            do {
                answer = bufferedReader.readLine().trim();
                isAnswerValid = options.contains(answer);

                if (!isAnswerValid){
                    output.printf("[%s] is not a valid option! Try again.\n", answer);
                }
            } while (!isAnswerValid);

            return answer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String join(String separator, List<String> values) {
        String joined = values.isEmpty()? "": values.get(0);

        for (int index = 1; index < values.size(); index++){
            joined += separator + values.get(index);
        }

        return joined;
    }
}
