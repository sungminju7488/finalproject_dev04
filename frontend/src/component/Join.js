import axios from "axios";
import React, { Component, useState } from "react";
import '../css/Join.css';
import DaumPostcode from 'react-daum-postcode';

function Join() {

    const [memberId, setmemberId] = useState(null);
    const [Password, setPassword] = useState(null);
    const [confirmPassword, setConfirmPassword] = useState(null);
    const [name, setName] = useState(null);
    const [year, setYear] = useState(null);
    const [month, setMonth] = useState(null);
    const [day, setDay] = useState(null);
    const [sex, setSex] = useState(null);
    const [email, setEmail] = useState(null);
    const [phoneNumber, setPhonenumber] = useState(null);
    const [nickName, setNickName] = useState(null);
    const [address1, setAddress1] = useState(null);
    const [address2, setAddress2] = useState(null);
    const [grade, setGrade] = useState(null);
    const birthDay = year + "-" + month + "-" + day;
    
    const joinHandler = async (event) => {
       event.preventDefault();

       axios.post("/member/joinpage",{
        memberId,
        Password,
        confirmPassword,
        name,
        birthDay,
        sex,
        email,
        phoneNumber,
        nickName,
        address1,
        address2,
        grade,
      
        
        }).then((response) => {
        alert(response.data.nickName);
     }).catch((error) => alert(error.response.data.msg));

    };

    const idHandler = (event) => {
        setmemberId(event.target.value)
        
    } 

    const PwHandler = (event) => {
        setPassword(event.target.value)
    } 

    const PwHandler2 = (event) => {
        setConfirmPassword(event.target.value)
    }

    const NameHandler = (event) => {
        setName(event.target.value)
    }
    const YearHandler = (event) => {
        setYear(event.target.value)
    }
    const MonthHandler = (event) => {
        setMonth(event.target.value)
    }
    const DayHandler = (event) => {
        setDay(event.target.value)
    }
    const SexHandler = (event) => {
        setSex(event.target.value)
    }
    const EmailHandler = (event) => {
        setEmail(event.target.value)
    }
    const PhonenumHandler = (event) => {
        setPhonenumber(event.target.value)
    }
    const NicknameHandler = (event) => {
        setNickName(event.target.value)
    }
    const AddressHandler = (event) =>{
        setAddress1(event.target.value)
    }
    const Address2Handler = (event) => {
        setAddress2(event.target.value)
    }
    const GreadeHandler = (event) => {
        setGrade(event.target.value)
    }

    /*
    const pcode = () => {
                pcode({
           oncomplete: function(data){

            var addr = '';
            var extraAddr = '';

            if (data.userSelectedType === 'R'){
                addr = data.roadAddress;
            } else {
                addr = data.jibunAddress;
            }

            if (data.userSelectedType === 'R'){

                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }

                if (data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                
                if (extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }

                document.getElementById("address2").value = extraAddr;
                
            } else {
                document.getElementById("address2").value = '';
            }

                document.getElementById("address1").value = addr;

                document.getElementById("address2").focus();

           }
       }).open();
    } */





    return(
      <form onSubmit={joinHandler}>  
        <div id="content">              
            {/* header부분 */}
            <div id="header">
            <a href="/" target="_self" title="선빵 메인 페이지 보러가기">
                <span id="sunbbang">선 빵</span>
            </a>
            </div>   
            {/* id 부분*/}
            <h3 className="join_title">
                <label for="id">아이디</label>
            </h3>
            <span className="box var_id">
                <input type="text" id="id" value={memberId}  onChange={idHandler} className="var" maxLength="20"></input>
            </span>
            {/* nickname 부분*/}
            <h3 className="join_title">
                <label for="nickname">별명</label>
            </h3>
            <span className="box var_nickname">
                <input type="text"
                       id="nickname"
                       value={nickName}
                       onChange={NicknameHandler}
                       className="var"
                       maxLength="20"></input>
            </span>
            {/* pw 1차 부분 */}
            <h3 className="join_title">
                <label for="pswd1">비밀번호</label>
            </h3>
            <span className="box var_pass">
                <input type="password"
                       id="pswd1" 
                       value={Password}
                       onChange={PwHandler}
                       className="var"
                       maxLength="20" ></input>
            </span>
            {/* pw 2차 부분 */}
            <h3 className="join_title">
                <label for="pawd2">비밀번호  재확인</label>
            </h3>
            <span className="box var_pass_check">
                <input type="password"
                        id="pswd2"
                        value={confirmPassword} 
                        onChange={PwHandler2}
                        className="var" 
                        maxLength="20"></input>
            </span>
            {/* 이름 부분 */}
            <h3 className="join_title">
                <label for="name">이름</label>
            </h3>
            <span className="box">
                <input type="text"
                       id="name"
                       value={name}
                       onChange={NameHandler} 
                       className="var" 
                       maxLength="20"></input>
            </span>
            {/*생년월일 부분*/}
            <div>
                <h3 className="join_title">
                    <label for="yy">생년월일</label>
                </h3>
                <div id="bir_wrap">
                <div id="bir_yy">
                    <span className="box">
                        <input type="text"
                         id="yy" 
                         value= {year}
                         onChange={YearHandler}
                         className="var" 
                         maxLength="4" 
                         placeholder="년(4자)"></input>
                    </span>
                </div>
                <div id="bir_mm">
                    <span className="box">
                        <select id="mm"
                                className="sel"
                                value={month}
                                onChange={MonthHandler}>
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
                        <input type="text"
                               id="dd"
                               value={day}
                               onChange={DayHandler} 
                               className="var" 
                               maxLength="2" 
                               placeholder="일"></input>
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
                    <select id="gender"
                            value={sex}
                            onChange={SexHandler} 
                            className="sel">
                        <option>성별</option>
                        <option value="M">남자</option>
                        <option value="F">여자</option>
                    </select>
                </span>
            </div>
            {/*주소 부분 */}
            <div>
           {/* <DaumPostcode
                onComplete={pcode}
           /> */}
              <div id="postbtn"> 
                <button type="text" id="post">
                    <span>주소찾기</span>
                </button>
              </div>                  
                <h3 className="join_title">
                    <label for="address1">주소</label>
                </h3>
                <span className="box">
                    <input type="text"
                           id="address1"
                           value={address1}
                           onChange={AddressHandler}
                           className="var"
                           maxLength="100"></input> 
                </span>
                <h3 className="join_title">
                    <label for="adress2">상세주소</label>
                </h3>
                <span className="box">
                    <input type="text"
                           id="address2"
                           value={address2}
                           onChange={Address2Handler}
                           className="var"
                           maxLength="100"></input>
                </span>
                 
            </div>
               
            {/*이메일 부분 */}
            <div>
                <h3 className="join_title">
                    <label for="email">이메일</label>
                </h3>
                <span className="box">
                    <input type="text" 
                           id="email"
                           value={email}
                           onChange={EmailHandler} 
                           className="var" 
                           maxLength="100"></input>
                </span>
            </div>
            {/*휴대전화 부분 */}
            <div>
                <h3 className="join_title">
                    <label for="phonenum">휴대전화</label>
                </h3>
                <span className="box">
                    <input type = "tel" 
                           id="mobile"
                           value={phoneNumber}
                           onChange={PhonenumHandler} 
                           className="var" 
                           maxLength="16" 
                           placeholder="-를 빼고 입력하세요."></input>
                </span>
            </div>
            {/*일반회원,사업자구분 */}
            <div>
                <h3 className="join_title">
                    <label for="grade">회원구분</label>
                </h3>
                <span className="box">
                    <select id="grade"
                            value={grade}
                            onChange={GreadeHandler}
                            className="sel">
                                <option>회원선택</option>
                                <option value="0">일반회원</option>
                                <option value="1">사업자 회원</option>
                            </select>
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


