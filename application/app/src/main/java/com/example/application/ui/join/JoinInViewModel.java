package com.example.application.ui.join;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.application.model.MemberVO;
import com.example.application.repository.MemberRepository;

import java.net.HttpCookie;
import java.text.Annotation;

import retrofit2.Callback;

public class JoinInViewModel extends ViewModel {
    private final MemberRepository repository;
    public MutableLiveData<String> Id;
    public MutableLiveData<String> password;
    public MutableLiveData<String> passwordConfirm;
    public MutableLiveData<String> name;
    public MutableLiveData<String> email;
    public MutableLiveData<String> phone;
    public MutableLiveData<String> profileUrl;

    public JoinInViewModel(MemberRepository repository) {
        this.repository = repository;
        Id = new MutableLiveData<>();
        password = new MutableLiveData<>();
        passwordConfirm = new MutableLiveData<>();
        name = new MutableLiveData<>();
        email = new MutableLiveData<>();
        phone = new MutableLiveData<>();
        profileUrl = new MutableLiveData<>();
    }


        void JoinIn(Callback<Object> callback) {

            MemberVO member = new MemberVO(
                    Id.getValue(),
                    password.getValue(),
                    name.getValue(),
                    email.getValue(),
                    phone.getValue(),
                    null

            );
    }


    boolean validateSignUpInfo() {
        return validateIdAndName()
                && validatePassword()
                && validateEmail()
                && phone.getValue() != null;
    }


    boolean validatePassword() {
        return password.getValue() != null
                && passwordConfirm.getValue() != null
                && password.getValue().equals(passwordConfirm.getValue());
    }


    boolean validateIdAndName() {
        return Id.getValue() != null
                && !Id.getValue().contains(" ")
                && name.getValue() != null
                && !name.getValue().contains(" ");
    }

    boolean validateEmail() {
        if (email.getValue() != null) {
            return email.getValue().matches("[\\S]+@[\\S]+");
        }
        return false;
    }


    boolean validateProfile() {
        return profileUrl.getValue() != null;
    }
}