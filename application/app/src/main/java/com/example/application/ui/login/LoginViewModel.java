package com.example.application.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.application.model.MemberVO;
import com.example.application.repository.AppRepository;

import retrofit2.Callback;

public class LoginViewModel extends ViewModel {
    private final AppRepository repository;
    public MutableLiveData<String> id;
    public MutableLiveData<String> password;

    public LoginViewModel(AppRepository repository) {
        this.repository = repository;
        id = new MutableLiveData<>();
        password = new MutableLiveData<>();
    }


    void login(Callback<MemberVO> callback) {
//        repository.loginMember(new MemberVO(id.getValue(), password.getValue()), callback);
    }


    boolean validateLoginInfo() {
        return id.getValue() != null && password.getValue() != null;
    }
}