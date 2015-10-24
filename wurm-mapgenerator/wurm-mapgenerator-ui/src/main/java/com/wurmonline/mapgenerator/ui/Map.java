package com.wurmonline.mapgenerator.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wurmonline.mapgenerator.core.context.AppModule;
import com.wurmonline.mesh.BushData.BushType;
import com.wurmonline.mesh.FoliageAge;
import com.wurmonline.mesh.GrassData.GrowthTreeStage;
import com.wurmonline.mesh.Tiles.Tile;
import com.wurmonline.mesh.TreeData.TreeType;
import com.wurmonline.wurmapi.api.MapData;
import com.wurmonline.wurmapi.api.WurmAPI;

@AppModule("none")
public class Map {

	public MapData data;
	public WurmAPI api;
	private Random random = new Random();
	
	public Map(String path, int size)
	{
		 try {
	            api = WurmAPI.create(path, size);
	            data = api.getMapData();
	        } catch (IOException ex) {
	            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
	        }
	}
	
	public void height(float[][] input, int scale)
	{
		for(int i=0; i<input.length; i++)
			for(int j=0; j< input.length; j++)
			{
				short height = (short)(input[i][j]*scale);
				short rockHeight = (short)(input[i][j]*scale -1);
				if(height < -300)
					height = -300;
				if(rockHeight < -301)
					rockHeight = -301;
				data.setSurfaceTile(i, j, Tile.TILE_GRASS, height);
				data.setRockHeight(i, j, rockHeight);
			}
	}
	
	public void terrain(float[][] input, Tile tile)
	{
		for(int i=0; i<input.length; i++)
			for(int j=0; j< input.length; j++)
				if(input[i][j] !=0)
					data.setSurfaceTile(i,j,tile,data.getSurfaceHeight(i, j));
	}
	
	public void pterrain(float[][] input, Tile tile)
	{
		for(int i=0; i<input.length; i++)
			for(int j=0; j< input.length; j++)
				if(input[i][j] > random.nextFloat())
					data.setSurfaceTile(i,j,tile,data.getSurfaceHeight(i, j));
	}
	
	public void rockHeight(float[][] input, int scale)
	{
		for(int i=0; i<input.length; i++)
			for(int j=0; j< input.length; j++)
				if(input[i][j] !=0)
				{
					short rockHeight = (short) (input[i][i]*scale);
					if(rockHeight < -301)
						rockHeight = -301;
					data.setRockHeight(i,j,rockHeight);
				}
	}
	
	public void tree(float[][] input, TreeType type, FoliageAge age, GrowthTreeStage stage)
	{
		for(int i=0; i<input.length; i++)
			for(int j=0; j< input.length; j++)
				if(input[i][j] !=0)
					data.setTree(i, j, type, age, stage);
	}
	
	public void ptree(float[][] input, TreeType type, FoliageAge age, GrowthTreeStage stage)
	{
		for(int i=0; i<input.length; i++)
			for(int j=0; j< input.length; j++)
				if(input[i][j] > random.nextFloat())
					data.setTree(i, j, type, age, stage);
	}
	
	public void setBush(float[][] input, BushType type, FoliageAge age, GrowthTreeStage stage)
	{
		for(int i=0; i<input.length; i++)
			for(int j=0; j< input.length; j++)
				if(input[i][j] !=0)
					data.setBush(i, j, type, age, stage);
	}
	
	public void save()
	{
		data.saveChanges();
	}
	
	public BufferedImage dump()
	{
		return data.createMapDump();
	}
}
