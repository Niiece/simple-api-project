package org.example.utils;

import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.List;

public class JsonUtils {

    public static <T> CollectionType getArrayType(Class<T> tClass) {
        return TypeFactory.defaultInstance().constructCollectionType(List.class, tClass);
    }
}
