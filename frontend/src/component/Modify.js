import react from "react";
import { Route } from "react-router";


function modify(){

    return(
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
                       className="var"
                       maxLength="20" ></input>
            </span>

            <h3 className="join_title">
                <label for="pswd1">비밀번호 변경</label>
            </h3>
            <span className="box var_pass">
                <input type="password"
                       id="pswd1" 
                       className="var"
                       maxLength="20" ></input>
            </span>
            <h3 className="join_title">
                <label for="pswd1">비밀번호 확인</label>
            </h3>
            <span className="box var_pass_check">
                <input type="password"
                       id="pswd2" 
                       className="var"
                       maxLength="20" ></input>
            </span>

            <h3 className="join_title">
                    <label for="phonenum">휴대전화 변경</label>
            </h3>
            <span className="box">
                    <input type = "tel" 
                           id="mobile"
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
                           className="var" 
                           maxLength="100"></input>
            </span>

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
                           className="var"
                           maxLength="100"></input> 
            </span>

            <h3 className="join_title">
                <label for="adress2">상세주소</label>
            </h3>
            <span className="box">
                    <input type="text"
                           id="address2"
                           className="var"
                           maxLength="100"></input>
            </span>
            <div className="btn_area">
                <button type="submit" id="mypage_btn">
                    <span>정보 수정</span>
                </button>
            </div>
        </div>
    );

}

export default modify;