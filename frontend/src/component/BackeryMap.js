import React, { useEffect, useState } from "react";
import "../css/Map.css";

const { kakao } = window;

//메서드 컴포넌트의 경우 맨 앞글자가 대문자여야함
const BackeryMap = () => {
  //브라우저 크기에 맞게 지도 크기를 변경할 수 있도록 변수설정
  const [windowSize, setWindowSize] = useState({
    width: window.innerWidth - 1,
    height: window.innerHeight - 1,
  });

  //브라우저 크기가 변경되면 windowSize변수 값도 변경하는 함수
  const handleResize = () => {
    setWindowSize({
      width: window.innerWidth - 1,
      height: window.innerHeight - 1,
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
          const locPosition = new kakao.maps.LatLng(
            position.coords.latitude,
            position.coords.longitude
          );
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

  return (
    <div
      id="myMap"
      style={{ width: windowSize.width, height: windowSize.height }}
    ></div>
  );
};

export default BackeryMap;
