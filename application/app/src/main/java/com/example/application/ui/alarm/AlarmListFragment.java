package com.example.application.ui.alarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.MainActivity;
import com.example.application.R;
import com.example.application.ViewModelFactory;
import com.example.application.databinding.FragmentAlarmListBinding;
import com.example.application.model.AuthVO;
import com.example.application.model.FoodVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmListFragment extends Fragment {

    private FragmentAlarmListBinding binding;
    private AlarmListViewModel viewModel;
    private AlarmListAdapter adapter;

    private Button reloadButton;
    private Button logoutButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_alarm_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, new ViewModelFactory()).get(AlarmListViewModel.class);

        reloadButton = view.findViewById(R.id.reload_btn);
        logoutButton = view.findViewById(R.id.logout_btn);

        adapter = new AlarmListAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.alarm_list);
        recyclerView.setAdapter(adapter);

        updateAlarmList();

        reloadButton.setOnClickListener(v -> {
           updateAlarmList();
        });
        logoutButton.setOnClickListener(v -> {
            AuthVO.getInstance().setMemberVO(null);
//            ((MainActivity) requireActivity()).navigateTo(new LoginFragment(), true);
        });
    }

    private void updateAlarmList(){
        viewModel.useAlarm(AuthVO.getInstance().getMemberVO(), new Callback<List<FoodVO>>() {
            @Override
            public void onResponse(Call<List<FoodVO>> call, Response<List<FoodVO>> response) {
                if(response.isSuccessful()){
                    viewModel.getAlarmList().setValue(response.body());
                    adapter.setAlarmList(viewModel.getAlarmList().getValue());
                } else if (requireActivity() != null){
                    Toast.makeText(requireActivity().getApplicationContext(), "알람리스트: 불러오기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FoodVO>> call, Throwable t) {
                if (requireActivity() != null) {
                    Toast.makeText(requireActivity().getApplicationContext(), "알람리스트: 통신 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
