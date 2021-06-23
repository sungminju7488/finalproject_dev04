import axios from "axios";
import { useState } from "react";
import { Link, Redirect } from "react-router-dom";

function FindPassword() {
  const [memberId, setMemberId] = useState("");
  const [memberSeq, setMemberSeq] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [findSuccess, setFindSuccess] = useState(false);

  const FindPasswordHandler = (event) => {
    event.preventDefault();

    if (memberId === "" || email === "" || phoneNumber === "")
      return alert("아이디와 이메일과 전화번호를 입력해주세요.");

    axios
      .post("/findPassword", { memberId, email, phoneNumber })
      .then((res) => {
        if (res.data !== undefined) {
          setMemberSeq(res.data.memberSeq);
          setFindSuccess(true);
        } else {
          alert("해당 정보와 매칭되는 계정이 존재하지 않습니다.");
        }
      })
      .catch((err) => alert(err.response.data.msg));
  };

  if (findSuccess)
    return (
      <Redirect
        to={{
          pathname: "/member/findpasswordresultpage",
          state: {
            memberSeq: memberSeq,
          },
        }}
      />
    );
  else {
    return (
      <div id="content">
        <div id="header">
          <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
            <span id="sunbbang">비밀번호 찾기</span>
          </a>
        </div>
        <form onSubmit={(e) => FindPasswordHandler(e)}>
          <span className="box">
            <input
              type="text"
              id="id"
              className="var"
              maxLength="20"
              placeholder="아이디"
              onChange={(e) => {
                setMemberId(e.target.value);
              }}
            ></input>
          </span>
          <span className="box">
            <input
              type="text"
              id="phoneNumber"
              className="var"
              maxLength="11"
              placeholder="전화번호"
              onChange={(e) => {
                setPhoneNumber(e.target.value);
              }}
            ></input>
          </span>
          <span className="box">
            <input
              type="email"
              id="text"
              className="var"
              placeholder="이메일"
              onChange={(e) => {
                setEmail(e.target.value);
              }}
            ></input>
          </span>
          <div id="btn_area">
            <button type="submit" id="login_btn">
              <span>비밀번호 찾기</span>
            </button>
          </div>
        </form>
      </div>
    );
  }
}

export default FindPassword;
