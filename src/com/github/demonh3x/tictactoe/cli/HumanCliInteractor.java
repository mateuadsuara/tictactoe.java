package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.GameInteractor;
import com.github.demonh3x.tictactoe.GameState;
import com.github.demonh3x.tictactoe.Location;
import com.github.demonh3x.tictactoe.Renderer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanCliInteractor implements GameInteractor {
    private final Renderer<String> renderer;
    private final BufferedReader input;

    public HumanCliInteractor(Renderer<String> renderer){
        this.renderer = renderer;
        input = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public Location play(GameState state) {
        print("Your turn!");
        print(renderer.render(state));
        print("Where do you play?");

        return askForALocation();
    }

    private void print(String message){
        System.out.println(message);
    }

    private Location askForALocation() {
        try {
            return readLocation();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Location readLocation() throws IOException {
        print("Location format: \"x,y\" (without the quotes)");

        final String line = input.readLine();
        final String[] parts = line.split(",");
        final int x = Integer.parseInt(parts[0]);
        final int y = Integer.parseInt(parts[1]);

        return new Location(x, y);
    }
}
