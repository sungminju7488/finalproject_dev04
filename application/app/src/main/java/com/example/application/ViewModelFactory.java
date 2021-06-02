package com.example.application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.application.repository.NetworkAPI;
import com.example.application.repository.AppRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        AppRepository appRepository = new AppRepository(NetworkAPI.getInstance().getMemberService());
//        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
//            return (T) new LoginViewModel(memberRepository);
//        } else if (modelClass.isAssignableFrom(ReadViewModel.class)) {
//            return (T) new ReadViewModel(articleRepository);
//        } else if (modelClass.isAssignableFrom(JoinViewModel.class)) {
//            return (T) new JoinViewModel(memberRepository);
//        } else if (modelClass.isAssignableFrom(MyPageViewModel.class)) {
//            return (T) new MyPageViewModel(memberRepository);
//        } else if (modelClass.isAssignableFrom(ModifyViewModel.class)) {
//            return (T) new ModifyViewModel(memberRepository);
//        } else if (modelClass.isAssignableFrom(QuitViewModel.class)) {
//            return (T) new QuitViewModel(memberRepository);
//        } else if (modelClass.isAssignableFrom(ListViewModel.class)) {
//            return (T) new ListViewModel(articleRepository);
//        } else {
//            throw new IllegalArgumentException("Unknown ViewModel class");
//        }
    }
}