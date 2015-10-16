package com.wurmonline.mapgenerator.core;

import com.wurmonline.mapgenerator.core.noise.PerlinNoiseGenerator;
import com.wurmonline.mapgenerator.core.util.MathUtils;

public class Main {

	public static void main(String... args)
	{
		MathUtils utils = new MathUtils();
		PerlinNoiseGenerator gen = new PerlinNoiseGenerator(0.6f,8,256);
		float[][] map = gen.generate(512,512);
		float[][] normMap = utils.normalize(map);
	    float[][] gradients = utils.normalize(utils.generateGradientMap(normMap));
	    //Map mapObject = new Map(map,gradients);
		//mapObject.render();
	    //Game.render(map, "map");
	    Game.render(normMap,"normMap");
	    Game.render(gradients, "gradients");
	}	
}
