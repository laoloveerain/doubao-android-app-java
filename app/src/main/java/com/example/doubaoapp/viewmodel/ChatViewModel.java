package com.example.doubaoapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doubaoapp.api.DoubaoApi;
import com.example.doubaoapp.model.ChatRequest;
import com.example.doubaoapp.model.ChatResponse;
import com.example.doubaoapp.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatViewModel extends ViewModel {

    private final DoubaoApi api = RetrofitClient.getDoubaoApi();

    // 配置你的 API Key
    private final String apiKey = "ced6e412-98ed-4aa5-aa64-dc5b0a5a61b1";

    // 模型名称，根据你开通的模型修改
    private final String modelName = "doubao-pro-32k";

    private final MutableLiveData<List<ChatRequest.Message>> messages = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<UiState> uiState = new MutableLiveData<>(new UiState.Idle());

    public LiveData<List<ChatRequest.Message>> getMessages() {
        return messages;
    }

    public LiveData<UiState> getUiState() {
        return uiState;
    }

    public void sendMessage(final String userMessage) {
        uiState.setValue(new UiState.Loading());

        // 添加用户消息到列表
        List<ChatRequest.Message> currentMessages = messages.getValue();
        if (currentMessages == null) {
            currentMessages = new ArrayList<>();
        }
        List<ChatRequest.Message> updatedMessages = new ArrayList<>(currentMessages);
        updatedMessages.add(new ChatRequest.Message("user", userMessage));
        messages.setValue(updatedMessages);

        // 构建请求
        ChatRequest request = new ChatRequest(modelName, updatedMessages);
        request.setStream(false);
        request.setTemperature(0.7f);

        // 调用 API
        api.chat("Bearer " + apiKey, "application/json", request).enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ChatRequest.Message assistantMessage = response.body().getChoices().get(0).getMessage();
                    List<ChatRequest.Message> finalMessages = new ArrayList<>(updatedMessages);
                    finalMessages.add(assistantMessage);
                    messages.setValue(finalMessages);
                    uiState.setValue(new UiState.Success(assistantMessage.getContent()));
                } else {
                    String errorMsg = "API 调用失败: " + response.code() + " - " + response.message();
                    uiState.setValue(new UiState.Error(errorMsg));
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                uiState.setValue(new UiState.Error("网络错误: " + t.getMessage()));
            }
        });
    }

    public void clearChat() {
        messages.setValue(new ArrayList<>());
        uiState.setValue(new UiState.Idle());
    }

    public void resetUiState() {
        uiState.setValue(new UiState.Idle());
    }

    // UI State classes
    public static class UiState {
        public static class Idle extends UiState {}
        public static class Loading extends UiState {}
        public static class Success extends UiState {
            private final String message;

            public Success(String message) {
                this.message = message;
            }

            public String getMessage() {
                return message;
            }
        }
        public static class Error extends UiState {
            private final String message;

            public Error(String message) {
                this.message = message;
            }

            public String getMessage() {
                return message;
            }
        }
    }
}
