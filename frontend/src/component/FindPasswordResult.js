import { useState } from "react";

function FindPasswordResult(props) {
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const moveLoginHandler = () => {
    window.location.replace("/member/loginpage");
  };

  return (
    <div id="content">
      {/* header부분 */}
      <div id="header">
        <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
          <span id="sunbbang">비밀번호 변경</span>
        </a>
      </div>
      {/* pw 1차 부분 */}
      <h3 className="join_title">
        <label>새 비밀번호</label>
      </h3>
      <span className="box var_pass">
        <input
          type="password"
          id="password"
          onChange={(e) => {
            setPassword(e.target.value);
          }}
          className="var"
          maxLength="20"
        ></input>
      </span>
      {/* pw 2차 부분 */}
      <h3 className="join_title">
        <label>비밀번호 재확인</label>
      </h3>
      <span className="box var_pass_check">
        <input
          type="password"
          id="confirmPassword"
          onChange={(e) => {
            setConfirmPassword(e.target.value);
          }}
          className="var"
          maxLength="20"
        ></input>
      </span>
      <div id="btn_area">
        <button type="button" id="login_btn" onClick={moveLoginHandler}>
          <span>비밀번호 변경</span>
        </button>
      </div>
    </div>
  );
}
export default FindPasswordResult;
