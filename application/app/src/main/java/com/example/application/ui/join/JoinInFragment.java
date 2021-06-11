//package com.example.application.ui.join;
//import androidx.databinding.DataBindingUtil;
//import androidx.lifecycle.ViewModelProvider;
//
//import android.content.Context;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//
//import com.example.application.R;
//import com.example.application.ui.base.ViewModelFactory;
//import com.example.application.databinding.FragmentJoininBinding;
//
//
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class JoinInFragment extends Fragment {
//    private static final int PERMISSION_REQUEST_CODE = 1000;
//    private ImageView profileView;
//    private FragmentJoininBinding binding;
//    private JoinInViewModel JoinInViewModel;
//    private Context context;
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_joinin, container, false);
//        return binding.getRoot();
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        this.context = requireContext().getApplicationContext();
//
//        // Data Binding 연결
//        JoinInViewModel = new ViewModelProvider(this, new ViewModelFactory()).get(com.example.application.ui.join.JoinInViewModel.class);
//        binding.set(JoinInViewModel);
//
//
//
//        final Button signUpButton = view.findViewById(R.id.join_up_btn);
//        // 가입하기 버튼 클릭 이벤트 처리
//        signUpButton.setOnClickListener(v -> {
//            if (!JoinInViewModel.validateIdAndName()) {
//                Toast.makeText(context, "아이디나 이름에 공백을 포함할 수 없습니다.", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            if (!JoinInViewModel.validatePassword()) {
//                Toast.makeText(context, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            if (!JoinInViewModel.validateEmail()) {
//                Toast.makeText(context, "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            if (!JoinInViewModel.validateSignUpInfo()) {
//                Toast.makeText(context, "빈 칸을 모두 채워주세요.", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//
//
//
//
//            JoinInViewModel.JoinIn(new Callback<Object>() {
//                @Override
//                public void onResponse(Call<Object> call, Response<Object> response) {
//                    if (response.isSuccessful()) {
//                        Toast.makeText(context, "회원 가입 성공", Toast.LENGTH_SHORT).show();
//                        requireActivity().onBackPressed();
//                    } else {
//                        Toast.makeText(context, "회원 가입 실패", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Object> call, Throwable t) {
//                    Toast.makeText(context, "회원 가입 실패", Toast.LENGTH_SHORT).show();
//                }
//            });
//        });
//
//        final Button cancelButton = view.findViewById(R.id.sign_up_cancel_btn);
//        cancelButton.setOnClickListener(v -> {
//            requireActivity().onBackPressed();
//        });
//
//
//
//
//    }
//}