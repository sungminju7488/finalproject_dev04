import React, { Component } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import auth from "./Logic/Auth";

//전환용 import
import Main from "./component/Main";
import Join from "./component/Join";
import Login from "./component/Login";
import Quit from "./component/Quit";
import BakeryMap from "./component/BakeryMap";
import ChangeGradeConfirm from "./component/ChangeGradeConfirm";
import JoinBakery from "./component/JoinBakery";
import MyShop from "./component/MyShop";
import Mypage from "./component/Mypage";
import FindId from "./component/FindId";
import FindPassword from "./component/FindPassword";
import FindIdResult from "./component/FindIdResult";
import FindPasswordResult from "./component/FindPasswordResult";
import Modify from "./component/Modify";
import ChangeBakery from "./component/ChangeBakery";
import MenuList from "./component/MenuList";
import AddMenu from "./component/AddMenu";
import ModifyMenu from "./component/ModifyMenu";
import BakeryArticleList from "./component/BakeryArticleList";
import WriteArticle from "./component/WriteArticle";
import ReadArticle from "./component/ReadArticle";
import ModifyArticle from "./component/ModifyArticle";
import SearchFood from "./component/SearchFood";

class App extends Component {
  constructor(props) {
    super(props);
    auth.checkAuth();
  }
  render() {
    return (
      <BrowserRouter>
        <Switch>
          {/* 루트 */}
          <Route exact path="/" component={Main} />
          {/* 서비스 */}
          <Route exact path="/service/bakerymap" component={BakeryMap} />
          {/* 멤버 */}
          <Route exact path="/member/joinpage" component={Join} />
          <Route exact path="/member/loginpage" component={Login} />
          <Route exact path="/member/mypagepage" component={Mypage} />
          <Route exact path="/member/findidpage" component={FindId} />
          <Route
            exact
            path="/member/findpasswordpage"
            component={FindPassword}
          />
          <Route
            exact
            path="/member/findidresultpage"
            component={FindIdResult}
          />
          <Route
            exact
            path="/member/findpasswordresultpage"
            component={FindPasswordResult}
          />
          <Route exact path="/member/changememberpage" component={Modify} />
          <Route
            exact
            path="/member/changegradeconfirmpage"
            component={ChangeGradeConfirm}
          />
          <Route exact path="/member/quitpage" component={Quit} />
          {/* 베이커리 */}
          <Route exact path="/bakery/joinbakerypage" component={JoinBakery} />
          <Route exact path="/bakery/myshoppage" component={MyShop} />
          <Route
            exact
            path="/bakery/changebakerypage"
            component={ChangeBakery}
          />
          <Route exact path="/bakery/menulistpage" component={MenuList} />
          <Route exact path="/bakery/addmenupage" component={AddMenu} />
          <Route exact path="/bakery/modifymenupage" component={ModifyMenu} />
          <Route exact path="/bakery/searchfoodpage" component={SearchFood} />
          {/* 게시판 */}
          <Route
            exact
            path="/article/bakeryarticlelistpage"
            component={BakeryArticleList}
          />
          <Route
            exact
            path="/article/writearticlepage"
            component={WriteArticle}
          />
          <Route
            exact
            path="/article/readarticlepage"
            component={ReadArticle}
          />
          <Route
            exact
            path="/article/modifyarticlepage"
            component={ModifyArticle}
          />
        </Switch>
      </BrowserRouter>
    );
  }
}

export default App;
