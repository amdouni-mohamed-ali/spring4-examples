package org.spring.tutorial.examples.core.beans;

import org.spring.tutorial.examples.core.wrapper.PropertiesWrapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
 
public class AppMain {
 
    public static void main(String args[]) {
        
    	AbstractApplicationContext context = new AnnotationConfigApplicationContext(
    			ConfigClass.class,
    			AnotherConfigClass.class
    			);
        PropertiesWrapper propertiesWrapper = (PropertiesWrapper) context.getBean("propertiesWrapper");
        propertiesWrapper.showProperties();
        context.destroy();
        
    }
 
}
