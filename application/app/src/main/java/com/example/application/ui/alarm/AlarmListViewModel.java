package com.example.application.ui.alarm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.application.model.AuthVO;
import com.example.application.model.FoodVO;
import com.example.application.model.MemberVO;
import com.example.application.repository.AppRepository;

import java.util.List;

import retrofit2.Callback;

public class AlarmListViewModel extends ViewModel {

    private AppRepository appRepository;
    private MutableLiveData<List<FoodVO>>  alarmList;

    public AlarmListViewModel(AppRepository appRepository){
        this.appRepository = appRepository;
        alarmList = new MutableLiveData<>();
    }

    public MutableLiveData<List<FoodVO>> getAlarmList() {
        return alarmList;
    }

    public void useAlarm(MemberVO memberVO, Callback<List<FoodVO>> callback){
        System.out.println(memberVO.getAlarmSet());
        appRepository.useAlarm(memberVO, callback);
    }

    public void deleteAlarm(MemberVO memberVO, FoodVO foodVO, Callback<MemberVO> callback){
        appRepository.deleteAlarm(memberVO, foodVO, callback);
    }
}
