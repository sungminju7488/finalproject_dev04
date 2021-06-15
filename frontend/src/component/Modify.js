import React, { useEffect, useState } from "react";
import AddressModal from "./AddressModal";
import DaumPostcode from "react-daum-postcode";
import axios from "axios";
import auth from "../Logic/Auth";

function Modify({ history }) {
  const [memberId, setMemberId] = useState(null);
  const [savePassword, setSavePassword] = useState(null);
  const [newPassword, setNewPassword] = useState(null);
  const [confirmPassword, setConfirmPassword] = useState(null);
  const [name, setName] = useState(null);
  const [nickName, setNickName] = useState(null);
  const [sex, setSex] = useState(null);
  const [birthDay, setBirthDay] = useState(null);
  const [email, setEmail] = useState(null);
  const [phoneNumber, setPhoneNumber] = useState(null);
  const [address1, setAddress1] = useState(null);
  const [address2, setAddress2] = useState(null);
  const [grade, setGrade] = useState(null);
  const [flatForm, setFlatForm] = useState(null);
  //주소 Modal
  const [ModalOpen, setModalOpen] = useState(false);

  const init = (data) => {
    setMemberId(data.memberId);
    setSavePassword(data.password);
    setName(data.name);
    setNickName(data.nickName);
    setSex(data.sex);
    setBirthDay(data.birthDay);
    setEmail(data.email);
    setPhoneNumber(data.phoneNumber);
    setAddress1(data.address1);
    setAddress2(data.address2);
    setFlatForm(data.flatForm);
    setGrade(data.grade);
  };

  useEffect(() => {
    const memberId = auth.memberId;

    axios
      .post("/myPage", { memberId })
      .then((res) => {
        init(res.data);
      })
      .catch((err) => alert(err.response.data.msg));
  }, []);

  //주소 검색 Modal열기
  const openModal = () => {
    setModalOpen(true);
  };
  //주소 검색 Modal닫기
  const closeModal = () => {
    setModalOpen(false);
  };

  // 정보수정(서버통신)
  const modifyHandler = async (event) => {
    event.preventDefault();

    const phoneNumberRegex = /^\d{2}/;
    //정규식 및 가입불가 예외처리
    if (newPassword !== confirmPassword) {
      return alert("입력하신 새 비밀번호와 재확인 비밀번호가 맞지않습니다.");
    } else if (!phoneNumberRegex.test(phoneNumber)) {
      return alert("전화번호에는 숫자만 입력이 가능합니다.");
    }

    console.log("savePassword : " + savePassword);

    const memberSeq = auth.memberSeq;
    let password = newPassword;
    if (password === null || password === undefined) password = savePassword;

    axios
      .post("/changeMember", {
        memberSeq,
        memberId,
        password,
        name,
        nickName,
        address1,
        address2,
        sex,
        birthDay,
        email,
        phoneNumber,
        grade,
        flatForm,
      })
      .then((response) => {
        if (response.data === true) {
          alert("정보수정이 완료되었습니다.");
          history.goBack();
        } else {
          alert("정보수정에 실패하였습니다.");
        }
      })
      .catch((error) => alert(error.response.data.msg));
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
    <form onSubmit={modifyHandler}>
      <div id="content">
        <div id="header">
          <a href="/" target="_self" title="선빵 메인 페이지 보러가기">
            <span id="sunbbang">정보수정</span>
          </a>
        </div>
        <h3 className="join_title">
          <label>별명</label>
        </h3>
        <span className="box var_nickname">
          <input
            type="text"
            id="nickname"
            value={nickName || ""}
            onChange={(event) => {
              setNickName(event.target.value);
            }}
            className="var"
            maxLength="20"
          ></input>
        </span>

        <h3 className="join_title">
          <label>새 비밀번호</label>
        </h3>
        <span className="box var_pass">
          <input
            type="password"
            id="pswd1"
            onChange={(event) => {
              setNewPassword(event.target.value);
            }}
            className="var"
            maxLength="20"
            placeholder="변경을 원하지 않으면 입력하지마세요."
          ></input>
        </span>
        <h3 className="join_title">
          <label>새 비밀번호 확인</label>
        </h3>
        <span className="box var_pass_check">
          <input
            type="password"
            id="pswd2"
            onChange={(event) => {
              setConfirmPassword(event.target.value);
            }}
            className="var"
            maxLength="20"
          ></input>
        </span>

        <h3 className="join_title">
          <label>휴대전화 변경</label>
        </h3>
        <span className="box">
          <input
            type="tel"
            id="mobile"
            value={phoneNumber || ""}
            onChange={(event) => {
              setPhoneNumber(event.target.value);
            }}
            className="var"
            maxLength="16"
            placeholder="-를 빼고 입력하세요."
          ></input>
        </span>

        <h3 className="join_title">
          <label>이메일</label>
        </h3>
        <span className="box">
          <input
            type="text"
            id="email"
            value={email || ""}
            onChange={(event) => {
              setEmail(event.target.value);
            }}
            className="var"
            maxLength="100"
          ></input>
        </span>

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
          <AddressModal open={ModalOpen} close={closeModal} header="주소 검색">
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
            onChange={(event) => {
              setAddress2(event.target.value);
            }}
            value={address2 || ""}
            className="var"
            maxLength="100"
          ></input>
        </span>
        <div className="btn_area">
          <button type="submit" id="mypage_btn">
            <span>정보 수정</span>
          </button>
        </div>
      </div>
    </form>
  );
}

export default Modify;
