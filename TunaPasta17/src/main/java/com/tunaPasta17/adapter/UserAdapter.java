package com.tunaPasta17.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tunaPasta17.R;
import com.tunaPasta17.databinding.ItemUserBinding;
import com.tunaPasta17.model.UserInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserAdapterHolder> {

    private List<UserInfo> userList;

    public UserAdapter(List<UserInfo> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user, parent, false);
        return new UserAdapterHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterHolder holder, int position) {
        holder.getBinding().setUser(userList.get(position));
    }

    @Override
    public int getItemCount() {
        if (userList == null) {
            return 0;
        }
        return userList.size();
    }

    class UserAdapterHolder extends RecyclerView.ViewHolder {
        private ItemUserBinding binding;

        UserAdapterHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ItemUserBinding getBinding() {
            return binding;
        }
    }

}
