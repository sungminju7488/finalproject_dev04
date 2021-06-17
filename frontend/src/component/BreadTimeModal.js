import axios from "axios";
import { Fragment, useEffect, useState } from "react";
import { Form, Table } from "react-bootstrap";
import { Link } from "react-router-dom";
import "../css/BreadTimeModal.css";
import auth from "../Logic/Auth";

const BreadTimeModal = (props) => {
  const { open, close, bakeryData, breadData } = props;
  const [alarmList, setAlarmList] = useState([]);

  useEffect(() => {
    updateAlarmList();
  }, []);

  function updateAlarmList() {
    if (auth.alarmSet === null || auth.alarmSet === undefined) return;
    let str = auth.alarmSet;
    let tempAlarmList = [];
    let strList = str.split(",");
    for (let i = 0; i < strList.length; i++) {
      if (
        strList[i] !== null ||
        strList[i] !== undefined ||
        strList[i] !== ""
      ) {
        tempAlarmList.push(parseInt(strList[i]));
      }
    }
    setAlarmList(tempAlarmList);
  }

  const equalsNumber = (arr, value) => {
    if (arr.length === 0) return false;
    for (let i = 0; i < arr.length; i++) {
      if (arr[i] == value) return true;
    }
    return false;
  };

  const handleOnChange = (event, Seq) => {
    event.preventDefault();

    console.log("alarmList : " + alarmList);
    console.log("Seq : " + Seq);
    console.log(equalsNumber(alarmList, Seq));

    if (equalsNumber(alarmList, Seq)) return alert("이미 등록되어있습니다.");

    const formData = new FormData();
    formData.append("foodSeq", Seq);
    formData.append("memberSeq", auth.memberSeq);

    axios
      .post("/bakery/setAlarm", formData)
      .then((res) => {
        if (res.data !== null) {
          auth.setAuth(res.data);
          updateAlarmList();
          alert("해당 빵이 알람등록되었습니다.");
        } else {
          alert("알람 등록에 실패하였습니다.");
        }
      })
      .catch((err) => alert(err.response.data.msg));
  };

  const viewBreadTimeTable = () => {
    if (!open) return;

    const result = [];
    result.push(
      <thead>
        <tr>
          <th>알림등록</th>
          <th>빵 이름</th>
          <th>나오는 시간</th>
          <th>가격</th>
        </tr>
      </thead>
    );
    for (let i = 0; i < breadData.length; i++) {
      result.push(
        <tr>
          <td>
            <button
              onClick={(e) => {
                handleOnChange(e, breadData[i].foodSeq);
              }}
            >
              등록
            </button>
          </td>
          <td>{breadData[i].foodName}</td>
          <td>{breadData[i].saleTime}</td>
          <td>{breadData[i].price}</td>
        </tr>
      );
    }
    return result;
  };

  function MoveArticleListHandler(BakeryData) {
    sessionStorage.setItem("ArticleCopRegNum", BakeryData.copRegNum);
    sessionStorage.setItem("ArticleStoreName", BakeryData.storeName);
    window.location.href = "/article/bakeryarticlelistpage";
  }

  function ViewBoardBtn(props) {
    if (props.BoardSet === "T") {
      return (
        <Fragment>
          <button onClick={() => MoveArticleListHandler(bakeryData)}>
            리뷰 게시판
          </button>
        </Fragment>
      );
    } else {
      return "";
    }
  }

  return (
    //modal이 열릴 때 openModal 클래스가 생성된다.
    <div className={open ? "openModal modal" : "modal"}>
      {open ? (
        <section>
          <header>
            {bakeryData.storeName}
            <button className="close" onClick={close}>
              &times;
            </button>
          </header>
          <main>
            {/* 회사정보 */}
            <div>
              전화번호: {bakeryData.storeContact}
              <br />
              주소:{" "}
              {bakeryData.storeAddress2 !== null ||
              bakeryData.storeAddress2 !== undefined
                ? bakeryData.storeAddress1 + " " + bakeryData.storeAddress2
                : bakeryData.storeAddress1}
              <br />
              영업시간: {bakeryData.businessHour}
              <br />
              정기휴무: {bakeryData.holiday}
              <br />
              <br />
            </div>
            {/* 게시판 버튼 */}
            <ViewBoardBtn BoardSet={bakeryData.boardSet} />
            {/* 빵나오는 시간 */}
            <Table striped bordered size="sm">
              {viewBreadTimeTable()}
            </Table>
          </main>
          <footer>
            <button className="close" onClick={close}>
              &times;
            </button>
          </footer>
        </section>
      ) : null}
    </div>
  );
};

export default BreadTimeModal;
