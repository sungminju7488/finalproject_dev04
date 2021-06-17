import { Fragment, useEffect, useState } from "react";
import { Link, Redirect } from "react-router-dom";
import auth from "../Logic/Auth";
import "../css/WriteArticle.css";
import axios from "axios";

function WriteArticle(props) {
  const [articleCopRegNum, setArticleCopRegNum] = useState("");
  const [articleStoreName, setArticleStoreName] = useState("");
  const [title, setTitle] = useState("");
  const [score, setScore] = useState(0);
  const [content, setContent] = useState("");
  const [articlePath, setArticlePath] = useState("");
  const [articleSavePath, setArticleSavePath] = useState("");
  const [image, setImage] = useState(null);
  const [imageName, setImageName] = useState("");
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    setArticleCopRegNum(sessionStorage.getItem("ArticleCopRegNum"));
    setArticleStoreName(sessionStorage.getItem("ArticleStoreName"));
  });

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

  //이미지 변경
  const changeImageHandler = (event) => {
    let reader = new FileReader();
    if (event.target.files[0]) {
      reader.readAsDataURL(event.target.files[0]); //1.파일을 읽어 버퍼에 저장
      setImage(event.target.files[0]);
      setArticlePath(URL.createObjectURL(event.target.files[0]));
      setArticleSavePath(URL.createObjectURL(event.target.files[0]));
      setImageName(event.target.files[0].name);
    }
  };

  //글 작성
  const WriteHanler = (event) => {
    event.preventDefault();

    const formData = new FormData();
    formData.append("copRegNum", articleCopRegNum);
    formData.append("title", title);
    formData.append("score", score);
    formData.append("report", "0");
    formData.append("content", content);
    formData.append("articlePath", articlePath);
    formData.append("articleSavePath", articleSavePath);
    formData.append("image", image);
    formData.append("imageName", imageName);
    formData.append("nickName", auth.nickName);
    formData.append("memberSeq", auth.memberSeq);

    const config = {
      headers: { "Content-Type": "multipart/form-data" },
    };

    axios
      .post("/article/writeArticle", formData, config)
      .then((res) => {
        if (res.data === true) {
          alert("게시글이 등록되었습니다.");
          setSuccess(true);
        } else {
          alert("게시글 등록에 실패하였습니다.");
        }
      })
      .catch((err) => alert(err.response.data.msg));
  };

  if (success) {
    return <Redirect to="/article/bakeryarticlelistpage" />;
  } else {
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
        <div id="content">
          {/* header부분 */}
          <div id="header">
            <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
              <span id="sunbbang">글작성</span>
            </a>
          </div>
          {/* 내용작성 */}
          <form onSubmit={(e) => WriteHanler(e)}>
            {/* 제목 */}
            <h3 className="join_title">
              <label>제목</label>
            </h3>
            <span className="box">
              <input
                type="text"
                id="title"
                className="var"
                onChange={(e) => {
                  setTitle(e.target.value);
                }}
              ></input>
            </span>
            {/* 작성자 */}
            <h3 className="join_title">
              <label>작성자</label>
            </h3>
            <span className="box">
              <input
                type="text"
                id="title"
                className="var"
                value={auth.nickName}
                readOnly
              ></input>
            </span>
            {/* 평점 */}
            <h3 className="join_title">
              <label>평점</label>
            </h3>
            <span className="box">
              <input
                type="number"
                id="score"
                className="var"
                placeholder="1~5점으로 점수를 입력해주세요."
                min="1"
                max="5"
                onChange={(e) => {
                  setScore(e.target.value);
                }}
              ></input>
            </span>
            {/* 평가내용 */}
            <h3 className="join_title">
              <label>평가내용</label>
            </h3>
            <span className="">
              <textarea
                className="tvar"
                rows="10"
                onChange={(e) => {
                  setContent(e.target.value);
                }}
              ></textarea>
            </span>
            {/*articlePath 부분 */}
            <div>
              <h3 className="join_title">
                <label>매장사진 등록</label>
              </h3>
              <div className="box">
                <input
                  type="file"
                  id="image"
                  onChange={changeImageHandler}
                  className="var"
                />
              </div>
              {articlePath ? (
                <div
                  className="bakeryImage"
                  style={{
                    width: "300px",
                    marginLeft: "auto",
                    marginRight: "auto",
                  }}
                >
                  <img
                    src={articlePath}
                    style={{
                      backgroundColor: "#efefef",
                      width: "300px",
                      height: "300px",
                    }}
                  />
                </div>
              ) : (
                ""
              )}
            </div>
            {/* 글 작성 버튼 */}
            <div id="btn_area">
              <button type="submit" id="login_btn">
                <span>글 작성하기</span>
              </button>
            </div>
          </form>
        </div>
      </div>
    );
  }
}

export default WriteArticle;
