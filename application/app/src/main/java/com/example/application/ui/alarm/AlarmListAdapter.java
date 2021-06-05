package com.example.application.ui.alarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.BR;
import com.example.application.R;
import com.example.application.model.FoodVO;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AlarmListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private AlarmListViewModel viewModel;
    private List<FoodVO> alarmList = new ArrayList<>();

    public AlarmListAdapter(AlarmListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setAlarmList(List<FoodVO> alarmList) {
        this.alarmList = alarmList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new AlarmViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof AlarmViewHolder){
            ((AlarmViewHolder)holder).bind(alarmList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return alarmList.size();
    }

    private class AlarmViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;
        private TextView food_name;
        private TextView sale_time;
        private TextView delete_btn;

        private FoodVO foodVO;

        public AlarmViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            View itemView = binding.getRoot();
            food_name = itemView.findViewById(R.id.food_name);
            sale_time = itemView.findViewById(R.id.sale_time);
            delete_btn = itemView.findViewById(R.id.delete_btn);
        }

        private void bind(FoodVO foodVO){
            this.foodVO = foodVO;

            binding.setVariable(BR.foodVO, foodVO);
            binding.executePendingBindings();
        }
    }
}
