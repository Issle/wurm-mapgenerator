package com.wurmonline.mapgenerator.ui;

import java.awt.EventQueue;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import com.wurmonline.mapgenerator.core.context.AppModule;
import com.wurmonline.mapgenerator.ui.ui.Window;

public class Application {

	public static Window window;
	public static Interpreter interpreter = new Interpreter();

	public static void main(String[] args) {

		  scanComponents("com.wurmonline.mapgenerator.core.noise");
		  scanComponents("com.wurmonline.mapgenerator.core.interpolator");
		  scanComponents("com.wurmonline.mapgenerator.core.util");
		  scanComponents("com.wurmonline.mapgenerator.core.filter");
		  scanComponents("com.wurmonline.mapgenerator.ui");
		  EventQueue.invokeLater(new Runnable() { public void run() { try {
		  window = new Window(interpreter); window.getFrame().setTitle(
		  "Wurm MapGenerator"); window.getFrame().setVisible(true);
		  interpreter.init(); } catch (Exception e) { e.printStackTrace(); } }
		  });
	}

	public static void scanComponents(String packageName) {

		String simplePackageName = handlePackage(packageName);
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
		String basePackage = packageName.replace("\\.", "/");
		provider.addExcludeFilter(new AnnotationTypeFilter(AppModule.class, true));
		Set<BeanDefinition> filteredComponents = provider.findCandidateComponents(basePackage);
		System.out.println("No of components :" + filteredComponents.size());

		for (BeanDefinition component : filteredComponents) {
			handleComponent(component,simplePackageName,packageName);
		}

		provider.resetFilters(true);
		provider.addIncludeFilter(new AnnotationTypeFilter(AppModule.class, true));
		filteredComponents = provider.findCandidateComponents(basePackage);
		System.out.println("No of components :" + filteredComponents.size());

		for (BeanDefinition component : filteredComponents) {
			handleComponent(component,simplePackageName, packageName);
		}
	}
	
	public static void handleComponent(BeanDefinition component, String simplePackage, String extendedPackage)
	{
		try {
			Class<?> c = Class.forName(component.getBeanClassName());
			AppModule annotation = c.getAnnotation(AppModule.class);
			String name = annotation.value();
			System.out.println(interpreter.exec("from "+extendedPackage+" import "+ c.getSimpleName()));
			if(name.equals("none"))
				return;
			System.out.println(interpreter.exec(simplePackage+"."+name+" = "+ c.getSimpleName()+"()"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String handlePackage(String packageName)
	{
		String[] parts = packageName.split("\\.");
		String name = parts[parts.length-1];
		String className = "class"+name;
		System.out.println(interpreter.exec("class "+className+"(): pass"));
		System.out.println(interpreter.exec(name+" = "+className+"()"));
		return name;
	}
}
