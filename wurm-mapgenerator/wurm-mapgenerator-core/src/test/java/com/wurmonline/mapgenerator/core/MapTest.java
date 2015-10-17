package com.wurmonline.mapgenerator.core;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import com.wurmonline.mapgenerator.core.filter.Filter;
import com.wurmonline.mapgenerator.core.filter.SquareVignetteFilter;
import com.wurmonline.mapgenerator.core.interpolator.BicubicInterpolator;
import com.wurmonline.mapgenerator.core.interpolator.BilinearInterpolator;
import com.wurmonline.mapgenerator.core.noise.NoiseGenerator;
import com.wurmonline.mapgenerator.core.noise.PerlinNoiseGenerator;
import com.wurmonline.mapgenerator.core.util.MathUtils;
import com.wurmonline.mesh.FoliageAge;
import com.wurmonline.mesh.GrassData;
import com.wurmonline.mesh.TreeData;
import com.wurmonline.mesh.Tiles.Tile;
import com.wurmonline.wurmapi.api.MapData;
import com.wurmonline.wurmapi.api.WurmAPI;

public class MapTest {

	public static void main(String... args)
	{
		MathUtils utils = new MathUtils();
		NoiseGenerator gen = new PerlinNoiseGenerator(0.5f,8,0);
		float[][] noise = gen.generate(512);
		float[][] trees = gen.generate(1024);
		//BicubicInterpolator inter = new BicubicInterpolator(8,32);
		BilinearInterpolator inter = new BilinearInterpolator();
		noise = inter.interpolate(noise,2);
		Filter f = new SquareVignetteFilter();
		f.filter(noise);
		
		float[][] grads = utils.generateGradientMap(noise);
		
		float[][] rock = gen.generate(1024);
		
		 WurmAPI api;
	        try {
	            api = WurmAPI.create("Creative", 10);
	            
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
	                
	                    map.setRockHeight(i, i2, (short) (-200));

	                    int height = (int) (noise[i][i2]*8000-3000);
	                    if(height > 20){
	                    		map.setSurfaceTile(i, i2, Tile.TILE_GRASS, (short)height);
	                    		if(trees[i][i2] >0.5f){
	                    			 int rnd = (int) (random.nextInt(100) * trees[i][i2]);
	                    			 if(rnd > 30)
	                    			 map.setTree(i, i2, TreeData.TreeType.CEDAR, FoliageAge.OLD_ONE, GrassData.GrowthTreeStage.MEDIUM);
	                    		}
	                    		else{
	                    			 int rnd = (int) (random.nextInt(100) * trees[i][i2]);
	                    			 if(rnd < 60)
	                    				 map.setSurfaceTile(i, i2, Tile.TILE_SAND, (short)height);
	                    		     //map.setTree(i, i2, TreeData.TreeType.CEDAR, FoliageAge.OLD_ONE, GrassData.GrowthTreeStage.MEDIUM);
	                    		}
	                    	    if(grads[i][i2] > 0.001f)
	                    		{
	                    			map.setRockHeight(i, i2, (short) (height+1));
	                    			map.setSurfaceTile(i, i2, Tile.TILE_ROCK, (short)height);
	                    		}
	                    }
	                    else if(height >0 && height <20)
	                    {
	                    	map.setSurfaceTile(i, i2, Tile.TILE_SAND, (short)height);
	                    }
	                    else
	                    {
	                    	if(height < -200)
	                    		height = -100;
	                    	
	                    	map.setSurfaceTile(i, i2, Tile.TILE_DIRT, (short)height);
	                    }
	                    
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
