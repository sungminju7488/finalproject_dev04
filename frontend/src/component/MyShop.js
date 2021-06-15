import axios from "axios";
import React, { useEffect, useState } from "react";
import auth from "../Logic/Auth";
import "../css/MyShop.css";
import { Link } from "react-router-dom";

function MyShop() {
  const [copRegNum, setCopRegNum] = useState(0);
  const [manager, setManager] = useState("");
  const [storeName, setStoreName] = useState("");
  const [storeAddress1, setStoreAddress1] = useState("");
  const [storeAddress2, setStoreAddress2] = useState("");
  const [storeContact, setStoreContant] = useState("");
  const [businessHour, setBusinessHour] = useState("");
  const [holiday, setHoliday] = useState("");
  const [specialHoliday, setSpecialHoliday] = useState("");
  const [eatable, setEatable] = useState("");
  const [boardSet, setBoardSet] = useState("");
  const [bakeryPath, setBakeryPath] = useState("");

  const init = (data) => {
    setCopRegNum(data.copRegNum);
    setManager(data.manager);
    setStoreName(data.storeName);
    setStoreAddress1(data.storeAddress1);
    setStoreAddress2(data.storeAddress2);
    setStoreContant(data.storeContact);
    setBusinessHour(data.businessHour);
    setHoliday(data.holiday);
    setSpecialHoliday(data.specialHoliday);
    setEatable(data.eatable);
    setBoardSet(data.boardSet);
    setBakeryPath(data.bakeryPath);
  };

  useEffect(() => {
    //화면에 들어올시 매장 정보를 조회한다.

    const memberSeq = auth.memberSeq;

    axios
      .post("/bakery/myShop", { memberSeq })
      .then((res) => {
        init(res.data);
      })
      .catch((err) => alert(err.response.data.msg));
  }, []);

  return (
    <div id="content">
      <div id="header">
        <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
          <span id="sunbbang">매장정보</span>
        </a>
      </div>
      {/* 매장 이미지 */}
      <img src={bakeryPath} style={{ width: "100%", height: "460px" }} />
      {/* copRegNum 부분*/}
      <h3 className="join_title">
        <label>사업자번호</label>
      </h3>
      <span className="box var_id">
        <input
          type="text"
          id="copRegNum"
          value={copRegNum}
          className="var"
          readOnly
        ></input>
      </span>
      {/* manager 부분*/}
      <h3 className="join_title">
        <label>사업자명</label>
      </h3>
      <span className="box var_nickname">
        <input
          type="text"
          id="manager"
          value={manager || ""}
          className="var"
          readOnly
        ></input>
      </span>
      {/* storeName 부분 */}
      <h3 className="join_title">
        <label>매장명</label>
      </h3>
      <span className="box var_pass">
        <input
          type="text"
          id="storeName"
          className="var"
          value={storeName}
          readOnly
        ></input>
      </span>
      {/* storeAddress1 부분 */}
      <div>
        <h3 className="join_title">
          <label>매장주소</label>
        </h3>
        <span className="addressbox input-group mb-3">
          <input
            type="text"
            id="storeAddress1"
            className="var"
            maxLength="100"
            value={storeAddress1 + " " + storeAddress2 || ""}
            readOnly
          />
        </span>
      </div>
      {/* storeContact 부분 */}
      <h3 className="join_title">
        <label>매장 전화번호</label>
      </h3>
      <span className="box var_pass_check">
        <input
          type="tel"
          id="storeContact"
          className="var"
          value={storeContact || ""}
          readOnly
        ></input>
      </span>
      {/* businessHour 부분 */}
      <h3 className="join_title">
        <label>영업시간</label>
      </h3>
      <span className="box">
        <input
          type="text"
          id="businessHour"
          className="var"
          value={businessHour || ""}
          readOnly
        ></input>
      </span>
      {/* holiday 부분*/}
      <div>
        <h3 className="join_title">
          <label>정기휴무</label>
        </h3>
        <span className="box">
          <input
            type="text"
            id="holiday"
            className="var"
            value={holiday || ""}
            readOnly
          ></input>
        </span>
      </div>
      {/* specialHoliday 부분*/}
      <div>
        <h3 className="join_title">
          <label>임시휴무</label>
        </h3>
        <span className="box">
          <input
            type="text"
            id="specialHoliday"
            className="var"
            value={specialHoliday || ""}
            readOnly
          ></input>
        </span>
      </div>
      {/* eatable 부분 */}
      <div>
        <h3 className="join_title">
          <label>취식여부</label>
        </h3>
        <span className="box">
          <input
            id="eatable"
            className="var"
            value={eatable === "F" ? "불가능" : "가능"}
            readOnly
          />
        </span>
      </div>

      {/*boardSet 부분 */}
      <div>
        <h3 className="join_title">
          <label>게시판활성화</label>
        </h3>
        <span className="box">
          <input
            id="boardSet"
            className="var"
            value={boardSet === "F" ? "비활성화" : "활성화"}
            readOnly
          />
        </span>
      </div>
      {/* 매장 정보 변경 버튼 */}
      <div className="btn_area">
        <Link to="/bakery/changebakerypage">
          <button type="button" id="btnJoin">
            <span>매장 정보 변경</span>
          </button>
        </Link>
      </div>
    </div>
  );
}

export default MyShop;