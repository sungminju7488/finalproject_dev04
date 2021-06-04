import React, { Fragment, useEffect, useState } from "react";
import "../css/Sidebar.css";

//슬라이드바
const Sidebar = ({ width, height, children }) => {
  //움직일 X값 세팅
  const [xPosition, setX] = useState(-width);

  //슬라이드바 X위치 변경
  const toggleMenu = () => {
    if (xPosition < 0) {
      setX(0);
    } else {
      setX(-width);
    }
  };

  //컴포넌트 실행시 작동
  useEffect(() => {
    setX(-width);
  }, []);

  return (
    <div
      className="side-bar"
      style={{
        width: width,
        minHeight: height,
        transform: `translatex(${xPosition}px)`, //애니메이션추가
      }}
    >
      <button
        onClick={() => toggleMenu()}
        className="toggle-menu"
        style={{
          transform: `translate(${width}px, 20vh)`,
        }}
      ></button>
      <div className="content">{children}</div>
    </div>
  );
};

export default Sidebar;
