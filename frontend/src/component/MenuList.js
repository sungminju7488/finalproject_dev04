import axios from "axios";
import { useEffect } from "react";
import auth from "../Logic/Auth";

function MenuList() {
  useEffect(() => {
    const copRegNum = auth.copRegNum;
    axios
      .post("/bakery/menuList", { copRegNum })
      .then((res) => {
        console.log("통신 완료");
        alert(res.data);
      })
      .catch((err) => alert(err.response.data.msg));
  }, []);

  return (
    <div>
      <table>
        <tr>
          <th>제품명</th>
          <th>종류</th>
          <th>판매매장</th>
          <th>판매시각</th>
          <th>제품가격</th>
        </tr>
      </table>
    </div>
  );
}

export default MenuList;
