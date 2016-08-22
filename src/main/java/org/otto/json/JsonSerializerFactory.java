package org.otto.json;

import org.otto.json.impl.JsonSerializerImpl;

/**
 * Created by tomek on 2016-08-20.
 */
public class JsonSerializerFactory {
    private static JsonSerializer serializer = new JsonSerializerImpl();

    public static JsonSerializer fetchSerializer() {
        return serializer;
    }
}
