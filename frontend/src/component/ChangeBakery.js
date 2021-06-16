import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import auth from "../Logic/Auth";
import AddressModal from "./AddressModal";
import DaumPostcode from "react-daum-postcode";
import axios from "axios";

const { kakao } = window;

const ChangeBakery = ({ history }) => {
  const [copRegNum, setCopRegNum] = useState(0);
  const [manager, setManager] = useState("");
  const [storeName, setStoreName] = useState("");
  const [storeAddress1, setStoreAddress1] = useState("");
  const [storeAddress2, setStoreAddress2] = useState("");
  const [storeContact, setStoreContant] = useState("");
  const [businessHour, setBusinessHour] = useState("");
  const [holiday, setHoliday] = useState("");
  const [specialHoliday, setSpecialHoliday] = useState("");
  const [eatable, setEatable] = useState("");
  const [latitude, setLatitude] = useState("");
  const [longitude, setLongitude] = useState("");
  const [boardSet, setBoardSet] = useState("");
  const [bakeryPath, setBakeryPath] = useState("");
  const [bakerySavePath, setBakerySavePath] = useState("");
  const [image, setBakeryImage] = useState(null);
  const [imageName, setImageName] = useState("");
  const [imagePath, setImagePath] = useState(null);
  //주소 Modal
  const [ModalOpen, setModalOpen] = useState(false);

  const init = (data) => {
    setCopRegNum(data.copRegNum);
    setManager(data.manager);
    setStoreName(data.storeName);
    setStoreAddress1(data.storeAddress1);
    setStoreAddress2(data.storeAddress2);
    setStoreContant(data.storeContact);
    setBusinessHour(data.businessHour);
    setHoliday(data.holiday);
    setSpecialHoliday(data.specialHoliday);
    setEatable(data.eatable);
    setBoardSet(data.boardSet);
    setBakeryPath(data.bakeryPath);
    setLatitude(data.latitude);
    setLongitude(data.longitude);
    setBakerySavePath(data.bakerySavePath);
  };

  useEffect(() => {
    //화면에 들어올시 매장 정보를 조회한다.

    const memberSeq = auth.memberSeq;

    axios
      .post("/bakery/myShop", { memberSeq })
      .then((res) => {
        init(res.data);
      })
      .catch((err) => alert(err.response.data.msg));
  }, []);

  const changeBakeryHandler = async (event) => {
    event.preventDefault();

    let formData = new FormData();
    formData.append("copRegNum", copRegNum);
    formData.append("manager", manager);
    formData.append("storeName", storeName);
    formData.append("storeAddress1", storeAddress1);
    formData.append(
      "storeAddress2",
      storeAddress2 === undefined ? "" : storeAddress2
    );
    formData.append("storeContact", storeContact);
    formData.append("bakeryPath", bakeryPath);
    formData.append("bakerySavePath", bakerySavePath);
    formData.append("businessHour", businessHour);
    formData.append("holiday", holiday === undefined ? "" : holiday);
    formData.append(
      "specialHoliday",
      specialHoliday === undefined ? "" : specialHoliday
    );
    formData.append("eatable", eatable);
    formData.append("latitude", latitude);
    formData.append("longitude", longitude);
    formData.append("boardSet", boardSet);
    formData.append("memberSeq", auth.memberSeq);
    formData.append("image", image);
    formData.append("imageName", imageName);

    const config = {
      headers: { "Content-Type": "multipart/form-data" },
    };

    axios
      .post("/bakery/changeBakery", formData, config)
      .then((res) => {
        if (res.data !== null || res.data !== undefined) {
          alert("매장정보가 변경되었습니다.");
          history.goBack();
        } else {
          alert("사업자 회원 등록에 실패했습니다.");
        }
      })
      .catch((err) => alert(err.response.data.msg));
  };

  //주소 검색 Modal열기
  const openModal = () => {
    setModalOpen(true);
  };

  //주소 검색 Modal닫기
  const closeModal = () => {
    setModalOpen(false);
  };

  //주소 변경
  const handleAddress = (data) => {
    let fullAddress = data.address;
    let extraAddress = "";

    if (data.addressType === "R") {
      if (data.bname !== "") {
        extraAddress += data.bname;
      }
      if (data.buildingName !== "") {
        extraAddress +=
          extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
    }

    //주소세팅
    setStoreAddress1(fullAddress);

    //위도 경도 검색용 Geocoder생성
    const geocoder = new kakao.maps.services.Geocoder();

    geocoder.addressSearch(fullAddress, function (results, status) {
      //정상적으로 검색완료
      if (status === kakao.maps.services.Status.OK) {
        const result = results[0];

        setLatitude(result.y.substring(0, 9));
        setLongitude(result.x.substring(0, 9));
      }
    });

    //Modal닫기
    closeModal();
  };

  //이미지 변경
  const changeImageHandler = (event) => {
    let reader = new FileReader();
    if (event.target.files[0]) {
      reader.readAsDataURL(event.target.files[0]); //1.파일을 읽어 버퍼에 저장
      setBakeryImage(event.target.files[0]);
      setImagePath(URL.createObjectURL(event.target.files[0]));
      setImageName(event.target.files[0].name);

      console.log(event.target.files[0]);
    }
  };

  return (
    <form onSubmit={changeBakeryHandler}>
      <div id="content">
        {/* header부분 */}
        <div id="header">
          <Link to="/">
            <span id="sunbbang">매장정보변경</span>
          </Link>
        </div>
        {/* copRegNum 부분*/}
        <h3 className="join_title">
          <label>사업자번호</label>
        </h3>
        <span className="box var_id">
          <input
            type="text"
            id="copRegNum"
            value={copRegNum || ""}
            onChange={(e) => {
              setCopRegNum(e.target.value);
            }}
            className="var"
            maxLength="10"
            placeholder="사업자번호(10자리)"
          ></input>
        </span>
        {/* manager 부분*/}
        <h3 className="join_title">
          <label>사업자명</label>
        </h3>
        <span className="box var_nickname">
          <input
            type="text"
            id="manager"
            value={manager || ""}
            className="var"
            maxLength="20"
            readOnly
          ></input>
        </span>
        {/* storeName 부분 */}
        <h3 className="join_title">
          <label>매장명</label>
        </h3>
        <span className="box var_pass">
          <input
            type="text"
            id="storeName"
            value={storeName || ""}
            onChange={(e) => {
              setStoreName(e.target.value);
            }}
            className="var"
          ></input>
        </span>
        {/* storeAddress1 부분 */}
        <div>
          <h3 className="join_title">
            <label>매장주소</label>
          </h3>
          <span className="addressbox input-group mb-3">
            <input
              type="text"
              id="storeAddress1"
              className="var"
              maxLength="100"
              value={storeAddress1 || ""}
              readOnly
            />
            <button type="button" id="post" onClick={openModal}>
              <span>주소찾기</span>
            </button>
            <AddressModal
              open={ModalOpen}
              close={closeModal}
              header="주소 검색"
            >
              <DaumPostcode onComplete={handleAddress} />
            </AddressModal>
          </span>
          {/* storeAddress2 부분 */}
          <h3 className="join_title">
            <label>매장상세주소</label>
          </h3>
          <span className="box">
            <input
              type="text"
              id="storeAddress2"
              value={storeAddress2}
              onChange={(e) => {
                setStoreAddress2(e.target.value);
              }}
              className="var"
              maxLength="100"
            ></input>
          </span>
        </div>
        {/* storeContact 부분 */}
        <h3 className="join_title">
          <label>매장 전화번호</label>
        </h3>
        <span className="box var_pass_check">
          <input
            type="tel"
            id="storeContact"
            value={storeContact || ""}
            onChange={(e) => {
              setStoreContant(e.target.value);
            }}
            className="var"
            maxLength="11"
            placeholder="전화번호를 입력하세요(ex 01012345678)"
          ></input>
        </span>
        {/* businessHour 부분 */}
        <h3 className="join_title">
          <label>영업시간</label>
        </h3>
        <span className="box">
          <input
            type="text"
            id="businessHour"
            value={businessHour || ""}
            onChange={(e) => {
              setBusinessHour(e.target.value);
            }}
            className="var"
            maxLength="20"
          ></input>
        </span>
        {/* holiday 부분*/}
        <div>
          <h3 className="join_title">
            <label>정기휴무</label>
          </h3>
          <span className="box">
            <input
              type="text"
              id="holiday"
              value={holiday}
              onChange={(e) => {
                setHoliday(e.target.value);
              }}
              className="var"
            ></input>
          </span>
        </div>
        {/* specialHoliday 부분*/}
        <div>
          <h3 className="join_title">
            <label>임시휴무</label>
          </h3>
          <span className="box">
            <input
              type="text"
              id="specialHoliday"
              value={specialHoliday || ""}
              onChange={(e) => {
                setSpecialHoliday(e.target.value);
              }}
              className="var"
            ></input>
          </span>
        </div>
        {/* eatable 부분 */}
        <div>
          <h3 className="join_title">
            <label>취식여부</label>
          </h3>
          <span className="box">
            <select
              id="eatable"
              value={eatable}
              onChange={(e) => {
                setEatable(e.target.value);
              }}
              className="sel"
            >
              {/* <option>선택</option> */}
              <option value="F">불가능</option>
              <option value="T">가능</option>
            </select>
          </span>
        </div>

        {/*boardSet 부분 */}
        <div>
          <h3 className="join_title">
            <label>게시판활성화</label>
          </h3>
          <span className="box">
            <select
              id="boardSet"
              value={boardSet}
              onChange={(e) => {
                setBoardSet(e.target.value);
              }}
              className="sel"
            >
              <option value="F">비활성화</option>
              <option value="T">활성화</option>
            </select>
          </span>
        </div>
        {/*bakeryPath 부분 */}
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
          {bakeryPath ? (
            <div
              className="bakeryImage"
              style={{
                width: "300px",
                marginLeft: "auto",
                marginRight: "auto",
              }}
            >
              <img
                src={
                  imagePath === null
                    ? bakeryPath + "?" + new Date().getTime()
                    : imagePath
                }
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

        {/* 변경하기 버튼 */}
        <div className="btn_area">
          <button type="submit" id="btnJoin">
            <span>변경하기</span>
          </button>
        </div>
      </div>
    </form>
  );
};

export default ChangeBakery;
