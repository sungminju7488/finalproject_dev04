import { Link } from "react-router-dom";
import auth from "../Logic/Auth";
import "../css/Navbar.css";
import "../css/carousel.css";
import { Fragment } from "react";

function Main() {
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
  function GradeFunc(props) {
    const userGrade = props.grade;
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
      </Fragment>
    );
  }

  return (
    <div>
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
              <Link to="#" className="nav-link">
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
      <div
        id="carouselExampleIndicators"
        className="carousel slide"
        data-ride="carousel"
      >
        <ol className="carousel-indicators">
          <li
            data-target="#carouselExampleIndicators"
            data-slide-to="0"
            className="active"
          ></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div className="carousel-inner">
          <div className="carousel-item item1 active">
            <div className="helper"></div>
            <div className="intro">
              <h2>근처 빵 집을 찾아보세요.</h2>
              <h3>따끈따끈한 빵들이 기다리고 있습니다.</h3>
              <a href="#" id="carouselLink">
                빵 집 찾기
              </a>
            </div>
          </div>
          <div className="carousel-item item2">
            <div className="helper"></div>
            <div className="intro">
              <h2>어떤 빵을 드시고 싶으신가요??</h2>
              <h3>고객님이 원하시는 빵을 한눈에!</h3>
              <a href="#" id="carouselLink">
                빵 찾기
              </a>
            </div>
          </div>
          <div className="carousel-item item3">
            <div className="helper"></div>
            <div className="intro">
              <h2>혹시 선택하기 힘드신가요??</h2>
              <h3>고객님께 빵 집을 추천해드립니다.</h3>
              <a href="#" id="carouselLink">
                빵 집 추천
              </a>
            </div>
          </div>
        </div>
        <a
          className="carousel-control-prev"
          href="#carouselExampleIndicators"
          role="button"
          data-slide="prev"
        >
          <span
            className="carousel-control-prev-icon"
            aria-hidden="true"
          ></span>
          <span className="sr-only">Previous</span>
        </a>
        <a
          className="carousel-control-next"
          href="#carouselExampleIndicators"
          role="button"
          data-slide="next"
        >
          <span
            className="carousel-control-next-icon"
            aria-hidden="true"
          ></span>
          <span className="sr-only">Next</span>
        </a>
      </div>
    </div>
  );
}

export default Main;
