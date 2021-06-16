package com.example.application.ui.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.BR;
import com.example.application.R;
import com.example.application.model.AuthVO;
import com.example.application.model.FoodVO;
import com.example.application.model.MemberVO;
import com.example.application.ui.base.MainActivity;
import com.example.application.util.AlarmReceiver;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEADER_POSITION = 0;

    private AlarmListViewModel viewModel;
    private List<FoodVO> alarmList = new ArrayList<>();

    private Context context;
    private AlarmManager alarmManager;
    private Intent intent;
    private PendingIntent pendingIntent;

    public AlarmListAdapter() {}

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

        FoodVO foodVO = alarmList.get(position);

        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        Date date = new Date();

        int hour = Integer.parseInt(foodVO.getSaleTime().substring(0,2));
        date.setHours(hour);

        int minute = Integer.parseInt(foodVO.getSaleTime().substring(3,5));
        date.setMinutes(minute);

        if(date.getTime() <= new Date().getTime()){
            date.setDate(date.getDate()+1);
        }

        time.format(date);

        context = holder.itemView.getContext();
        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("storeName", foodVO.getBakeryVO().getStoreName());
        intent.putExtra("foodName", foodVO.getFoodName());
        intent.putExtra("saleTime", foodVO.getSaleTime());
        intent.putExtra("foodSeq", foodVO.getFoodSeq());
        pendingIntent = PendingIntent.getBroadcast(context, foodVO.getFoodSeq(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time.getCalendar().getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 1000, pendingIntent);

        if(holder instanceof AlarmViewHolder){
            ((AlarmViewHolder)holder).bind(foodVO);
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

            delete_btn.setOnClickListener(v -> {
                context = itemView.getContext();
                alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                intent = new Intent(context, AlarmReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(context, foodVO.getFoodSeq(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.cancel(pendingIntent);

                viewModel.deleteAlarm(AuthVO.getInstance().getMemberVO(), foodVO, new Callback<MemberVO>() {
                    @Override
                    public void onResponse(Call<MemberVO> call, Response<MemberVO> response) {
                        MemberVO memberVO = response.body();
                        AuthVO.getInstance().setMemberVO(memberVO);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<MemberVO> call, Throwable t) {
                        Toast.makeText(context, "알람삭제: 통신 실패", Toast.LENGTH_SHORT).show();
                    }
                });
            });

            Log.i("hans", foodVO.getFoodName());
            binding.setVariable(BR.foodVO, foodVO);
            binding.executePendingBindings();
        }
    }
}
