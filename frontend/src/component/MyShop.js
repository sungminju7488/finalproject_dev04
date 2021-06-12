import axios from "axios";
import React, { useEffect } from "react";
import auth from "../Logic/Auth";
import "../css/MyShop.css";

function MyShop() {
  useEffect(() => {
    //화면에 들어올시 매장 정보를 조회한다.

    const memberSeq = auth.memberSeq;

    axios
      .post("/bakery/myShop", { memberSeq })
      .then((res) => {
        alert(res.data);
      })
      .catch((err) => alert(err.response.data.msg));
  }, []);

  return (
    <div id="content">
      <div id="header">
        <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
          <span id="sunbbang">선 빵</span>
        </a>
      </div>
      <table className="shoptable">
        <tbody>
          <tr>
            <td rowSpan="7">사진</td>
            <td>매장 이름</td>
            <td>가게이름</td>
          </tr>
          <tr>
            <td>매장 주소</td>
            <td>경기도</td>
          </tr>
          <tr>
            <td>매장 연락처</td>
            <td>031</td>
          </tr>
          <tr>
            <td>운영시간</td>
            <td>AM 10:00 ~ PM 8:00</td>
          </tr>
          <tr>
            <td>정기휴무</td>
            <td>매주 월요일/공휴일</td>
          </tr>
          <tr>
            <td>취식여부</td>
            <td>0/1</td>
          </tr>
          <tr>
            <td>게시판 활성화</td>
            <td>유/무</td>
          </tr>
        </tbody>
      </table>
      <table className="identificationtable">
        <tbody>
          <tr>
            <td>사업자 명</td>
            <td>이름</td>
          </tr>
          <tr>
            <td>사업자 번호</td>
            <td>1065405640</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}

export default MyShop;
