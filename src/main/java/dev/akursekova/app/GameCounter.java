package dev.akursekova.app;

public class GameCounter {
    private static int gameCount = 0;

public static void increaseGameCount(){
    gameCount = gameCount+1;
    //System.out.println(gameCount);
}

public static int getGameCount(){
    return gameCount;
}
}
