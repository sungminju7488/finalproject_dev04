import { Fragment, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import auth from "../Logic/Auth";
import "../css/SearchFood.css";
import { Table } from "react-bootstrap";
import axios from "axios";
import ReactPaginate from "react-js-pagination";

function SearchFood() {
  const [keyword, setKeyword] = useState("");
  const [page, setPage] = useState(0);
  const [total, setTotal] = useState(0);
  const [perPage, setPerPage] = useState(0);
  const [breadList, setBreadList] = useState([]);
  const [alarmList, setAlarmList] = useState([]);

  useEffect(() => {
    updateAlarmList();
    handlePage(1);
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

  function handlePage(No) {
    const formData = new FormData();
    formData.append("keyword", keyword);
    formData.append("pageNo", No - 1);

    axios
      .post("/bakery/searchFood", formData)
      .then((res) => {
        console.log(res.data);
        SettingPage(
          res.data.pageable.page,
          res.data.total,
          res.data.pageable.size
        );
        setBreadList(res.data.content);
      })
      .catch((err) => alert(err.response.data.msg));
  }

  function SettingPage(_page, _total, _perPage) {
    setPage(_page);
    setTotal(_total);
    setPerPage(_perPage);
  }

  //로그인 상태에 따른 태그 구분
  function ViewLogFunc(Props) {
    const isloggedIn = Props.isloggedIn;
    if (isloggedIn) return <LoginFunc />;
    else return <NotLoginFunc />;
  }

  //로그인 상태일때 사용되는 태그 함수
  function LoginFunc() {
    const handleLogout = () => {
      alert("로그아웃되셨습니다.");
      auth.logout();
      window.location.reload();
    };

    return (
      <Fragment>
        <GradeFunc grade={auth.grade} />
        <li className="nav-item">
          <Link to="/member/mypagepage" className="nav-link">
            마이페이지
          </Link>
        </li>
        <li className="nav-item">
          <button className="nav-link logoutBtn" onClick={handleLogout}>
            로그아웃
          </button>
        </li>
      </Fragment>
    );
  }

  //로그인이 아닐 때 사용되는 태그 함수
  function NotLoginFunc() {
    return (
      <Fragment>
        <li className="nav-item">
          <Link to="/member/joinpage" className="nav-link">
            회원가입
          </Link>
        </li>
        <li className="nav-item">
          <Link to="/member/loginpage" className="nav-link">
            로그인
          </Link>
        </li>
      </Fragment>
    );
  }

  //사용자 등급에 따른 태그 구분
  function GradeFunc(Props) {
    const userGrade = Props.grade;
    if (userGrade === "0") return <MemberFunc />;
    else if (userGrade === "1") return <IdentificationFunc />;
  }

  //일반 회원 태그
  function MemberFunc() {
    return (
      <Fragment>
        <li className="nav-item">
          <span className="grade grade-member nav-link">일반회원</span>
        </li>
        <li className="nav-item">
          <Link to="/member/changegradeconfirmpage" className="nav-link">
            사업자전환
          </Link>
        </li>
      </Fragment>
    );
  }

  //일반 회원 태그
  function IdentificationFunc() {
    return (
      <Fragment>
        <li className="nav-item">
          <span className="grade grade-identification nav-link">
            사업자회원
          </span>
        </li>
        <li className="nav-item">
          <Link to="/bakery/myshoppage" className="nav-link">
            내 매장정보
          </Link>
        </li>
        <li className="nav-item">
          <Link to="/bakery/menulistpage" className="nav-link">
            메뉴관리
          </Link>
        </li>
      </Fragment>
    );
  }

  const equalsNumber = (arr, value) => {
    if (arr.length === 0) return false;
    for (let i = 0; i < arr.length; i++) {
      if (arr[i] === value) return true;
    }
    return false;
  };

  const handleOnChange = (event, Seq) => {
    event.preventDefault();

    console.log(alarmList);
    console.log(Seq);
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
          alert("해당 빵이 알람등록되었습니다.");
          updateAlarmList();
        } else {
          alert("알람 등록에 실패하였습니다.");
        }
      })
      .catch((err) => alert(err.response.data.msg));
  };

  return (
    <div>
      {/* 네비게이션 바 */}
      <nav className="navbar navbar-expand-lg navbar-light bg-#f0e8d9">
        <Link to="/" className="navbar-brand" id="BrandName">
          선 빵
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to="/service/bakerymap" className="nav-link">
                빵 집 찾기
              </Link>
            </li>
            <li className="nav-item">
              <Link to="/bakery/searchfoodpage" className="nav-link">
                빵 찾기
              </Link>
            </li>
            <li className="nav-item">
              <Link to="#" className="nav-link">
                빵 종류
              </Link>
            </li>
            <li className="nav-item">
              <Link to="#" className="nav-link">
                빵 집 추천
              </Link>
            </li>
          </ul>

          <ul className="navbar-nav">
            <ViewLogFunc isloggedIn={auth.loggedIn} />
          </ul>
        </div>
      </nav>
      {/* header부분 */}
      <div id="header">
        <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
          <span id="sunbbang">빵 검색</span>
        </a>
      </div>
      <div id="SearchFoodContent">
        {/* 검색 */}
        <span className="addressbox input-group mb-3">
          <input
            type="text"
            id="search"
            className="var"
            onChange={(e) => {
              setKeyword(e.target.value);
            }}
          />
          <button
            type="button"
            id="post"
            onClick={() => {
              handlePage(1);
            }}
          >
            <span>검색</span>
          </button>
        </span>
        {/* content */}
        <Table style={{ textAlign: "center" }}>
          <thead>
            <tr>
              <th style={{ textAlign: "center" }}>이미지</th>
              <th style={{ textAlign: "center" }}>제품명</th>
              <th style={{ textAlign: "center" }}>판매처</th>
              <th style={{ textAlign: "center" }}>판매 시작 시각</th>
              <th style={{ textAlign: "center" }}>가격</th>
              <th style={{ textAlign: "center" }}>알람등록</th>
            </tr>
          </thead>
          <tbody>
            {breadList.map((obj, index) => (
              <tr key={index}>
                <td>
                  <img
                    src={obj.foodPath}
                    style={{ width: "30px", height: "30px" }}
                  />
                </td>
                <td>{obj.foodName}</td>
                <td>{obj.bakeryVO.storeName}</td>
                <td>{obj.saleTime}</td>
                <td>{obj.price}</td>
                <td>
                  <button onClick={(e) => handleOnChange(e, obj.foodSeq)}>
                    등록
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
        <div style={{ width: "250px", margin: "0 auto" }}>
          <ReactPaginate
            activePage={page}
            totalItemsCount={total}
            itemsCountPerPage={perPage}
            onChange={(event) => handlePage(event)}
            innerClass="pagination"
            itemClass="page-item"
            activeClass="active"
            nextPageText="다음"
            prevPageText="이전"
            className="d-flex justify-content-center"
          />
        </div>
      </div>
    </div>
  );
}

export default SearchFood;
