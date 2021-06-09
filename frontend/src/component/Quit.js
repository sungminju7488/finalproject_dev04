import react from "react";
import "../css/Quit.css";
import { Link } from "react-router-dom";


function quit(){
    return(
        <div id="content">
            <div id="header">
                <a href="/" target="_self" title="선빵 메인 페이지 보러가기">
                    <span id="sunbbang">선 빵</span>
                </a>
            </div>
            <h1>
                회 원 탈 퇴
            </h1>
            <h3 className="join_title">
                <label for="id">아이디</label>
            </h3>
            <span className="box var_id">
                <input type="text"
                       id="id" 
                       className="var" 
                       maxLength="20" 
                       placeholder="아이디를 입력해주세요.">
                </input>
            </span>

            <h3 className="join_title">
                <label for="pswd1">비밀번호</label>
            </h3>
            <span className="box var_pass">
                <input type="password"
                       id="pswd1" 
                       className="var"
                       maxLength="20" 
                       placeholder="비밀번호를 입력해주세요."></input>
            </span>

            <h3 className="join_title">
                <label for="pawd2">비밀번호  재확인</label>
            </h3>
            <span className="box var_pass_check">
                <input type="password"
                        id="pswd2"
                        className="var" 
                        maxLength="20"
                        placeholder="비밀번호를 입력해주세요."></input>
            </span>

            <div className="btn_area">
              <Link to="/">    
                <button type="submit" id="btnCancel">
                    <span>취 소</span>
                </button>
              </Link>  
                <button type="submit" id="btnQuit">
                    <span>회 원 탈 퇴</span>
                </button>
            </div>
            

        </div> 
    );
}


export default quit;