import axios from "axios";
import { useState } from "react";
import { Link, Redirect } from "react-router-dom";
import auth from "../Logic/Auth";
import jwtdecode from "jwt-decode";

function ChangeGradeConfirm() {
  const [password, setPassword] = useState("");
  const [identificationSuccess, setIdentificationSuccess] = useState(false);

  const handleIdentification = (event) => {
    event.preventDefault();
    const memberId = auth.memberId;
    axios
      .post("/member/login", { memberId, password })
      .then((res) => {
        const payload = jwtdecode(res.data.accessToken);
        if (payload.memberId === auth.memberId) {
          setIdentificationSuccess(true);
          window.location.reload();
        }
      })
      .catch((err) => {
        if (err.response.data.msg === undefined) {
          alert("암호가 맞지 않습니다.");
        } else {
          alert(err.response.data.msg);
        }
      });
  };

  if (identificationSuccess) return <Redirect to="/bakery/joinbakerypage" />;
  else {
    return (
      <form onSubmit={handleIdentification}>
        {/* body */}
        <div id="content">
          <div id="header">
            <Link to="/" id="sunbbang">
              본인확인
            </Link>
          </div>
          <h3 className="join_title">
            <label>회원 비밀번호</label>
          </h3>
          <span className="box var_pass">
            <input
              type="password"
              id="pswd1"
              onChange={(e) => {
                setPassword(e.target.value);
              }}
              className="var"
              maxLength="20"
            ></input>
          </span>
          <div className="btn_area">
            <button type="submit" id="btnJoin">
              <span>확인</span>
            </button>
          </div>
        </div>
      </form>
    );
  }
}

export default ChangeGradeConfirm;
