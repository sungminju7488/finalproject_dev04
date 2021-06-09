import axios from "axios";
import React, { useEffect, useState } from "react";
import "../css/Map.css";
import Sidebar from "../subcomponent/Sidebar";
import BreadTimeModal from "./BreadTimeModal";

const { kakao } = window;

//메서드 컴포넌트의 경우 맨 앞글자가 대문자여야함
const BakeryMap = () => {
  var [modalOpen, setModalOpen] = useState(false);
  var [bakeryData, setBakeryData] = useState(null);
  var [breadData, setBreadData] = useState(null);
  var [viewMap, setViewMap] = useState(null);
  var [viewOverlay, setViewOverlay] = useState(null);
  //브라우저 크기에 맞게 지도 크기를 변경할 수 있도록 변수설정
  const [windowSize, setWindowSize] = useState({
    width: window.innerWidth,
    height: window.innerHeight,
  });

  //브라우저 크기가 변경되면 windowSize변수 값도 변경하는 함수
  const handleResize = () => {
    setWindowSize({
      width: window.innerWidth,
      height: window.innerHeight,
    });
  };

  //빵나오는 시간 Modal열기
  const openBreadTimeModal = (bakeryInfoData, breadInfoData) => {
    setBakeryData(bakeryInfoData);
    setBreadData(breadInfoData);
    //console.log("Data storeName : " + data.storeName);
    setModalOpen(true);
  };

  //빵나오는 시간 Modale닫기
  const closeBreadTimeModal = () => {
    setModalOpen(false);
    setBakeryData(null);
  };

  const handleFirstPage = async () => {
    const pageNo = 0;

    axios
      .post("/bakery/searchBakery", { pageNo })
      .then((res) => {
        init(res.data.content);
      })
      .catch((err) => alert(err.response.data.msg));
  };
  const handleSecondPage = async () => {
    const pageNo = 1;

    axios
      .post("/bakery/searchBakery", { pageNo })
      .then((res) => {
        init(res.data.content);
      })
      .catch((err) => alert(err.response.data.msg));
  };

  //class 컴포넌트 함수중 componentDidMount
  useEffect(() => {
    //브라우저 크기 리사이징 이벤트 등록
    window.addEventListener("resize", handleResize);

    const pageNo = 0;
    //통신을 통해 빵집 데이터를 가져온다.
    //GET, DELETE | POST, PUT
    axios
      .post("/bakery/searchBakery", {})
      .then(
        (res) => {
          init(res.data.content);
        }
        // alert(data[1].storeName);
      )
      .catch((err) => alert(err.response.data.msg));

    //마운트 종료시 이벤트 삭제
    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  function init(listData) {
    //GPS지원 여부 검사
    if (navigator.geolocation) {
      //GPS로 현재 위치의 경고와 위도를 받아옵니다.
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
          saveMap(map);

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
          const memberSeq = listData[i].memberSeq;
          console.log("memberSeq : " + memberSeq);
          //통신
          axios.post("/bakery/menuViewList", { memberSeq }).then((res) => {
            openBreadTimeModal(listData[i], res.data);
          });
        });
      })(bakeryMarker, overlay);
    }
  }

  const saveOverlay = (data) => {
    setViewOverlay(data);
  };

  const saveMap = (data) => {
    setViewMap(data);
  };

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

  //jQuery 처리

  return (
    <div>
      <Sidebar width={300} height={"100vh"}>
        <h1>테스트용</h1>
        <h1>슬라이드바</h1>
        <button type="button" onClick={() => handleFirstPage()}>
          1
        </button>
        <button type="button" onClick={() => handleSecondPage()}>
          2
        </button>
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
          height: windowSize.height,
        }}
      ></div>
    </div>
  );
};

export default BakeryMap;
