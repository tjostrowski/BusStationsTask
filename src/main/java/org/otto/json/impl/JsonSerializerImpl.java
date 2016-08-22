package org.otto.json.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.otto.json.JsonSerializer;

import java.io.StringWriter;

/**
 * Created by tomek on 2016-08-20.
 */
public class JsonSerializerImpl implements JsonSerializer {
    private Gson gson;

    public JsonSerializerImpl() {
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Override
    public String serialize(Object toSerialize) {
        StringWriter writer = new StringWriter();
        gson.toJson(toSerialize, writer);
        return writer.getBuffer().toString();
    }
}
