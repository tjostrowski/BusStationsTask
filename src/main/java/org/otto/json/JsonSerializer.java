package org.otto.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.StringWriter;

/**
 * Created by tomek on 2016-08-20.
 */
public interface JsonSerializer {
    String serialize(Object toSerialize);
}
