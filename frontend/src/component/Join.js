import React, { Component, useState } from "react";
import axios from "axios";
import "../css/Join.css";

function Join() {
  const [id, setId] = useState(null);
  const [pw, setPw] = useState(null);
  const [c_pw, setC_Pw] = useState(null);
  const [name, setName] = useState(null);
  const [year, setYear] = useState(null);
  const [month, setMonth] = useState(null);
  const [day, setDay] = useState(null);
  const [gender, setGender] = useState(null);
  const [email, setEmail] = useState(null);
  const [phonenumber, setPhonenumber] = useState(null);

  const joinHandler = async (event) => {
    event.preventDefault();

    // alert(
    //   "id : " +
    //     id +
    //     "\n" +
    //     "pw : " +
    //     pw +
    //     "\n" +
    //     "c_pw : " +
    //     c_pw +
    //     "\n" +
    //     "name : " +
    //     name +
    //     "\n" +
    //     "year : " +
    //     year +
    //     "\n" +
    //     "month : " +
    //     month +
    //     "\n" +
    //     "day : " +
    //     day +
    //     "\n" +
    //     "gender : " +
    //     gender +
    //     "\n" +
    //     "email : " +
    //     email +
    //     "\n" +
    //     "phonenumber : " +
    //     phonenumber
    // );

    const birthday = year + "-" + month + "-" + day;

    const data = {
      id: id,
      password: pw,
      confirmpassword: c_pw,
      name: name,
      birthday: birthday,
      gender: gender,
      email: email,
      phonenumber: phonenumber,
    };

    axios
      .post("/member/join", { data })
      .then((res) => {
        alert("정상적으로 통신이 이루어졌습니다.");
      })
      .catch((err) => alert(err.response.data.msg));
  };

  return (
    <form onSubmit={joinHandler}>
      <div id="content">
        {/* header부분 */}
        <div id="header">
          <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
            <span id="sunbbang">선 빵</span>
          </a>
        </div>
        {/* id 부분*/}
        <h3 className="join_title">
          <label for="id">아이디</label>
        </h3>
        <span className="box var_id">
          <input
            type="text"
            id="id"
            className="var"
            maxLength="20"
            value={id}
            onChange={(e) => setId(e.target.value)}
          ></input>
        </span>
        {/* pw 1차 부분 */}
        <h3 className="join_title">
          <label for="pswd1">비밀번호</label>
        </h3>
        <span className="box var_pass">
          <input
            type="password"
            id="pswd1"
            className="var"
            maxLength="20"
            onChange={(e) => setPw(e.target.value)}
          ></input>
        </span>
        {/* pw 2차 부분 */}
        <h3 className="join_title">
          <label for="pawd2">비밀번호 재확인</label>
        </h3>
        <span className="box var_pass_check">
          <input
            type="password"
            id="pswd2"
            className="var"
            maxLength="20"
            onChange={(e) => setC_Pw(e.target.value)}
          ></input>
        </span>
        {/* 이름 부분 */}
        <h3 className="join_title">
          <label for="name">이름</label>
        </h3>
        <span className="box">
          <input
            type="text"
            id="name"
            className="var"
            maxLength="20"
            onChange={(e) => setName(e.target.value)}
          ></input>
        </span>
        {/*생년월일 부분*/}
        <div>
          <h3 className="join_title">
            <label for="yy">생년월일</label>
          </h3>
          <div id="bir_wrap">
            <div id="bir_yy">
              <span className="box">
                <input
                  type="text"
                  id="yy"
                  className="var"
                  maxLength="4"
                  placeholder="년(4자)"
                  onChange={(e) => setYear(e.target.value)}
                ></input>
              </span>
            </div>
            <div id="bir_mm">
              <span className="box">
                <select
                  id="mm"
                  className="sel"
                  onChange={(e) => setMonth(e.target.value)}
                >
                  <option>월</option>
                  <option value="01">1</option>
                  <option value="02">2</option>
                  <option value="03">3</option>
                  <option value="04">4</option>
                  <option value="05">5</option>
                  <option value="06">6</option>
                  <option value="07">7</option>
                  <option value="08">8</option>
                  <option value="09">9</option>
                  <option value="10">10</option>
                  <option value="11">11</option>
                  <option value="12">12</option>
                </select>
              </span>
            </div>
            <div id="bir_dd">
              <span className="box">
                <input
                  type="text"
                  id="dd"
                  className="var"
                  maxLength="2"
                  placeholder="일"
                  onChange={(e) => setDay(e.target.value)}
                ></input>
              </span>
            </div>
          </div>
        </div>
        {/*성별 부분 */}
        <div>
          <h3 className="join_title">
            <label for="gender">성별</label>
          </h3>
          <span className="box">
            <select
              id="gender"
              className="sel"
              onChange={(e) => setGender(e.target.value)}
            >
              <option>성별</option>
              <option value="M">남자</option>
              <option value="F">여자</option>
            </select>
          </span>
        </div>
        {/*이메일 부분 */}
        <div>
          <h3 className="join_title">
            <label for="email">이메일</label>
          </h3>
          <span className="box">
            <input
              type="text"
              id="email"
              className="var"
              maxLength="100"
              onChange={(e) => setEmail(e.target.value)}
            ></input>
          </span>
        </div>
        {/*휴대전화 부분 */}
        <div>
          <h3 className="join_title">
            <label for="phonenum">휴대전화</label>
          </h3>
          <span className="box">
            <input
              type="tel"
              id="mobile"
              className="var"
              maxLength="11"
              placeholder="전화번호를 입력하세요"
              onChange={(e) => setPhonenumber(e.target.value)}
            ></input>
          </span>
        </div>
        {/* 가입하기 버튼 */}
        <div className="btn_area">
          <button type="submit" id="btnJoin">
            <span>가입하기</span>
          </button>
        </div>
      </div>
    </form>
  );
}

export default Join;
