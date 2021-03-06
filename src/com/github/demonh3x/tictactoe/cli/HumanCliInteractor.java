package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.game.*;

import java.io.*;

public class HumanCliInteractor implements Interactor {
    private final BufferedReader input;
    private final PrintStream output;

    public HumanCliInteractor(PrintStream output, InputStream input){
        this.output = output;
        this.input = new BufferedReader(new InputStreamReader(input));
    }

    @Override
    public Location choose(State state) {
        print("Your turn! Where do you play?");

        return askForAValidLocation(state);
    }

    private void print(String message){
        output.println(message);
    }

    private Location askForAValidLocation(State state) {
        print("Location format: \"col,row\" (without the quotes)");

        Location location;
        boolean isValid;
        do {
            location = askForALocation();
            isValid = isValid(location, state);

            if (!isValid)
                print("That location is not valid! Please, try another one.");
        } while (!isValid);

        return location;
    }

    private boolean isValid(Location location, State state) {
        return state.board.contains(location) && state.lookAt(location) == null;
    }

    private Location askForALocation() {
        try {
            return readLocation();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Location readLocation() throws IOException {
        String line = null;
        Location l = null;

        do {
            try {
                line = readLine();
                l = parseLocation(line);
            } catch (IllegalArgumentException e){
                print(String.format("Can't understant \"%s\". Does it meet the Location format? Please, try again.", line));
            }
        } while (l == null);

        return l;
    }

    private String readLine() throws IOException {
        String line = input.readLine();

        if (line == null)
            throw new IOException("End of the stream reached!");

        return line;
    }

    private Location parseLocation(String line) throws IllegalArgumentException {
        final int col, row;

        try {
            final String[] parts = line.split(",");
            col = Integer.parseInt(parts[0].trim());
            row = Integer.parseInt(parts[1].trim());
        } catch (Throwable t){
            throw new IllegalArgumentException(t);
        }

        return new Location(col, row);
    }
}
