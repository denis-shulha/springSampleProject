package tst.springSample.annotationProcessors;

import javafx.util.Pair;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PeriodicalScopeConfigurer implements Scope {
    private Map<String, Pair<Integer,Object>> map = new HashMap<>();
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        if (map.containsKey(name)) {
            Pair<Integer,Object> pair = map.get(name);
            if( pair.getKey() == 4) {
                map.put(name,new Pair<>(0,objectFactory.getObject()));
            }
            else
                map.put(name,new Pair<>(pair.getKey()+1,pair.getValue()));
        }
        else {
            map.put(name, new Pair<>(0,objectFactory.getObject()));
        }
        return map.get(name).getValue();
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
