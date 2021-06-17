import axios from "axios";
import { useEffect, useState } from "react";
import { Button, Card, Table, Col, Row } from "react-bootstrap";
import { Link } from "react-router-dom";
import auth from "../Logic/Auth";
import "../css/MenuList.css";

function MenuList({ memberSeq }) {
  const [menuTile, setMenuTile] = useState("");
  const [menuData, setMenuData] = useState([]);

  useEffect(() => {
    const copRegNum = auth.copRegNum;
    axios
      .post("/bakery/menuList", { copRegNum })
      .then((res) => {
        if (res.data.length === 0) return;
        setMenuData(res.data);
      })
      .catch((err) => alert(err.response.data.msg));
  }, []);

  const addMenuHandler = () => {
    window.location.href = "http://localhost:3000/bakery/addmenupage";
  };

  const deleteMenuHandler = (Seq) => {
    let foodSeq = Seq;
    axios
      .post("/bakery/deleteMenu", { foodSeq })
      .then((res) => {
        if (res.data === true) {
          alert("해당 메뉴가 삭제되었습니다.");
          window.location.reload();
        } else {
          alert("해당 메뉴 삭제에 실패했습니다.");
        }
      })
      .catch((err) => alert(err.response.data.msg));
  };

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
      <div className="menuBox">
        <Col>
          <Row>
            {menuData !== null && menuData !== undefined
              ? menuData.map((obj, index) => (
                  <Card key={index} className="breadCard">
                    <Card.Img
                      variant="top"
                      src={obj.foodPath + "?" + new Date().getTime()}
                    />
                    <Card.Body>
                      <Card.Title>{obj.foodName}</Card.Title>
                      <Card.Text>
                        종류: {obj.kind} <br />
                        시간: {obj.saleTime}
                        <br />
                        가격: {obj.price}
                      </Card.Text>
                      <Link
                        to={{
                          pathname: `/bakery/modifymenupage`,
                          state: {
                            foodSeq: obj.foodSeq,
                            foodName: obj.foodName,
                            kind: obj.kind,
                            foodPath: obj.foodPath,
                            foodSavePath: obj.foodSavePath,
                            price: obj.price,
                            saleTime: obj.saleTime,
                          },
                        }}
                      >
                        <Button variant="primary">수정</Button>
                      </Link>
                      &emsp;
                      <Button
                        variant="danger"
                        onClick={(event) => deleteMenuHandler(obj.foodSeq)}
                      >
                        삭제
                      </Button>
                    </Card.Body>
                  </Card>
                ))
              : ""}
          </Row>
        </Col>
      </div>
    </div>
  );
}

// MenuList.propTypes = {
//   foodSeq: propTypes.number.isRequired,
//   foodName: propTypes.string.isRequired,
//   kind: propTypes.string.isRequired,
//   foodPath: propTypes.string.isRequired,
//   price: propTypes.number.isRequired,
//   saleTime: propTypes.string.isRequired,
// };

export default MenuList;
