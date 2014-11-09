package com.github.demonh3x.tictactoe.game;

public class Play {
    public final Player player;
    public final Location location;

    public Play(Player player, Location location){
        this.player = player;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Play play = (Play) o;

        if (location != null ? !location.equals(play.location) : play.location != null) return false;
        if (player != null ? !player.equals(play.player) : play.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }
}
