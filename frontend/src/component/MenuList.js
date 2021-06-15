import axios from "axios";
import { useEffect, useState } from "react";
import { Button, Card, Table, Col, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import auth from "../Logic/Auth";
import "../css/MenuList.css";

function MenuList() {
  const [menuTile, setMenuTile] = useState("");
  const [menuData, setMenuData] = useState([]);

  useEffect(() => {
    const copRegNum = auth.copRegNum;
    axios
      .post("/bakery/menuList", { copRegNum })
      .then((res) => {
        console.log(res.data[0].foodName);
        setMenuData(res.data);
        //setMenuTile(SettingMenu(res.data));
      })
      .catch((err) => alert(err.response.data.msg));
  }, []);

  const addMenuHandler = () => {
    window.location.href = "http://localhost:3000/bakery/addmanupage";
  };

  // const SettingMenu = (data) => {
  //   console.log("SettindMenu 진입");
  //   if (data === null) return "";
  //   console.log("data null 아님");
  //   console.log("data length : " + data[0].foodName);

  //   const content = ``;
  //   for (let i = 0; i < data.length; i++) {
  //     content += '<div className="card" style="width: 18rem;">';
  //     console.log("for문 반복 : " + data[i].kind);
  //     content += `<img src="${data[i].foodPath}" className="card-img-top" alt="빵 이미지">`;
  //     content += `<div className="card-body">`;
  //     content += `<h5 className="card-title">${data[i].foodName}</h5>`;
  //     content += `<p class="card-text">종류: ${data[i].kind}</p>`;
  //     content += `<a href="" className="btn btn-primary">수정</a><a href="" className="btn btn-danger">삭제</a>`;
  //     content += `</div>`;
  //     content += `</div>`;
  //     console.log("for문 종료");
  //   }
  //   console.log("for문 나옴");
  //   return content;
  // };

  return (
    <div id="content">
      {/* header부분 */}
      <div id="header">
        <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
          <span id="sunbbang">선 빵</span>
        </a>
      </div>
      {/* 메뉴 추가 버튼 */}
      <div className="mb-3">
        <button type="submit" id="login_btn" onClick={addMenuHandler}>
          <span>메뉴추가</span>
        </button>
      </div>
      <Table className="menuBox">
        <Col>
          <Row>
            {menuData.map((obj, index) => (
              <Card key={index} className="breadCard">
                <Card.Img variant="top" src={obj.foodPath} />
                <Card.Body>
                  <Card.Title>{obj.foodName}</Card.Title>
                  <Card.Text>종류: {obj.kind}</Card.Text>
                  <Button variant="primary">수정</Button>
                  <Button variant="danger">삭제</Button>
                </Card.Body>
              </Card>
            ))}
          </Row>
        </Col>
      </Table>
    </div>
  );
}

export default MenuList;
