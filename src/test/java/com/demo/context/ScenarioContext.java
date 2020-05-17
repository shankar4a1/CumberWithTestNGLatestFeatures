package com.demo.context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Map<String, Object> scenarioContext;

    public ScenarioContext(){
        scenarioContext = new HashMap<>();
    }

    public void setContext(Map<String, Object> map) {
        scenarioContext.putAll(map);
    }

    public String getContext(String key){
        return String.valueOf(scenarioContext.get(key));
    }

    public void setKeyValue(String key, String value){
        scenarioContext.put(key, value);

    }

    public Boolean isContains(String key){
        return scenarioContext.containsKey(key);
    }

    public void clearContext()
    {
        scenarioContext.clear();
    }

    public Map<String, Object> getScenarioContext(){
        return scenarioContext;
    }

}
 
