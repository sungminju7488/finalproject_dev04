import react from "react";
import { Container } from "react-bootstrap";
import { Link } from "react-router-dom";
import '../css/Mypage.css';


function mypage(){


    return(
        <div id="content">
            <div id="header">
                <a href="/" target="_self" title="선빵 메인 페이지 보러가기">
                    <span id="sunbbang">선 빵</span>
                </a>
            </div>
            <h3 className="mypage_title">
                <label for="id">아이디</label>
            </h3>
            <span className="box var_id"></span>

            <h3 className="mypage_title">
                <label for="name">이름</label>
            </h3>
            <span className="box var_name"></span>

            <h3 className="mypage_title">
                <label for="nickname">별명</label>
            </h3>
            <span className="box var_nickname"></span>

            <h3 className="mypage_title">
                <label for="sex">성별</label>
            </h3>
            <span className="box var_sex" ></span>

            <h3 className="mypage_title">
                <label for="birthday">생년월일</label>
            </h3>
            <span className="box var_birthday"></span>

            <h3 className="mypage_title">
                <label for="email">이메일</label>
            </h3>
            <span className="box var_email"></span>

            <h3 className="mypage_title">
                <label for="phonenum">휴대전화</label>
            </h3>
            <span className="box var_phonenum"></span>

            <h3 className="mypage_title">
                <label for="address1">주소</label>
            </h3>
            <span className="box var_address1"></span>

            <h3 className="mypage_title">
                <label for="address2">상세주소</label>
            </h3>
            <span className="box var_address2"></span>

            <h3 className="mypage_title">
                <label for="grade">회원구분</label>
            </h3>
            <span className="box var_grade"></span>

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

export default mypage;