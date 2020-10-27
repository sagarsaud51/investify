package io.saud.investify.ttlmap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author SAUD
 * @created 27/10/2020 - 12:55 PM
 */
public class TTLMapImpl implements TTLMap{

    Map<String,Object> ttlMap = new ConcurrentHashMap<>();


    @Override
    public void put(String key, Object value, long timeToLiveMillis) {
        ttlMap.put(key, value);


    }

    @Override
    public Object get(String key) {
        return null;
    }

    public static void cacheValidator(){

    }
}
