//package org.example;
//
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import okhttp3.*;
//
//import java.util.UUID;
//public class RestAPI {
//    private String key;
//
//    public void login(String user, String password) throws Exception{
//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\n    \"type\": \"login\",\n    \"json\": \"{'user': '" + user + "', 'password': '" + password + "'}\"\n}");
//        Request request = new Request.Builder()
//                .url("https://qhiug7xk62.execute-api.eu-central-1.amazonaws.com/default/Bucket")
//                .post(body)
//                .addHeader("x-api-key", "B8q6CdR6707ZV9YxGnivN4kHl9zbvP0h6jwxOsBS")
//                .addHeader("Content-Type", "application/json")
//                .addHeader("cache-control", "no-cache")
//                .addHeader("Postman-Token", "6c90d274-9497-4e5f-a261-39f77450e5bb")
//                .build();
//
//        Response response = client.newCall(request).execute();
//        JsonParser parser = new JsonParser();
//        JsonObject obj = parser.parse(response.body().string()).getAsJsonObject();
//        key = obj.get("key").getAsString();
//        System.out.println("Key: " + key);
//    }
//
//
//
//
//
//
//}
