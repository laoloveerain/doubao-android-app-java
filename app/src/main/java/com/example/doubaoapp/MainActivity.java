package com.example.doubaoapp;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.doubaoapp.adapter.ChatAdapter;
import com.example.doubaoapp.databinding.ActivityMainBinding;
import com.example.doubaoapp.viewmodel.ChatViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ChatViewModel viewModel;
    private ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setupViewModel();
        setupRecyclerView();
        setupObservers();
        setupListeners();
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(ChatViewModel.class);
    }

    private void setupRecyclerView() {
        adapter = new ChatAdapter();
        binding.recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewChat.setAdapter(adapter);
    }

    private void setupObservers() {
        viewModel.getMessages().observe(this, messages -> {
            adapter.submitList(messages);
            if (messages != null && !messages.isEmpty()) {
                binding.recyclerViewChat.scrollToPosition(messages.size() - 1);
            }
        });

        viewModel.getUiState().observe(this, state -> {
            if (state instanceof ChatViewModel.UiState.Loading) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.buttonSend.setEnabled(false);
            } else if (state instanceof ChatViewModel.UiState.Success) {
                binding.progressBar.setVisibility(View.GONE);
                binding.buttonSend.setEnabled(true);
                binding.editTextMessage.setText("");
            } else if (state instanceof ChatViewModel.UiState.Error) {
                binding.progressBar.setVisibility(View.GONE);
                binding.buttonSend.setEnabled(true);
                Toast.makeText(this, ((ChatViewModel.UiState.Error) state).getMessage(),
                        Toast.LENGTH_LONG).show();
            } else if (state instanceof ChatViewModel.UiState.Idle) {
                binding.progressBar.setVisibility(View.GONE);
                binding.buttonSend.setEnabled(true);
            }
        });
    }

    private void setupListeners() {
        binding.buttonSend.setOnClickListener(v -> {
            String message = binding.editTextMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                viewModel.sendMessage(message);
                hideKeyboard();
            } else {
                Toast.makeText(this, "请输入消息", Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonClear.setOnClickListener(v -> {
            viewModel.clearChat();
            Toast.makeText(this, "聊天记录已清空", Toast.LENGTH_SHORT).show();
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.editTextMessage.getWindowToken(), 0);
    }
}
