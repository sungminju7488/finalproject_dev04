import { Link } from "react-router-dom";
import Navbar from "react-bootstrap/Navbar";
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

function navbar() {
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
    </body>
  );
}

export default navbar;
