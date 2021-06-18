function FindIdResult(props) {
  const moveLoginHandler = () => {
    window.location.replace("/member/loginpage");
  };

  return (
    <div id="content">
      {/* header부분 */}
      <div id="header">
        <a href="/" target="_self" title="선빵 회원가입 페이지 보러가기">
          <span id="sunbbang">아이디 찾기</span>
        </a>
      </div>
      {/* id 부분*/}
      <h3 className="join_title">
        <label>아이디</label>
      </h3>
      <span className="box var_id">
        <input
          type="text"
          id="id"
          value={props.location.state.memberId || ""}
          className="var"
          readOnly
        ></input>
      </span>
      <div id="btn_area">
        <button type="button" id="login_btn" onClick={moveLoginHandler}>
          <span>로그인하기</span>
        </button>
      </div>
    </div>
  );
}
export default FindIdResult;
