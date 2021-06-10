import axios from "axios";
import jwt from "jwt-decode";

axios.defaults.baseURL = "http://localhost:3000";
axios.defaults.withCredetials = true;

//기본리턴
export default {
  loggedIn: false,
  memberSeq: null,
  memberId: null,
  name: null,
  nickName: null,
  grade: null,
  alarmSet: null,
  followSet: null,

  logout() {
    this.loggedIn = false;
    sessionStorage.clear();
  },

  checkAuth() {
    //로그인 데이터 체크
    const accessToken = sessionStorage.getItem("accessToken");
    if (!accessToken) return; //토큰이 없다면 비로그인 상태

    //데이터 세팅
    this.loggedIn = true;
    this.memberSeq = sessionStorage.getItem("memberSeq");
    this.memberId = sessionStorage.getItem("memberId");
    this.name = sessionStorage.getItem("name");
    this.nickName = sessionStorage.getItem("nickName");
    this.grade = sessionStorage.getItem("grade");
    this.alarmSet = sessionStorage.getItem("alarmSet");
    this.followSet = sessionStorage.getItem("followSet");

    //API 요청하는 콜마다 헤더에 accessToken을 담아 보내도록 설정
    axios.defaults.headers.common["Authorization"] = `Bearer ${accessToken}`;
    axios.defaults.headers.get["Authorization"] = `Bearer ${accessToken}`;
    axios.defaults.headers.post["Authorization"] = `Bearer ${accessToken}`;
  },

  setAuth(data) {
    axios.defaults.baseURL = "http://localhost:3000";
    //토큰 분리
    const accessToken = data.accessToken;
    const jwtDecode = jwt(accessToken);

    //데이터 세팅
    this.loggedIn = true;
    this.memberSeq = jwtDecode.memberSeq;
    this.memberId = jwtDecode.memberId;
    this.name = jwtDecode.name;
    this.nickName = jwtDecode.nickName;
    this.grade = jwtDecode.grade;
    this.alarmSet = jwtDecode.alarmSet;
    this.followSet = jwtDecode.followSet;

    //브라우저 저장
    sessionStorage.setItem("memberSeq", jwtDecode.memberSeq);
    sessionStorage.setItem("memberId", jwtDecode.memberId);
    sessionStorage.setItem("name", jwtDecode.name);
    sessionStorage.setItem("nickName", jwtDecode.nickName);
    sessionStorage.setItem("grade", jwtDecode.grade);
    sessionStorage.setItem("alarmSet", jwtDecode.alarmSet);
    sessionStorage.setItem("followSet", jwtDecode.followSet);
    sessionStorage.setItem("accessToken", data.accessToken);
    sessionStorage.setItem("refreshToken", data.refreshToken);

    //API 요청하는 콜마다 헤더에 accessToken을 담아 보내도록 설정
    axios.defaults.headers.common[
      "Authorization"
    ] = `Bearer ${data.accessToken}`;
    axios.defaults.headers.get["Authorization"] = `Bearer ${data.accessToken}`;
    axios.defaults.headers.post["Authorization"] = `Bearer ${data.accessToken}`;
  },
};
