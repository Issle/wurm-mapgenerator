package com.wurmonline.mapgenerator.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wurmonline.mapgenerator.core.context.AppModule;
import com.wurmonline.mesh.Tiles.Tile;
import com.wurmonline.wurmapi.api.MapData;
import com.wurmonline.wurmapi.api.WurmAPI;

@AppModule("none")
public class Map {

	public MapData data;
	public WurmAPI api;
	
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
	
	public void save()
	{
		data.saveChanges();
	}
	
	public BufferedImage dump()
	{
		return data.createMapDump();
	}
}
