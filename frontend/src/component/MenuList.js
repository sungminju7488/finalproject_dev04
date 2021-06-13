import axios from "axios";
import { useEffect } from "react";
import auth from "../Logic/Auth";

function MenuList() {
  useEffect(() => {
    const copRegNum = auth.copRegNum;
    // axios
    //   .post("/bakery/menuList", { copRegNum })
    //   .then((res) => {
    //     console.log("통신 완료");
    //     alert(res.data);
    //   })
    //   .catch((err) => alert(err.response.data.msg));
  }, []);

  return (
    <div id="content">
      {/* header부분 */}
      <div id="header">
        <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
          <span id="sunbbang">선 빵</span>
        </a>
      </div>
      <span className="box">
        <div className="var"></div>
      </span>
    </div>
  );
}

export default MenuList;
