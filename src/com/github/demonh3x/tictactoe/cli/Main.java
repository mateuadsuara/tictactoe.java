package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.GameState;
import com.github.demonh3x.tictactoe.Location;
import com.github.demonh3x.tictactoe.Piece;
import com.github.demonh3x.tictactoe.Player;

public class Main {
    public static void main(String ... args){
        final Player xPlayer = new Player();
        final Player oPlayer = new Player();
        final GameState gameState = GameState.empty()
                .put(new Piece(xPlayer), new Location(0, 0))
                .put(new Piece(oPlayer), new Location(1, 0));

        final String rendered = new TextRenderer(xPlayer, oPlayer, gameState).render();
        System.out.println(rendered);
    }
}
