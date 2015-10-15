package com.wurmonline.wurmapi.api;

import com.wurmonline.mesh.FoliageAge;
import com.wurmonline.mesh.GrassData;
import com.wurmonline.mesh.Tiles.Tile;
import com.wurmonline.mesh.TreeData;
import com.wurmonline.wurmapi.api.MapData;
import com.wurmonline.wurmapi.api.WurmAPI;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class MapTest {
    
    public static void main(String[] args) {
        WurmAPI api;
        try {
            api = WurmAPI.create("Creative", 11);
        } catch (IOException ex) {
            Logger.getLogger(MapTest.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
        MapData map = api.getMapData();
        
        int halfWidth = map.getWidth() / 2;
        int halfHeight = map.getHeight() / 2;
        Random random = new Random();
        for (int i = 0; i < map.getWidth(); i++) {
            for (int i2 = 0; i2 < map.getHeight(); i2++) {
                int distToEdge = Math.min(halfWidth - Math.abs(i - halfWidth), halfHeight - Math.abs(i2 - halfHeight));
                if (distToEdge > 10) {
                    map.setRockHeight(i, i2, (short) (95));

                    map.setSurfaceTile(i, i2, Tile.TILE_GRASS, (short)random.nextInt(700));
                    map.setTree(i, i2, TreeData.TreeType.CEDAR, FoliageAge.OLD_ONE, GrassData.GrowthTreeStage.MEDIUM);
                }
                else {
                    map.setRockHeight(i, i2, (short) (-200));
                    map.setSurfaceTile(i, i2, Tile.TILE_DIRT, (short) (-100));
                }
                map.setCaveTile(i, i2, Tile.TILE_CAVE_WALL);
            }
        }
        
        map.saveChanges();
        try {
            ImageIO.write(map.createMapDump(), "png", new File("map.png"));
            ImageIO.write(map.createTopographicDump(true, (short) 250), "png", new File("topography.png"));
        } catch (IOException ex) {
            Logger.getLogger(MapTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        api.close();
    }
    
}