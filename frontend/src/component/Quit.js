import "../css/Quit.css";
import { Link } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
import auth from "../Logic/Auth";

function Quit() {
  const [memberId, setMemberId] = useState("");
  const [password, setPassword] = useState("");

  const QuitHandler = () => {
    const grade = auth.grade;
    const memberSeq = auth.memberSeq;

    axios
      .post("/quit", { memberSeq, memberId, password, grade })
      .then((res) => {
        if (res.data === true) {
          auth.logout();
          alert("회원탈퇴에 성공하였습니다.");
          window.location.replace("/");
        } else {
          alert("회원탈퇴 요청이 실패하였습니다.");
        }
      })
      .catch((err) => alert(err.response.data.msg));
  };

  return (
    <div id="content">
      <form onSubmit={QuitHandler}>
        <div id="header">
          <a href="/" target="_self" title="선빵 메인 페이지 보러가기">
            <span id="sunbbang">회원탈퇴</span>
          </a>
        </div>
        <h3 className="join_title">
          <label>아이디</label>
        </h3>
        <span className="box var_id">
          <input
            type="text"
            id="id"
            className="var"
            maxLength="20"
            onChange={(e) => {
              setMemberId(e.target.value);
            }}
            placeholder="아이디를 입력해주세요."
          ></input>
        </span>

        <h3 className="join_title">
          <label>비밀번호</label>
        </h3>
        <span className="box var_pass">
          <input
            type="password"
            id="pswd1"
            className="var"
            maxLength="20"
            onChange={(e) => {
              setPassword(e.target.value);
            }}
            placeholder="비밀번호를 입력해주세요."
          ></input>
        </span>

        <div className="btn_area">
          <Link to="/">
            <button type="submit" id="btnCancel">
              <span>취 소</span>
            </button>
          </Link>
          <button type="submit" id="btnQuit">
            <span>회 원 탈 퇴</span>
          </button>
        </div>
      </form>
    </div>
  );
}

export default Quit;
