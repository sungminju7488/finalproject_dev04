import axios from "axios";
import React, { useState } from "react";
import auth from "../Logic/Auth";
import "../css/Login.css";
import { Redirect } from "react-router";
import { Link } from "react-router-dom";

function Login() {
  const [memberId, setMemberId] = useState("");
  const [password, setPassword] = useState("");
  const [isLogin, setIsLogin] = useState(false);

  const handleLogin = (e) => {
    e.preventDefault();

    console.log("id : " + memberId + " | pw : " + password);
    axios
      .post("/member/login", { memberId, password })
      .then((res) => {
        auth.setAuth(res.data);
        alert(auth.name + "님이 로그인하셨습니다.");
        setIsLogin(true);
      })
      .catch((err) => alert(err.response.data.msg));
  };

  if (isLogin) return <Redirect to="/" />;
  else {
    return (
      <div id="content">
        <div id="header">
          <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
            <span id="sunbbang">선 빵</span>
          </a>
        </div>
        <form onSubmit={handleLogin}>
          <span className="box id">
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
          <span className="box password">
            <input
              type="password"
              id="password"
              className="var"
              maxLength="20"
              placeholder="비밀번호"
              onChange={(e) => {
                setPassword(e.target.value);
              }}
            ></input>
          </span>
          <div id="btn_area">
            <button type="submit" id="login_btn">
              <span>로 그 인</span>
            </button>
          </div>
          <div style={{ width: "85px", margin: "-50px auto 0px auto" }}>
            <Link to="/member/findidpage">아이디 찾기</Link>
          </div>
        </form>
      </div>
    );
  }
}

export default Login;
