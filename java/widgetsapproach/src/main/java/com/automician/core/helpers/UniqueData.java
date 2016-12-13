package com.automician.core.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class UniqueData {

    private Map<String, String> dataContainer = new HashMap<String, String>();

    public String the(String name) {
        return the(name, "");
    }

    public String the(String name, String suffix) {
        if (!dataContainer.containsKey(name)) {
            dataContainer.put(name, name + UUID.randomUUID() + suffix);
        }
        return dataContainer.get(name);
    }

    public void clearUniqueData() {
        dataContainer.clear();
    }

    public void deleteUniqueData(String name) {
        dataContainer.remove(name);
    }
}
