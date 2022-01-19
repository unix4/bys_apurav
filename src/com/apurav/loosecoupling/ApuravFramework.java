package com.apurav.loosecoupling;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Properties;

/**
 * 
 * @author apuravchauhan
 *
 */
public class ApuravFramework {
    public static HashMap<String, Object> beanRegistry;
    
    static {
        Properties properties = new Properties();
        try {
            properties.load(Main.class.getResourceAsStream("dependencies.properties"));
            beanRegistry = new HashMap<String, Object>();
            for (Object key : properties.keySet()) {
                String keyName = key.toString();
                String val = properties.getProperty(keyName);
                if (keyName.startsWith("object.")) {
                    String beanName = keyName.replace("object.", "");
                    Class clz = Class.forName(val);
                    beanRegistry.put(beanName, clz.getDeclaredConstructor().newInstance());
                } else {
                	// val = provider

                    String[] keys = keyName.split("\\.");
                    Object renderer = beanRegistry.get(keys[0]); // keys[0] = getting renderer
                    
                    // Provider
                    // keys[1] = messageProvider
                    PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(keys[1], renderer.getClass()); 
                    objPropertyDescriptor.getWriteMethod().invoke(renderer, beanRegistry.get(val));
                }
            }
            outputBeanRegistry();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void outputBeanRegistry() {
    	beanRegistry.forEach((key, val) -> {
    		System.out.println("Key: " + key + " Value: " + val);
    	});
    }
}
