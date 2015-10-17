package com.wurmonline.mapgenerator.ui;

import java.awt.EventQueue;
import java.util.Set;
import java.util.logging.Logger;

import javax.swing.JFrame;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.wurmonline.mapgenerator.core.context.AppModule;
import com.wurmonline.mapgenerator.ui.ui.Window;

public class Application {

	public static final Logger log = Logger.getLogger(Application.class.getName());

	public static Window window;
	public static Interpreter interpreter = new Interpreter();

	public static void main(String[] args) {

		scanComponents("com.wurmonline.mapgenerator.core.noise");
		scanComponents("com.wurmonline.mapgenerator.core.interpolator");
		scanComponents("com.wurmonline.mapgenerator.core.util");
		scanComponents("com.wurmonline.mapgenerator.core.filter");
		scanComponents("com.wurmonline.mapgenerator.ui");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Window(interpreter);
					window.getFrame().setTitle("Wurm MapGenerator");
					window.getFrame().setExtendedState(JFrame.MAXIMIZED_BOTH); 
					window.getFrame().setVisible(true);
					interpreter.init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void scanComponents(String packageName) {

		String simplePackageName = handlePackage(packageName);
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(true);
		String basePackage = packageName.replace("\\.", "/");
		provider.addExcludeFilter(new AnnotationTypeFilter(AppModule.class, true));
		Set<BeanDefinition> filteredComponents = provider.findCandidateComponents(basePackage);
		log.info("No of components :" + filteredComponents.size());

		for (BeanDefinition component : filteredComponents) {
			handleComponent(component, simplePackageName, packageName);
		}

		provider.resetFilters(true);
		provider.addIncludeFilter(new AnnotationTypeFilter(AppModule.class, true));
		filteredComponents = provider.findCandidateComponents(basePackage);
		log.info("No of components :" + filteredComponents.size());

		for (BeanDefinition component : filteredComponents) {
			handleComponent(component, simplePackageName, packageName);
		}
	}

	public static void handleComponent(BeanDefinition component, String simplePackage, String extendedPackage) {
		try {
			Class<?> c = Class.forName(component.getBeanClassName());
			AppModule annotation = c.getAnnotation(AppModule.class);
			String name = annotation.value();
			String executionResult = interpreter.exec("from " + extendedPackage + " import " + c.getSimpleName());
			if (executionResult.length() > 1)
				log.info(executionResult);
			if (name.equals("none"))
				return;
			executionResult = interpreter.exec(simplePackage + "." + name + " = " + c.getSimpleName() + "()");
			if (executionResult.length() > 1)
				log.info(executionResult);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String handlePackage(String packageName) {
		String[] parts = packageName.split("\\.");
		String name = parts[parts.length - 1];
		String className = "class" + name;
		String executionResult = interpreter.exec("class " + className + "(): pass");
		if (executionResult.length() > 1)
			log.info(executionResult);
		executionResult = interpreter.exec(name + " = " + className + "()");
		if (executionResult.length() > 1)
			log.info(executionResult);
		return name;
	}
}
