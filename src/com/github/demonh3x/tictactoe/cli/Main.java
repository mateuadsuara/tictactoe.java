package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.GameState;
import com.github.demonh3x.tictactoe.Piece;
import com.github.demonh3x.tictactoe.Player;

import java.util.Arrays;

public class Main {
    public static void main(String ... args){
        final Player xPlayer = new Player();
        final Player oPlayer = new Player();
        final GameState gameState = new GameState(
                Arrays.<Piece>asList(
                        new Piece(xPlayer), new Piece(oPlayer), null, null, null, null, null, null, null
                )
        );

        final String rendered = new TextRenderer(xPlayer, oPlayer, gameState).render();
        System.out.println(rendered);
    }
}
