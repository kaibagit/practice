package com.kaiba.demo.loader;

import java.util.*;

/**
 * Created by luliru on 2016/8/8.
 */
public class ServiceLoaderDemo {

    public static void main(String[] args) throws Exception {
        List<TeslaModule> allServices = new ArrayList<>();
        for(TeslaModule module : ServiceLoader.load(TeslaModule.class)) {
            allServices.add(module);
        }
        Collections.sort(allServices, new Comparator<TeslaModule>() {
            @Override
            public int compare(TeslaModule o1, TeslaModule o2) {
                return o1.getStartLevel() - o2.getStartLevel();
            }
        });
        for (TeslaModule module : allServices) {
            module.init();
        }
        for (TeslaModule module : allServices) {
            module.start();
        }
        System.out.println("container start success");

        Collections.sort(allServices, new Comparator<TeslaModule>() {
            @Override
            public int compare(TeslaModule o1, TeslaModule o2) {
                return o2.getStartLevel() - o1.getStartLevel();
            }
        });
        for (TeslaModule module : allServices) {
            module.stop();
        }
        System.out.println("container stop success");
    }
}
