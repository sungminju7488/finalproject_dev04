import { Link } from "react-router-dom";
import Navbar from "react-bootstrap/Navbar";
import Carousel from 'react-bootstrap/Carousel';
import {
  Nav,
  Form,
  FormControl,
  Button,
  NavbarBrand,
  NavLink,
  
} from "react-bootstrap";
import NavbarToggle from "react-bootstrap/esm/NavbarToggle";
import NavbarCollapse from "react-bootstrap/esm/NavbarCollapse";
import "../css/Navbar.css";
import "../css/carousel.css";

function main() {
  return (
    <body>
      <nav className="navbar navbar-expand-lg navbar-light bg-#f0e8d9">
        <a className="navbar-brand" href="#" ID="BrandName">
          선 빵
        </a>
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
              <Link to="#" className="nav-link">
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
            <li className="nav-item">
              <Link to="/member/join1" className="nav-link">
                회원가입
              </Link>
            </li>
            <li className="nav-item">
              <Link to="/member/login1" className="nav-link">
                로그인
              </Link>
            </li>
          </ul>
        </div>
      </nav>
      <div id="carouselExampleIndicators" className="carousel slide" data-ride="carousel">
        <ol className="carousel-indicators">
          <li data-target="#carouselExampleIndicators" data-slide-to="0" className="active"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
          <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div className="carousel-inner">
          <div className="carousel-item item1 active">
            <div className="helper"></div><div className="intro">
              <h2>근처 빵 집을 찾아보세요.</h2>
              <h3>따끈따끈한 빵들이 기다리고 있습니다.</h3>
              <a href="#" id="carouselLink">빵 집 찾기</a>
            </div>
          </div>
          <div class="carousel-item item2">
            <div className="helper"></div><div className="intro">
              <h2>어떤 빵을 드시고 싶으신가요??</h2>
              <h3>고객님이 원하시는 빵을 한눈에!</h3>
              <a href="#" id="carouselLink">빵 찾기</a>
            </div>
          </div>
          <div className="carousel-item item3">
            <div className="helper"></div><div className="intro">
              <h2>혹시 선택하기 힘드신가요??</h2>
              <h3>고객님께 빵 집을 추천해드립니다.</h3>
              <a href="#" id="carouselLink">빵 집 추천</a>
            </div>
          </div>
        </div>
        <a className="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
          <span className="carousel-control-prev-icon" aria-hidden="true"></span>
          <span className="sr-only">Previous</span>
        </a>
        <a className="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
          <span className="carousel-control-next-icon" aria-hidden="true"></span>
          <span className="sr-only">Next</span>
        </a>
      </div>
  
  
  
    </body>
  );
}

export default main;
