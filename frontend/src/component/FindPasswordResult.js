import axios from "axios";
import { useState } from "react";

function FindPasswordResult(props) {
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const changePasswordHandler = () => {
    if (password === "" || confirmPassword === "")
      return alert("새 비밀번호와 재확인 비밀번호를 모두 입력해주세요");
    else if (password !== confirmPassword)
      return alert("새 비밀번호와 재확인 비밀번호가 다릅니다.");

    const memberSeq = props.location.state.memberSeq;
    axios
      .post("/changePassword", { memberSeq, password })
      .then((res) => {
        if (res.data === true) {
          alert("비밀번호가 변경되었습니다.");
          window.location.replace("/member/loginpage");
        } else {
          alert("비밀번호 변경에 실패하였습니다.");
        }
      })
      .catch((err) => alert(err.response.data.msg));
  };

  return (
    <div id="content">
      {/* header부분 */}
      <div id="header">
        <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
          <span id="sunbbang">비밀번호 변경</span>
        </a>
      </div>
      <form onSubmit={changePasswordHandler}>
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
          <button type="submit" id="login_btn">
            <span>비밀번호 변경</span>
          </button>
        </div>
      </form>
    </div>
  );
}
export default FindPasswordResult;
