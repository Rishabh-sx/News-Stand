package com.rishabh.newstand.data.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rishabh.newstand.pojo.headlinesresponse.Source;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class SourceTypeConverter {

    @TypeConverter
    public static Source stringToSource(String data) {
        if (data == null || data.isEmpty()) {
            return new Source();
        }
        return new Gson().fromJson(data, Source.class);
    }

    @TypeConverter
    public static String integerList(Source source) {
        return new Gson().toJson(source);
    }
}




