import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "../css/Mypage.css";
import auth from "../Logic/Auth";

function MyPage() {
  const [memberId, setMemberId] = useState("");
  const [name, setName] = useState("");
  const [nickName, setNickName] = useState("");
  const [sex, setSex] = useState("");
  const [birthDay, setBirthDay] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [address1, setAddress1] = useState("");
  const [address2, setAddress2] = useState("");
  const [grade, setGrade] = useState("");
  // 알림 리스트
  const [breadAlarmList, setBreadAlarmList] = useState([]);

  const init = (data) => {
    setMemberId(data.memberId);
    setName(data.name);
    setNickName(data.nickName);
    setSex(data.sex === "F" ? "여자" : "남자");
    setBirthDay(data.birthDay);
    setEmail(data.email);
    setPhoneNumber(data.phoneNumber);
    setAddress1(data.address1);
    setAddress2(data.address2);
    if (data.grade === "0") setGrade("일반회원");
    else if (data.grade === "1") setGrade("사업자회원");
    else setGrade("관리자");
  };

  useEffect(() => {
    const memberId = auth.memberId;
    const alarmSet = auth.alarmSet;

    axios
      .post("/myPage", { memberId })
      .then((res) => {
        init(res.data);
        if (res.data.alarmSet === null || res.data.alarmSet === undefined)
          return;
        axios
          .post("/bakery/useAlarm", { alarmSet })
          .then((f_res) => {
            setBreadAlarmList(f_res.data);
          })
          .catch((f_err) => alert(f_err.response.data.msg));
      })
      .catch((err) => alert(err.response.data.msg));
  }, []);

  function removeAlarmHandler(event, foodSeq) {
    event.preventDefault();

    const formData = new FormData();
    formData.append("memberSeq", auth.memberSeq);
    formData.append("foodSeq", foodSeq);

    axios
      .post("/bakery/deleteAlarm", formData)
      .then((res) => {
        if (res.data === true) {
          alert("해당 빵 알람이 삭제되었습니다.");
          window.location.reload();
        } else {
          alert("해당 빵 알림 삭제를 실패하였습니다.");
        }
      })
      .catch((err) => alert(err.response.data.msg));
  }

  return (
    <div id="content" style={{ width: "600px" }}>
      <div id="header">
        <a href="/" target="_self" title="선빵 메인 페이지 보러가기">
          <span id="sunbbang">마이페이지</span>
        </a>
      </div>
      <h3 className="mypage_title">
        <label>아이디</label>
      </h3>
      <span className="box var_id">
        <input
          type="text"
          id="memberId"
          value={memberId}
          className="var"
          readOnly
        ></input>
      </span>

      <h3 className="mypage_title">
        <label>이름</label>
      </h3>
      <span className="box var_name">
        <input
          type="text"
          id="name"
          value={name}
          className="var"
          readOnly
        ></input>
      </span>

      <h3 className="mypage_title">
        <label>별명</label>
      </h3>
      <span className="box var_nickname">
        <input
          type="text"
          id="nickName"
          value={nickName}
          className="var"
          readOnly
        ></input>
      </span>

      <h3 className="mypage_title">
        <label>성별</label>
      </h3>
      <span className="box var_sex">
        <input
          type="text"
          id="sex"
          value={sex}
          className="var"
          readOnly
        ></input>
      </span>

      <h3 className="mypage_title">
        <label>생년월일</label>
      </h3>
      <span className="box var_birthday">
        <input
          type="text"
          id="birthDay"
          value={birthDay}
          className="var"
          readOnly
        ></input>
      </span>

      <h3 className="mypage_title">
        <label>이메일</label>
      </h3>
      <span className="box var_email">
        <input
          type="text"
          id="email"
          value={email}
          className="var"
          readOnly
        ></input>
      </span>

      <h3 className="mypage_title">
        <label>휴대전화</label>
      </h3>
      <span className="box var_phonenum">
        <input
          type="text"
          id="phoneNumber"
          value={phoneNumber}
          className="var"
          readOnly
        ></input>
      </span>

      <h3 className="mypage_title">
        <label>주소</label>
      </h3>
      <span className="box var_address1">
        <input
          type="text"
          id="address1"
          value={address1}
          className="var"
          readOnly
        ></input>
      </span>

      <h3 className="mypage_title">
        <label>상세주소</label>
      </h3>
      <span className="box var_address2">
        <input
          type="text"
          id="address2"
          value={address2}
          className="var"
          readOnly
        ></input>
      </span>

      <h3 className="mypage_title">
        <label>회원구분</label>
      </h3>
      <span className="box var_grade">
        <input
          type="text"
          id="grade"
          value={grade}
          className="var"
          readOnly
        ></input>
      </span>

      {/* 알림 리스트 */}
      <h3 className="mypage_title">
        <label>알림 리스트</label>
      </h3>
      <table className="table">
        <thead>
          <tr style={{ textAlignLast: "center" }}>
            <th>알림해제</th>
            <th>이미지</th>
            <th>빵이름</th>
            <th>가게이름</th>
            <th>나오는시간</th>
            <th>가격</th>
          </tr>
        </thead>
        <tbody>
          {breadAlarmList.map((obj, index) => (
            <tr key={index} style={{ textAlignLast: "center" }}>
              <td>
                <button
                  onClick={(e) => removeAlarmHandler(e, obj.foodSeq)}
                ></button>
              </td>
              <td>
                <img
                  src={obj.foodPath}
                  style={{ width: "50px", height: "50px" }}
                />
              </td>
              <td>{obj.foodName}</td>
              <td>{obj.bakeryVO.storeName}</td>
              <td>{obj.saleTime}</td>
              <td>{obj.price}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <div id="btn_area">
        <Link to="/member/changememberpage">
          <button type="button" id="mypage_btn">
            <span>정보 변경하기</span>
          </button>
        </Link>
        <Link to="/member/quitpage">
          <button type="button" id="mypage_btn">
            <span>회원탈퇴</span>
          </button>
        </Link>
      </div>
    </div>
  );
}

export default MyPage;
