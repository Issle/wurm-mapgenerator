package com.wurmonline.mapgenerator.core;

import java.awt.*;

/**
 * Created by ipapaste on 9/29/15.
 */
public class Terrain {

    private int width;
    private int height;

    private int[][] altitudes;
    private int[][] colors;
    private int[][] grads;

    public Terrain(int width, int height)
    {
        altitudes = new int[width][height];
        colors = new int[width][height];
        grads = new int[width][height];
        this.width = width;
        this.height = height;
    }
    
    public void setAltitudes(int[][] altitudes)
    {
    	this.altitudes = altitudes;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAltitude(int x, int y)
    {
        return altitudes[x][y];
    }

    public void setAltitude(int x, int y, int value)
    {
        altitudes[x][y] = value;
    }

    public int getColor(int x, int y)
    {
        return colors[x][y];
    }

    public void setColor(int x, int y, int color)
    {
        colors[x][y] = color;
    }

    public void alter(int x, int y, int value)
    {
        Color c = new Color(getColor(x,y));
        int adjustment = 0;
        if(value <0)
        	adjustment = -100;
        else
        	adjustment = 30;
        int r = c.getRed() +value*4 + adjustment;
        int g = c.getGreen() + value*4 + adjustment;
        int b = c.getBlue() + value*4 + adjustment;
        if(r < 0)
            r = 0;
        if(r > 255)
            r = 255;

        if(g < 0 )
            g = 0;
        if(g > 255)
            g = 255;
        
        if(b<0)
        	b=0;
        if(b>255)
        	b=255;
        

        setColor(x,y, new Color(r,g,b).getRGB());
    }

    public int[][] getColors()
    {
        return colors;
    }

    public int[][] getAltitudes()
    {
        return altitudes;
    }

    public int getGrad(int x, int y)
    {
        return grads[x][y];
    }

    public void calculateGrads()
    {
        for(int i =1; i < getWidth()-1; i++)
            for(int j =1; j < getHeight()-1; j++)
            {
                grads[i][j] = 2* getAltitude(i,j)- getAltitude(i,j-1) - getAltitude(i-1,j);
            }
    }
}
