import React, { useState } from "react";
import { Route } from "react-router";
import AddressModal from "./AddressModal";
import DaumPostcode from "react-daum-postcode";
import axios from "axios";


function Modify(){
    const [password, setPassword] = useState(null);
    const [confirmPassword, setConfirmPassword] = useState(null);
    const [nickName, setNickName] = useState(null);
    const [address1, setAddress1] = useState(null);
    const [address2, setAddress2] = useState(null);
    const [email, setEmail] = useState(null);
    const [phoneNumber, setPhoneNumber] = useState(null);
      //주소 Modal
    const [ModalOpen, setModalOpen] = useState(false);


        //주소 검색 Modal열기
    const openModal = () => {
        setModalOpen(true);
    };
        //주소 검색 Modal닫기
    const closeModal = () => {
        setModalOpen(false);
    };

    // 정보수정(서버통신)
    const modifyHandler = async(event) => {
        event.preventDefault();

        axios
        .post("/member/changemember",{
            password,
            confirmPassword,
            nickName,
            address1,
            address2,
            email,
            phoneNumber

        })
        .then((response) => {
            if (response.data === true) {
                alert("정보수정이 완료되었습니다.");
                window.location.href = "http://localhost:3000/";
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




    return(
      <form onSubmit={modifyHandler}>  
        <div id="content">
            <div id="header">
                <a href="/" target="_self" title="선빵 메인 페이지 보러가기">
                    <span id="sunbbang">선 빵</span>
                </a>
            </div>
            <h3 className="join_title">
                <label for="nickname">별명 변경</label>
            </h3>
            <span className="box var_nickname">
                <input type="text"
                       id="nickname"
                       onChange={(event) => {
                           setNickName(event.target.value);
                       }}
                       className="var"
                       maxLength="20" ></input>
            </span>

            <h3 className="join_title">
                <label for="pswd1">비밀번호 변경</label>
            </h3>
            <span className="box var_pass">
                <input type="password"
                       id="pswd1" 
                       onChange={(event) => {
                           setPassword(event.target.value);
                       }}
                       className="var"
                       maxLength="20" ></input>
            </span>
            <h3 className="join_title">
                <label for="pswd1">비밀번호 확인</label>
            </h3>
            <span className="box var_pass_check">
                <input type="password"
                       id="pswd2"
                       onChange={(event) => {
                           setConfirmPassword(event.target.value);
                       }} 
                       className="var"
                       maxLength="20" ></input>
            </span>

            <h3 className="join_title">
                    <label for="phonenum">휴대전화 변경</label>
            </h3>
            <span className="box">
                    <input type = "tel" 
                           id="mobile"
                           onChange={(event) => {
                               setPhoneNumber(event.target.value);
                           }}
                           className="var" 
                           maxLength="16" 
                           placeholder="-를 빼고 입력하세요."></input>
             </span>

            <h3 className="join_title">
                    <label for="email">이메일 변경</label>
            </h3>
            <span className="box">
                    <input type="text" 
                           id="email"
                           onChange={(event) => {
                               setEmail(event.target.value);
                           }}
                           className="var" 
                           maxLength="100"></input>
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
                <AddressModal
                open={ModalOpen}
                close={closeModal}
                header="주소 검색"
                >
                <DaumPostcode onComplete={handleAddress} />
                </AddressModal>
            </span>

            <h3 className="join_title">
                <label for="adress2">상세주소</label>
            </h3>
            <span className="box">
                    <input type="text"
                           id="address2"
                           onChange={(event) => {
                               setAddress2(event.target.value);
                           }}
                           className="var"
                           maxLength="100"></input>
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