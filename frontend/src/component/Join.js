import axios from "axios";
import React, { useState } from "react";
import "../css/Join.css";
import AddressModal from "./AddressModal";
import DaumPostcode from "react-daum-postcode";

function Join() {
  //회원 정보
  const [memberId, setMemberId] = useState(null);
  const [password, setPassword] = useState(null);
  const [confirmPassword, setConfirmPassword] = useState(null);
  const [name, setName] = useState(null);
  const [year, setYear] = useState(null);
  const [month, setMonth] = useState(null);
  const [day, setDay] = useState(null);
  const [sex, setSex] = useState(null);
  const [email, setEmail] = useState(null);
  const [phoneNumber, setPhoneNumber] = useState(null);
  const [nickName, setNickName] = useState(null);
  const [address1, setAddress1] = useState(null);
  const [address2, setAddress2] = useState(null);
  //주소 Modal
  const [ModalOpen, setModalOpen] = useState(false);

  //회원가입(서버통신)
  const joinHandler = async (event) => {
    event.preventDefault();
    const birthDay = year + "-" + month + "-" + day;
    const grade = 0;
    const flatForm = "S";

    const yearRegex = /^\d{4}/;
    const dayRegex = /^\d{2}/;
    const phoneNumberRegex = /^\d{2}/;
    //정규식 및 가입불가 예외처리
    if (password !== confirmPassword) {
      return alert("입력하신 비밀번호와 재확인 비밀번호가 맞지않습니다.");
    } else if (!yearRegex.test(year)) {
      return alert("년도엔 숫자만 입력이 가능합니다.");
    } else if (!dayRegex.test(day)) {
      return alert("일에는 숫자만 입력이 가능합니다.");
    } else if (!phoneNumberRegex.test(phoneNumber)) {
      return alert("전화번호에는 숫자만 입력이 가능합니다.");
    }

    axios
      .post("/member/join", {
        memberId,
        password,
        name,
        birthDay,
        sex,
        email,
        phoneNumber,
        nickName,
        address1,
        address2,
        grade,
        flatForm,
      })
      .then((response) => {
        if (response.data === true) {
          alert("회원가입이 완료되었습니다.");
          window.location.href = "http://localhost:3000/";
        } else {
          alert("회원가입에 실패하였습니다.");
        }
      })
      .catch((error) => alert(error.response.data.msg));
  };

  //주소 검색 Modal열기
  const openModal = () => {
    setModalOpen(true);
  };

  //주소 검색 Modal닫기
  const closeModal = () => {
    setModalOpen(false);
  };

  //주소 변경
  const handleAddress = (data) => {
    let fullAddress = data.address;
    let extraAddress = "";

    if (data.addressType === "R") {
      if (data.bname !== "") {
        extraAddress += data.bname;
      }
      if (data.buildingName !== "") {
        extraAddress +=
          extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
    }

    //주소세팅
    setAddress1(fullAddress);

    //Modal닫기
    closeModal();
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
          <label>아이디</label>
        </h3>
        <span className="box var_id">
          <input
            type="text"
            id="id"
            onChange={(e) => {
              setMemberId(e.target.value);
            }}
            className="var"
            maxLength="20"
          ></input>
        </span>
        {/* nickname 부분*/}
        <h3 className="join_title">
          <label>별명</label>
        </h3>
        <span className="box var_nickname">
          <input
            type="text"
            id="nickname"
            onChange={(e) => {
              setNickName(e.target.value);
            }}
            className="var"
            maxLength="20"
          ></input>
        </span>
        {/* pw 1차 부분 */}
        <h3 className="join_title">
          <label>비밀번호</label>
        </h3>
        <span className="box var_pass">
          <input
            type="password"
            id="pswd1"
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
            id="pswd2"
            onChange={(e) => {
              setConfirmPassword(e.target.value);
            }}
            className="var"
            maxLength="20"
          ></input>
        </span>
        {/* 이름 부분 */}
        <h3 className="join_title">
          <label>이름</label>
        </h3>
        <span className="box">
          <input
            type="text"
            id="name"
            onChange={(e) => {
              setName(e.target.value);
            }}
            className="var"
            maxLength="20"
          ></input>
        </span>
        {/*생년월일 부분*/}
        <div>
          <h3 className="join_title">
            <label>생년월일</label>
          </h3>
          <div id="bir_wrap">
            <div id="bir_yy">
              <span className="box">
                <input
                  type="text"
                  id="yy"
                  onChange={(e) => {
                    setYear(e.target.value);
                  }}
                  className="var"
                  maxLength="4"
                  placeholder="년(4자)"
                ></input>
              </span>
            </div>
            <div id="bir_mm">
              <span className="box">
                <select
                  id="mm"
                  className="sel"
                  onChange={(e) => {
                    setMonth(e.target.value);
                  }}
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
                  onChange={(e) => {
                    setDay(e.target.value);
                  }}
                  className="var"
                  maxLength="2"
                  placeholder="일"
                ></input>
              </span>
            </div>
          </div>
        </div>
        {/*성별 부분 */}
        <div>
          <h3 className="join_title">
            <label>성별</label>
          </h3>
          <span className="box">
            <select
              id="gender"
              onChange={(e) => {
                setSex(e.target.value);
              }}
              className="sel"
            >
              <option>성별</option>
              <option value="M">남자</option>
              <option value="F">여자</option>
            </select>
          </span>
        </div>
        <div>
          <h3 className="join_title">
            <label>주소</label>
          </h3>
          <span className="addressbox input-group mb-3">
            <input
              type="text"
              id="address1"
              className="var"
              maxLength="100"
              value={address1 || ""}
              readOnly
            />
            <button type="button" id="post" onClick={openModal}>
              <span>주소찾기</span>
            </button>
            <AddressModal
              open={ModalOpen}
              close={closeModal}
              header="주소 검색"
            >
              <DaumPostcode onComplete={handleAddress} />
            </AddressModal>
          </span>
          <h3 className="join_title">
            <label>상세주소</label>
          </h3>
          <span className="box">
            <input
              type="text"
              id="address2"
              onChange={(e) => {
                setAddress2(e.target.value);
              }}
              className="var"
              maxLength="100"
            ></input>
          </span>
        </div>

        {/*이메일 부분 */}
        <div>
          <h3 className="join_title">
            <label>이메일</label>
          </h3>
          <span className="box">
            <input
              type="text"
              id="email"
              onChange={(e) => {
                setEmail(e.target.value);
              }}
              className="var"
              maxLength="100"
            ></input>
          </span>
        </div>
        {/*휴대전화 부분 */}
        <div>
          <h3 className="join_title">
            <label>전화번호</label>
          </h3>
          <span className="box">
            <input
              type="tel"
              id="mobile"
              onChange={(e) => {
                setPhoneNumber(e.target.value);
              }}
              className="var"
              maxLength="11"
              placeholder="전화번호를 입력하세요(ex 01012345678)"
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
