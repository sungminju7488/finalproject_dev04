import axios from "axios";
import { Fragment, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import auth from "../Logic/Auth";
import ReactPaginate from "react-js-pagination";
import "../css/BakeryArticleList.css";

function BakeryArticleList(props) {
  const [articleStoreName, setArticleStoreName] = useState("");
  var [page, setPage] = useState(0);
  var [count, setCount] = useState(0);
  var [perPage, setPerPage] = useState(0);
  const [articleData, setArticleData] = useState([]);

  useEffect(() => {
    setArticleStoreName(sessionStorage.getItem("ArticleStoreName"));
    handlePage(1);
  }, []);

  function handlePage(No) {
    const formData = new FormData();
    formData.append("copRegNum", sessionStorage.getItem("ArticleCopRegNum"));
    formData.append("pageNo", No - 1);

    axios
      .post("/article/articleList", formData)
      .then((res) => {
        setArticleData(res.data.content);
        settingPage(
          res.data.pageable.page,
          res.data.total,
          res.data.pageable.size
        );
      })
      .catch((err) => alert(err.response.data.msg));
  }

  function settingPage(_page, _total, _perPage) {
    setPage(_page + 1);
    setCount(_total);
    setPerPage(_perPage);
  }

  //로그인 상태에 따른 태그 구분
  function ViewLogFunc(props) {
    const isloggedIn = props.isloggedIn;
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

  function readArticleHandler(Seq) {
    sessionStorage.setItem("articleSeq", Seq);
    window.location.href = "/article/readarticlepage";
  }

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
      <div id="content" style={{ width: "700px" }}>
        {/* header부분 */}
        <div id="header">
          <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
            <span id="sunbbang">{articleStoreName}</span>
          </a>
        </div>
        {/* 게시판 구현 */}
        <table className="table table-hover" style={{ textAlign: "center" }}>
          <thead>
            <tr>
              <th style={{ textAlign: "center" }}>제목</th>
              <th style={{ textAlign: "center" }}>작성자</th>
              <th style={{ textAlign: "center" }}>작성일자</th>
              <th style={{ textAlign: "center" }}>평점</th>
            </tr>
          </thead>
          <tbody>
            {articleData.map((obj, index) => (
              <tr key={index}>
                <td>
                  <button
                    className="ArticleListTitle"
                    onClick={() => readArticleHandler(obj.articleSeq)}
                  >
                    {obj.title}
                  </button>
                </td>
                <td>{obj.writerNickname}</td>
                <td>{obj.regDate}</td>
                <td>{obj.score}</td>
              </tr>
            ))}
          </tbody>
        </table>
        <div style={{ margin: "0 auto", width: "230px" }}>
          <ReactPaginate
            activePage={page}
            totalItemsCount={count}
            itemsCountPerPage={perPage}
            onChange={(event) => handlePage(event)}
            innerClass="pagination"
            itemClass="page-item"
            activeClass="active"
            nextPageText="다음"
            prevPageText="이전"
            // className="d-flex justify-content-center"
          />
        </div>
        {/* 리뷰작성 */}
        <div className="btn_area">
          <Link to="/article/writearticlepage">
            <button id="btnJoin">
              <span>리뷰작성</span>
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
}

export default BakeryArticleList;
