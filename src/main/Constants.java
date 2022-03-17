package main;

public class Constants {
    
    public static final int ORIGINAL_TILE_SIZE = 32;
    public static final double TILE_SCALER = 1.5;
    public static final int DISPLAY_TILE_SIZE = (int)(TILE_SCALER * ORIGINAL_TILE_SIZE);
    public static final int TOTAL_SCREEN_COLLUMNS = 26;
    public static final int TOTAL_SCREEN_ROWS = 16;
    public static final int SCREEN_WIDTH = DISPLAY_TILE_SIZE * TOTAL_SCREEN_COLLUMNS;
    public static final int SCREEN_HEIGHT = DISPLAY_TILE_SIZE *TOTAL_SCREEN_ROWS;

    public static final int FPS = 60;
    public static final double NANOSECONDS_TO_SECONDS = 1000000000.00;
    public static final double DRAW_INTERVAL = NANOSECONDS_TO_SECONDS / FPS;

    public static final String LEFT_RELEASE_KEYBIND = "leftReleased";
    public static final String RIGHT_RELEASE_KEYBIND = "rightReleased";

    public static final double GRAVITAIONAL_CONSTANT = 9.8;
   
    private Constants() {
    }
}