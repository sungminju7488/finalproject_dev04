import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import '../css/Mypage.css';
import Auth from "../Logic/Auth";


function MyPage(){
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

    const init = (data) => {
        setMemberId(data.memberId);
        setName(data.name);
        setNickName(data.nickName);
        setSex(data.sex);
        setBirthDay(data.birthDay);
        setEmail(data.email);
        setPhoneNumber(data.phoneNumber);
        setAddress1(data.address1)
        setAddress2(data.address2);
        setGrade(data.grade);
    };

    useEffect(() => {
        const memberSeq = Auth.memberSeq;

        axios
        .post("/member/mypage", { memberSeq })
        .then((res) => {
            init(res.data);
        })
        .catch((err) => alert(err.response.data.msg));


    }, []);


    return(
        <div id="content">
            <div id="header">
                <a href="/" target="_self" title="선빵 메인 페이지 보러가기">
                    <span id="sunbbang">마이페이지</span>
                </a>
            </div>
            <h3 className="mypage_title">
                <label for="id">아이디</label>
            </h3>
            <span className="box var_id" >
                <input
                 type="text"
                 id="memberId"
                 value = {memberId}
                 className="var"
                 readOnly>

                 </input>
            </span>

            <h3 className="mypage_title">
                <label for="name">이름</label>
            </h3>
            <span className="box var_name" >
            <input
                 type="text"
                 id="name"
                 value = {name}
                 className="var"
                 readOnly>
                     
                 </input>
            </span>

            <h3 className="mypage_title">
                <label for="nickname">별명</label>
            </h3>
            <span className="box var_nickname" >
            <input
                 type="text"
                 id="nickName"
                 value = {nickName}
                 className="var"
                 readOnly>
                     
                 </input>
            </span>

            <h3 className="mypage_title">
                <label for="sex">성별</label>
            </h3>
            <span className="box var_sex" >
            <input
                 type="text"
                 id="sex"
                 value = {sex}
                 className="var"
                 readOnly>
                     
                 </input>
            </span>

            <h3 className="mypage_title">
                <label for="birthday">생년월일</label>
            </h3>
            <span className="box var_birthday" >
            <input
                 type="text"
                 id="birthDay"
                 value = {birthDay}
                 className="var"
                 readOnly>
                     
                 </input>
            </span>

            <h3 className="mypage_title">
                <label for="email">이메일</label>
            </h3>
            <span className="box var_email" >
            <input
                 type="text"
                 id="email"
                 value = {email}
                 className="var"
                 readOnly>
                     
                 </input>
            </span>

            <h3 className="mypage_title">
                <label for="phonenum">휴대전화</label>
            </h3>
            <span className="box var_phonenum" >
            <input
                 type="text"
                 id="phoneNumber"
                 value = {phoneNumber}
                 className="var"
                 readOnly>
                     
                 </input>
            </span>

            <h3 className="mypage_title">
                <label for="address1">주소</label>
            </h3>
            <span className="box var_address1" >
            <input
                 type="text"
                 id="address1"
                 value = {address1}
                 className="var"
                 readOnly>
                     
                 </input>
            </span>

            <h3 className="mypage_title">
                <label for="address2">상세주소</label>
            </h3>
            <span className="box var_address2" >
            <input
                 type="text"
                 id="address2"
                 value = {address2}
                 className="var"
                 readOnly>
                     
                 </input>
            </span>

            <h3 className="mypage_title">
                <label for="grade">회원구분</label>
            </h3>
            <span className="box var_grade" >
            <input
                 type="text"
                 id="grade"
                 value = {grade}
                 className="var"
                 readOnly>
                     
                 </input>
            </span>

            <div id="btn_area">
              <Link to="/member/changememberpage">    
                <button type="button" id="mypage_btn">
                    <span>정보를 바꾸고싶어요</span>
                </button>
              </Link>  
            </div>

        </div>

    );


    


}

export default MyPage;