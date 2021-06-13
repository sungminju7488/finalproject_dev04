import axios from "axios";
import { useState } from "react";
import auth from "../Logic/Auth";

function AddMenu() {
  const [foodName, setFoodName] = useState("");
  const [kind, setKind] = useState("");
  const [foodPath, setFoodPath] = useState("");
  const [price, setPrice] = useState("");
  const [saletime, setSaleTime] = useState("");
  const [image, setImage] = useState("");
  const [imageName, setImageName] = useState("");

  //이미지 변경
  const changeImageHandler = (event) => {
    let reader = new FileReader();
    if (event.target.files[0]) {
      reader.readAsDataURL(event.target.files[0]); //1.파일을 읽어 버퍼에 저장
      setImage(event.target.files[0]);
      setFoodPath(URL.createObjectURL(event.target.files[0]));
      setImageName(event.target.files[0].name);

      console.log(event.target.files[0]);
    }
  };

  const addHandler = (event) => {
    event.preventDefault();

    const formData = new FormData();
    formData.append("copRegNum", auth.copRegNum);
    formData.append("foodName", foodName);
    formData.append("kind", kind);
    formData.append("foodPath", foodPath);
    formData.append("price", price);
    formData.append("saleTime", saletime);
    formData.append("image", image);
    formData.append("imageName", imageName);

    const config = {
      headers: { "Content-Type": "multipart/form-data" },
    };

    // console.log("image : " + image);
    // console.log("time : " + saletime);
    axios
      .post("/bakery/addMenu", formData, config)
      .then((res) => {
        if (res.data === true) {
          alert(foodName + " 메뉴가 정상 등록되었습니다.");
          window.location.replace = "http://localhost:3000/bakery/menulistpage";
        } else {
          alert("메뉴 등록에 실패 하였습니다.");
        }
      })
      .catch((err) => alert(err.response.data.msg));
  };

  return (
    <div id="content">
      <div id="header">
        <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
          <span id="sunbbang">메뉴추가</span>
        </a>
      </div>
      <form onSubmit={addHandler}>
        {/* 제품 명 */}
        <h3 className="join_title">
          <label>제품 명</label>
        </h3>
        <span className="box">
          <input
            type="text"
            id="foodName"
            className="var"
            maxLength="50"
            onChange={(e) => {
              setFoodName(e.target.value);
            }}
          ></input>
        </span>
        {/* 제품 종류 */}
        <h3 className="join_title">
          <label>제품 종류</label>
        </h3>
        <span className="box">
          <select
            id="kind"
            className="sel"
            onChange={(e) => {
              setKind(e.target.value);
            }}
          >
            <option>종류선택</option>
            <option value="빵">빵</option>
            <option value="케익">케익</option>
            <option value="제과">제과</option>
            <option value="떡">떡</option>
            <option value="음료">음료</option>
            <option value="기타">기타</option>
          </select>
        </span>
        {/* 판매시각 */}
        <h3 className="join_title">
          <label>판매 시작 시각</label>
        </h3>
        <span className="box">
          <input
            type="time"
            id="saleTime"
            className="var"
            onChange={(e) => {
              setSaleTime(e.target.value);
            }}
          ></input>
        </span>
        {/* 제품 가격 */}
        <h3 className="join_title">
          <label>제품 가격</label>
        </h3>
        <span className="box">
          <input
            type="number"
            id="price"
            className="var"
            onChange={(e) => {
              setPrice(e.target.value);
            }}
          ></input>
        </span>
        {/*bakeryPath 부분 */}
        <div>
          <h3 className="join_title">
            <label>제품사진 등록</label>
          </h3>
          <div className="box">
            <input
              type="file"
              id="image"
              onChange={changeImageHandler}
              className="var"
            />
          </div>
          {foodPath ? (
            <div
              className="bakeryImage"
              style={{
                width: "300px",
                marginLeft: "auto",
                marginRight: "auto",
              }}
            >
              <img
                src={foodPath}
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
        <div id="btn_area">
          <button type="submit" id="login_btn">
            <span>메뉴등록</span>
          </button>
        </div>
      </form>
    </div>
  );
}

export default AddMenu;
