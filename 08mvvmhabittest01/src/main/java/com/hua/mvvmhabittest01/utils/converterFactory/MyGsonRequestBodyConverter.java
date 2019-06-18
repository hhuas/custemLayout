package com.hua.mvvmhabittest01.utils.converterFactory;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

public class MyGsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final String TAG = MyGsonRequestBodyConverter.class.getSimpleName();

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    public MyGsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {

        Log.e(TAG, "request中传递的value数据：" + value);
        String json = gson.toJson(value.toString());
        Log.e(TAG, "request中传递的json数据：" + json);
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        adapter.write(jsonWriter, value);
        jsonWriter.close();
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE, buffer.readByteString());

        Log.e(TAG, "request中传递的requestBody数据：" + requestBody.toString());
//        RequestBody requestBody = RequestBody.create(MEDIA_TYPE, value.toString());

        return requestBody;
    }
}
