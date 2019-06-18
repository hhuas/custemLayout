package com.hua.mvvmhabittest01.utils.converterFactory;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.hua.mvvmhabittest01.entity.MyBaseResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private static final String TAG = MyGsonResponseBodyConverter.class.getSimpleName();
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    public MyGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        Log.e(TAG, "response中接收的value数据：" + value);
        String s = gson.toJson(value.toString());
        Log.e(TAG, "response中接收的json数据：" + s);


        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            T result = adapter.read(jsonReader);
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw new JsonIOException("JSON document was not fully consumed.");
            }
           MyBaseResponse myBaseResponse= (MyBaseResponse) result;
            Log.e(TAG, "response中传递的result数据：" + result);
            Log.e(TAG, "response中传递的(MyBaseResponse) result数据：" + myBaseResponse.getCode());
            return result;
        } finally {
            value.close();
        }
    }
}
