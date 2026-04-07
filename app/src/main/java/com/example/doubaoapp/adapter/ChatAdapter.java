package com.example.doubaoapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doubaoapp.R;
import com.example.doubaoapp.databinding.ItemMessageBinding;
import com.example.doubaoapp.model.ChatRequest;

import java.util.List;
import java.util.Objects;

public class ChatAdapter extends ListAdapter<ChatRequest.Message, ChatAdapter.MessageViewHolder> {

    public ChatAdapter() {
        super(new MessageDiffCallback());
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMessageBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_message,
                parent,
                false
        );
        return new MessageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        private final ItemMessageBinding binding;

        public MessageViewHolder(ItemMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ChatRequest.Message message) {
            binding.textViewRole.setText("user".equals(message.getRole()) ? "你" : "豆包");
            binding.textViewMessage.setText(message.getContent());

            // 根据角色设置不同的样式
            if ("user".equals(message.getRole())) {
                binding.cardView.setCardBackgroundColor(
                        binding.getRoot().getContext().getResources().getColor(android.R.color.holo_blue_light)
                );
            } else {
                binding.cardView.setCardBackgroundColor(
                        binding.getRoot().getContext().getResources().getColor(android.R.color.holo_green_light)
                );
            }
        }
    }

    private static class MessageDiffCallback extends DiffUtil.ItemCallback<ChatRequest.Message> {
        @Override
        public boolean areItemsTheSame(@NonNull ChatRequest.Message oldItem, @NonNull ChatRequest.Message newItem) {
            return Objects.equals(oldItem.getContent(), newItem.getContent()) &&
                   Objects.equals(oldItem.getRole(), newItem.getRole());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ChatRequest.Message oldItem, @NonNull ChatRequest.Message newItem) {
            return Objects.equals(oldItem.getContent(), newItem.getContent()) &&
                   Objects.equals(oldItem.getRole(), newItem.getRole());
        }
    }
}
