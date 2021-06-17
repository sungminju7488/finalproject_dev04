import axios from "axios";
import React, { Fragment, useEffect, useState } from "react";
import "../css/Map.css";
import Sidebar from "../subcomponent/Sidebar";
import BreadTimeModal from "./BreadTimeModal";
import ReactPaginate from "react-js-pagination";
import auth from "../Logic/Auth";
import { Link } from "react-router-dom";

const { kakao } = window;

//메서드 컴포넌트의 경우 맨 앞글자가 대문자여야함
const BakeryMap = () => {
  var [minusHeight, setMinusHeight] = useState(37);
  var [page, setPage] = useState(0);
  var [count, setCount] = useState(0);
  var [perPage, setPerPage] = useState(0);
  var [modalOpen, setModalOpen] = useState(false);
  var [bakeryData, setBakeryData] = useState([]);
  const [bakeryListData, setBakeryListData] = useState([]);
  var [breadData, setBreadData] = useState(null);
  var [viewMap, setViewMap] = useState(null);
  // 검색용 변수
  var [keyword, setKeyword] = useState("");
  //브라우저 크기에 맞게 지도 크기를 변경할 수 있도록 변수설정
  const [windowSize, setWindowSize] = useState({
    width: window.innerWidth,
    height: window.innerHeight - minusHeight,
  });

  //브라우저 크기가 변경되면 windowSize변수 값도 변경하는 함수
  const handleResize = () => {
    setWindowSize({
      width: window.innerWidth,
      height: window.innerHeight - minusHeight,
    });
  };

  //빵나오는 시간 Modal열기
  const openBreadTimeModal = (bakeryInfoData, breadInfoData) => {
    setBakeryData(bakeryInfoData);
    setBreadData(breadInfoData);
    console.log("Data storeName : " + bakeryInfoData);
    setModalOpen(true);
  };

  //빵나오는 시간 Modale닫기
  const closeBreadTimeModal = () => {
    setModalOpen(false);
    setBakeryData(null);
  };

  function handlePage(num) {
    //보낼땐 0부터 시작하므로 -1;
    const pageNo = num - 1;

    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function (pos) {
        const myLatitude = pos.coords.latitude.toFixed(5);
        const myLongitude = pos.coords.longitude.toFixed(5);

        axios
          .post("/bakery/searchBakery", {
            keyword,
            pageNo,
            myLatitude,
            myLongitude,
          })
          .then((res) => {
            console.log(res.data);
            setPageData(
              res.data.pageable.page,
              res.data.total,
              res.data.pageable.size
            );
            setBakeryListData(res.data.content);
            setMapAndBakery(res.data.content);
          })
          .catch((err) => alert(err.response.data.msg));
      });
    }
  }

  //class 컴포넌트 함수중 componentDidMount
  useEffect(() => {
    //브라우저 크기 리사이징 이벤트 등록
    window.addEventListener("resize", handleResize);

    //통신을 통해 빵집 데이터를 가져온다.
    //GET, DELETE | POST, PUT
    handlePage(1);

    //마운트 종료시 이벤트 삭제
    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  function setPageData(_page, _count, _perPage) {
    //받을땐 0부터 시작이므로 최소1이되도록 변경
    setPage(_page + 1);
    setCount(_count);
    setPerPage(_perPage);
  }

  function setMapAndBakery(listData) {
    //GPS지원 여부 검사
    if (navigator.geolocation) {
      //GPS로 현재 위치의 경도와 위도를 받아옵니다.
      navigator.geolocation.getCurrentPosition(
        //정상처리 되었다면 콜백함수를 실행합니다.(매개변수: 위치값)
        function (position) {
          //id가 myMap인 태그를 가져옵니다.
          const container = document.getElementById("myMap");
          //중심 위치를 GPS로 가져온 값으로 세팅합니다.
          const options = {
            center: new kakao.maps.LatLng(
              position.coords.latitude,
              position.coords.longitude
            ),
            level: 3,
          };
          //지도를 그립니다.
          const map = new kakao.maps.Map(container, options);
          setViewMap(map);

          //마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다.
          const userLocPosition = new kakao.maps.LatLng(
            position.coords.latitude,
            position.coords.longitude
          );

          //사용자의 마커를 표시한다.
          displayUserMarker(userLocPosition, map);

          //베이커리 마커를 표시한다.
          displayBakeryMarker(map, listData);
        },
        //에러가 났을경우 에러출력
        function (error) {
          console.log("error: " + error);
        }
      );
    } else {
      alert("GPS를 지원하지 않습니다.");
    }
  }

  //사용자 마커 표시 메서드
  function displayUserMarker(userLocPosition, map) {
    const userMarker = new kakao.maps.Marker({
      map: map,
      position: userLocPosition,
    });
  }

  //베이커리 마커 표시 메서드
  function displayBakeryMarker(map, listData) {
    //베이커리 위치 정보
    const bakeryPositions = bakeryDummyData(listData);

    //베이커리 마커 이미지
    var imageSrc =
      "https://cdn.discordapp.com/attachments/711782943781290077/849946664038825984/KakaoTalk_20210602_120156541.png";

    //마커 이미지의 크기
    var imageSize = new kakao.maps.Size(26, 50);

    //마커 이미지 생성
    var bakeryMarkerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    //bakeryPositions를 모두 표기합니다.
    for (let i = 0; i < bakeryPositions.length; i++) {
      //베이커리 마커 생성
      var bakeryMarker = new kakao.maps.Marker({
        map: map,
        position: bakeryPositions[i].latlng,
        image: bakeryMarkerImage,
        clickable: true,
      });

      //커스텀 오버레이 생성
      var overlay = new kakao.maps.CustomOverlay({
        content: bakeryPositions[i].content,
        map: null,
        position: bakeryPositions[i].latlng,
        clickable: true,
      });

      //메서드 등록
      (function (BakeryMarker, Overlay) {
        //베이커리 마커에 mouseover이벤트를 등록(Overlay On)
        kakao.maps.event.addListener(BakeryMarker, "mouseover", function () {
          Overlay.setMap(map);
        });

        //베이커리 마커에 mouseout이벤트를 등록(Overlay Off)
        kakao.maps.event.addListener(BakeryMarker, "mouseout", function () {
          Overlay.setMap(null);
        });

        //베이커리 마커에 click이벤트를 등록(팝업 생성)
        kakao.maps.event.addListener(BakeryMarker, "click", function () {
          //베이커리 SEQ
          const copRegNum = listData[i].copRegNum;
          //통신
          axios.post("/bakery/menuViewList", { copRegNum }).then((res) => {
            console.log(listData[i]);
            openBreadTimeModal(listData[i], res.data);
          });
        });
      })(bakeryMarker, overlay);
    }
  }

  //임시 빵집 더미데이터
  function bakeryDummyData(listData) {
    var testData = [];

    console.log("length : " + listData.length);

    for (let i = 0; i < listData.length; i++) {
      const address =
        listData[i].storeAddress1 + " " + listData[i].storeAddress2;

      testData.push({
        title: listData[i].storeName,
        latlng: new kakao.maps.LatLng(
          listData[i].latitude,
          listData[i].longitude
        ),
        content:
          `<div class="wrap">` +
          `<div class="info">` +
          `<div class="title">` +
          `${listData[i].storeName}` +
          `</div>` +
          `<div class="body">` +
          `<div class="img">` +
          `<img src="${listData[i].bakeryPath}" width="73" height="70">` +
          `</div>` +
          `<div class="desc">` +
          `<div class="ellipsis">${address}</div>` +
          `<div class="ellipsis">전화번호 : ${listData[i].storeContact}</div>` +
          `</div>` +
          `</div>` +
          `</div>` +
          `</div>`,
      });
    }
    return testData;
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

  //사이드에 빵집을 누르면 해당 정보로 이동
  function sideBakeryBtnHandler(BakeryData) {
    const copRegNum = BakeryData.copRegNum;

    //TODO: 중앙잡기
    viewMap.setCenter(
      new kakao.maps.LatLng(BakeryData.latitude, BakeryData.longitude)
    );

    //통신
    axios.post("/bakery/menuViewList", { copRegNum }).then((res) => {
      console.log(BakeryData);
      openBreadTimeModal(BakeryData, res.data);
    });
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
      {/* 슬라이드바 */}
      <Sidebar width={300} height={"70vh"}>
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
            style={{ margin: "1px", fontSize: "15px" }}
          >
            <span>검색</span>
          </button>
        </span>
        {/* 베이커리 리스트 */}
        {bakeryListData.map((obj, index) => (
          <div key={index}>
            <button
              className="sliderBakeryBtn"
              onClick={() => {
                sideBakeryBtnHandler(obj);
              }}
            >
              <div className="card" style={{ textAlign: "left" }}>
                <h5 className="card-header">{obj.storeName}</h5>
                <p className="card-text">
                  주소:&nbsp;
                  {obj.storeAddress2 === null || obj.storeAddress2 === undefined
                    ? obj.storeAddress1
                    : obj.storeAddress1 + " " + obj.storeAddress2}
                  <br />
                  전화번호:&nbsp;{obj.storeContact}
                </p>
              </div>
            </button>
          </div>
        ))}
        {/* 페이징 처리(react-js-pagination 라이브러리 사용) */}
        <div style={{ width: "300px", margin: "0px auto" }}>
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
            className="d-flex justify-content-center"
          />
        </div>
      </Sidebar>
      <BreadTimeModal
        open={modalOpen}
        close={closeBreadTimeModal}
        bakeryData={bakeryData}
        breadData={breadData}
      />

      <div
        id="myMap"
        style={{
          width: windowSize.width,
          height: windowSize.height - minusHeight,
        }}
      ></div>
    </div>
  );
};

export default BakeryMap;
