package io.saud.investify.ttlmap;

/**
 * @author SAUD
 * @created 27/10/2020 - 12:54 PM
 */
public interface TTLMap {

    void put(String key, Object value, long timeToLiveMillis);

    Object get(String key);
}
