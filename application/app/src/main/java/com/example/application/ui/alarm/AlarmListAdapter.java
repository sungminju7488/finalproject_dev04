package com.example.application.ui.alarm;

import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
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
import com.example.application.model.MemberVO;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER_POSITION = 0;
//    private AlarmListViewModel viewModel;
//    private MemberVO memberVO;
    private List<FoodVO> alarmList = new ArrayList<>();

//    public AlarmListAdapter(AlarmListViewModel viewModel, MemberVO memberVO) {
//        this.viewModel = viewModel;
//        this.memberVO = memberVO;
//    }

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

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_list;
    }

    private class AlarmViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        private TextView delete_btn;

        private FoodVO foodVO;

        public AlarmViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            View itemView = binding.getRoot();
            delete_btn = itemView.findViewById(R.id.delete_btn);
        }

        private void bind(FoodVO foodVO){
            this.foodVO = foodVO;

            Log.i("hans", foodVO.getFoodName());
            binding.setVariable(BR.foodVO, foodVO);
            binding.executePendingBindings();
        }
    }
}
