package com.automician.core.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import static java.lang.System.currentTimeMillis;


public class UniqueData {

    private Map<String, String> dataContainer = new HashMap<String, String>();

    public String the(String name) {
        return the(name, "");
    }

    public String the(String name, String suffix) {
        if (!this.dataContainer.containsKey(name)) {
            //dataContainer.put(name, name + UUID.randomUUID() + suffix);
            //this.dataContainer.put(name, name + System.currentTimeMillis() + suffix);
            this.dataContainer.put(name, name +  (new Random().nextInt(Integer.MAX_VALUE)) + suffix);
        }
        return this.dataContainer.get(name);
    }

    public void clearUniqueData() {
        this.dataContainer.clear();
    }

    public void deleteUniqueData(String name) {
        this.dataContainer.remove(name);
    }
}
