package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import main.Constants;
import main.GameMotor;

public class TileManager {

    GameMotor gM;
    Tile[] tile;
    int[][] mapTileNum;

    public int getMapTileNum(int i1, int i2) {
        return mapTileNum[i1][i2]; 
    }

    public Tile getTile(int index) {
        return tile[index];
    }

    public TileManager(GameMotor gM) {

        this.gM = gM;
        tile = new Tile[10];
        mapTileNum = new int[Constants.TOTAL_ROOM_COLLUMNS] [Constants.TOTAL_ROOM_ROWS];
        getTileImage();
        loadMap("/Res/maps/testMap.txt");

    }

    public void getTileImage() {

        try {
            tile[0] = new Tile();
            tile[0].setImage(ImageIO.read(getClass().getResourceAsStream("/Res/tiles/background.png")));
            tile[0].setCollision(false);

            tile[1] = new Tile();
            tile[1].setImage(ImageIO.read(getClass().getResourceAsStream("/Res/tiles/entrance.png")));
            tile[1].setCollision(false);

            tile[2] = new Tile();
            tile[2].setImage(ImageIO.read(getClass().getResourceAsStream("/Res/tiles/floor.png")));
            tile[2].setCollision(true);

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {

        try {
            //Gets map data
            InputStream mapInput = getClass().getResourceAsStream(filePath);
            BufferedReader mapReader = new BufferedReader(new InputStreamReader(mapInput));

            int col = 0;
            int row = 0;

            while(col < Constants.TOTAL_ROOM_COLLUMNS && row < Constants.TOTAL_ROOM_ROWS) {

                String line = mapReader.readLine();
                String[] splitCol = line.split(" ");

                //*************************************************************************************//
                // This section takes the map data form the "line" string.  It then splits the string  //
                // at each space and converts each new string to an integer variable, storing them in  //
                // the "stringToNum" integer variable.  Finally, the integer is passed to the          //
                // "mapTileNum" array.  It then moves to the next column and repeats the process until //
                // it reaches the final column.                                                        //
                //*************************************************************************************//

                while(col < Constants.TOTAL_ROOM_COLLUMNS) {

                    
                    int stringToNum = Integer.parseInt(splitCol[col]);
                    mapTileNum[col][row] = stringToNum;
                    col++;

                }

                //Goes to the next row once the column is finished and starts the process again at the first column
                if(col == Constants.TOTAL_ROOM_COLLUMNS) {

                    col = 0;
                    row++;
                }
            }
            mapReader.close();
        } catch (IOException e) {
            System.out.println("failed to load");
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < Constants.TOTAL_ROOM_COLLUMNS && worldRow < Constants.TOTAL_ROOM_ROWS) {

            int tileNum = mapTileNum[worldCol] [worldRow];

            //Determines what part of the world is being drawn
            int worldX = worldCol * Constants.DISPLAY_TILE_SIZE;
            int worldY = worldRow * Constants.DISPLAY_TILE_SIZE;

            int screenX = worldX - Constants.DISPLAY_TILE_SIZE;
            int screenY = worldY - Constants.DISPLAY_TILE_SIZE;

            
            g2.drawImage(tile[tileNum].getImage(), screenX, screenY, Constants.DISPLAY_TILE_SIZE, Constants.DISPLAY_TILE_SIZE, null);
            worldCol++;

            if(worldCol == Constants.TOTAL_ROOM_COLLUMNS) {
                worldCol = 0;
                worldRow++;
            }

        }

        

    }
}
