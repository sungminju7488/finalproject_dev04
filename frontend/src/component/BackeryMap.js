import React, { Fragment, useEffect, useState } from "react";
import "../css/Map.css";
import Sidebar from "../subcomponent/Sidebar";

const { kakao } = window;

//메서드 컴포넌트의 경우 맨 앞글자가 대문자여야함
const BackeryMap = () => {
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

  //class 컴포넌트 함수중 componentDidMount
  useEffect(() => {
    //브라우저 크기 리사이징 이벤트 등록
    window.addEventListener("resize", handleResize);

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

          //마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다.
          const userLocPosition = new kakao.maps.LatLng(
            position.coords.latitude,
            position.coords.longitude
          );

          //사용자의 마커를 표시한다.
          displayUserMarker(userLocPosition, map);

          //베이커리 마커를 표시한다.
          displayBackeryMarker(map);
        },
        //에러가 났을경우 에러출력
        function (error) {
          console.log("error: " + error);
        }
      );
    } else {
      alert("GPS를 지원하지 않습니다.");
    }

    //마운트 종료시 이벤트 삭제
    return () => {
      window.removeEventListener("resize", handleResize);
    };
  }, []);

  //사용자 마커 표시 메서드
  function displayUserMarker(userLocPosition, map) {
    const userMarker = new kakao.maps.Marker({
      map: map,
      position: userLocPosition,
    });
  }

  //베이커리 마커 표시 메서드
  function displayBackeryMarker(map) {
    //베이커리 위치 정보
    const backeryPositions = backeryDummyData();

    //베이커리 마커 이미지
    var imageSrc =
      "https://cdn.discordapp.com/attachments/711782943781290077/849946664038825984/KakaoTalk_20210602_120156541.png";

    //마커 이미지의 크기
    var imageSize = new kakao.maps.Size(26, 50);

    //마커 이미지 생성
    var backeryMarkerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    //backeryPositions를 모두 표기합니다.
    for (let i = 0; i < backeryPositions.length; i++) {
      //베이커리 마커 생성
      var backeryMarker = new kakao.maps.Marker({
        map: map,
        position: backeryPositions[i].latlng,
        image: backeryMarkerImage,
        clickable: true,
      });

      //커스텀 오버레이 생성
      var overlay = new kakao.maps.CustomOverlay({
        content: backeryPositions[i].content,
        map: null,
        position: backeryPositions[i].latlng,
      });

      //메서드 등록
      (function (BackeryMarker, Overlay) {
        //베이커리 마커에 mouseover이벤트를 등록
        kakao.maps.event.addListener(BackeryMarker, "mouseover", function () {
          Overlay.setMap(map);
        });

        //베이커리 마커에 mouseout이벤트를 등록
        kakao.maps.event.addListener(BackeryMarker, "mouseout", function () {
          Overlay.setMap(null);
        });
      })(backeryMarker, overlay);
    }
  }

  //임시 빵집 더미데이터
  function backeryDummyData() {
    const Dummy = [
      {
        title: "파리바게뜨 장호원점",
        latlng: new kakao.maps.LatLng(37.117272, 127.631443),
        content:
          `<div class="wrap">` +
          `<div class="info">` +
          `<div class="title">` +
          `파리바게뜨 장호원점` +
          `</div>` +
          `<div class="body">` +
          `<div class="img">` +
          `<img src="https://lh5.googleusercontent.com/p/AF1QipPrzZe9I4DwweEstQxUZmy7AR10ptb5Ck0O47lB=s516-k-no" width="73" height="70">` +
          `</div>` +
          `<div class="desc">` +
          `<div class="ellipsis">경기도 이천시 장호원읍 장호원리 45</div>` +
          `</div>` +
          `</div>` +
          `</div>` +
          `</div>`,
      },
      {
        title: "뚜레쥬르 장호원점",
        latlng: new kakao.maps.LatLng(37.117712, 127.631947),
        content:
          `<div class="wrap">` +
          `<div class="info">` +
          `<div class="title">` +
          `뚜레쥬르 장호원점` +
          `</div>` +
          `<div class="body">` +
          `<div class="img">` +
          `<img src="https://mblogthumb-phinf.pstatic.net/MjAxODA2MDdfMTIw/MDAxNTI4MzU0NzU0NTU1.jw2u3jbqwpqHtPfZr8r7qwdQhcdZ8oOe1r7libHQTqwg.6Lnul2J5nvJ1m3Yf-EjrKldBeki7k4ZUEP7Kjo65lVog.JPEG.hoo3652404/DSC03299.JPG?type=w800" width="73" height="70">` +
          `</div>` +
          `<div class="desc">` +
          `<div class="ellipsis">경기도 이천시 장호원읍 장호원리 40-1번지</div>` +
          `</div>` +
          `</div>` +
          `</div>` +
          `</div>`,
      },
      {
        title: "빵굼터 사당점",
        latlng: new kakao.maps.LatLng(37.476394, 126.977545),
        content:
          `<div class="wrap">` +
          `<div class="info">` +
          `<div class="title">` +
          `빵굼터 사당점` +
          `</div>` +
          `<div class="body">` +
          `<div class="img">` +
          `<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4SeQDd-JWxB8BKtJQS9kuwxSPnJN6DwBGyg&usqp=CAU" width="73" height="70">` +
          `</div>` +
          `<div class="desc">` +
          `<div class="ellipsis">서울특별시 관악구 남현동 1057-23</div>` +
          `</div>` +
          `</div>` +
          `</div>` +
          `</div>`,
      },
      {
        title: "캉파뇽제빵소",
        latlng: new kakao.maps.LatLng(37.476877, 126.978549),
        content:
          `<div class="wrap">` +
          `<div class="info">` +
          `<div class="title">` +
          `캉파뇽제빵소` +
          `</div>` +
          `<div class="body">` +
          `<div class="img">` +
          `<img src="https://mblogthumb-phinf.pstatic.net/MjAyMDAzMjVfMTAy/MDAxNTg1MDY1NTk1NjM1.JEC6rocBYAIfj8mfnH8JwYl3569gLpiNPv55-XygmT0g.Q5zNXC2Ejjny-pn0REIWjNnuQmhhVVhZVhRoh5UH5LQg.JPEG.dnvkd/SE-6a251d9c-425e-4d53-9cbd-f2d5380676c1.jpg?type=w800" width="73" height="70">` +
          `</div>` +
          `<div class="desc">` +
          `<div class="ellipsis">서울특별시 동작구 사당1동 남부순환로 2067</div>` +
          `</div>` +
          `</div>` +
          `</div>` +
          `</div>`,
      },
      {
        title: "해이 케이크샵",
        latlng: new kakao.maps.LatLng(37.480092, 126.983878),
        content:
          `<div class="wrap">` +
          `<div class="info">` +
          `<div class="title">` +
          `해이 케이크샵` +
          `</div>` +
          `<div class="body">` +
          `<div class="img">` +
          `<img src="https://blog.kakaocdn.net/dn/r82GX/btqNhaB0rO6/TkYyFvuK1bq1z2y8uQkIyK/img.jpg" width="73" height="70">` +
          `</div>` +
          `<div class="desc">` +
          `<div class="ellipsis">서울특별시 서초구 방배2동 454-14</div>` +
          `</div>` +
          `</div>` +
          `</div>` +
          `</div>`,
      },
      {
        title: "파리바게뜨 은계어반리더스점",
        latlng: new kakao.maps.LatLng(37.451222, 126.79618),
        content:
          `<div class="wrap">` +
          `<div class="info">` +
          `<div class="title">` +
          `파리바게뜨 은계어반리더스점` +
          `</div>` +
          `<div class="body">` +
          `<div class="img">` +
          `<img src="https://mblogthumb-phinf.pstatic.net/MjAyMDExMjBfODMg/MDAxNjA1Nzk5MDk3OTE2.Z4khS8-m48G0gmHKH97OxynvXEI4bhqApBBIzAOdsB4g.jLLEtGgEfiEAm_8KIG_CTA4ReiPBgkf7P-pBrpOJUIcg.JPEG.gksthdgml071/SE-e1b16943-2a51-11eb-a8e6-a75bcfac3448.jpg?type=w800" width="73" height="70">` +
          `</div>` +
          `<div class="desc">` +
          `<div class="ellipsis">경기 시흥시 은계로 338번길 42</div>` +
          `</div>` +
          `</div>` +
          `</div>` +
          `</div>`,
      },
    ];

    return Dummy;
  }

  return (
    <div>
      <Sidebar width={300} height={"100vh"}>
        <h1>테스트용</h1>
        <h1>슬라이드바</h1>
      </Sidebar>
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

export default BackeryMap;
