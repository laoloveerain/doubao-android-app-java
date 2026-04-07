package com.example.doubaoapp.api;

import com.example.doubaoapp.model.ChatRequest;
import com.example.doubaoapp.model.ChatResponse;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface DoubaoApi {

    @POST("chat/completions")
    retrofit2.Call<ChatResponse> chat(
        @Header("Authorization") String authorization,
        @Header("Content-Type") String contentType,
        @Body ChatRequest request
    );

    String BASE_URL = "https://ark.cn-beijing.volces.com/api/v3/";
}
