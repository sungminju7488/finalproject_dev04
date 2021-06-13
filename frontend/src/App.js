import React, { Component } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import auth from "./Logic/Auth";

//전환용 import
import Main from "./component/Main";
import Join from "./component/Join";
import Login from "./component/Login";
import BakeryMap from "./component/BakeryMap";
import ChangeGradeConfirm from "./component/ChangeGradeConfirm";
import JoinBakery from "./component/JoinBakery";
import MyShop from "./component/MyShop";
import Mypage from "./component/Mypage";
import Modify from "./component/Modify";
import ChangeBakery from "./component/ChangeBakery";
import MenuList from "./component/MenuList";
import AddMenu from "./component/AddMenu";

class App extends Component {
  constructor(props) {
    super(props);
    auth.checkAuth();
  }
  render() {
    return (
      <BrowserRouter>
        <Switch>
          <Route exact path="/" component={Main} />
          {/* 서비스 */}
          <Route exact path="/service/bakerymap" component={BakeryMap} />
          {/* 멤버 */}
          <Route exact path="/member/joinpage" component={Join} />
          <Route exact path="/member/loginpage" component={Login} />
          <Route exact path="/member/mypagepage" component={Mypage} />
          <Route exact path="/member/changememberpage" component={Modify} />
          <Route
            exact
            path="/member/changegradeconfirmpage"
            component={ChangeGradeConfirm}
          />
          {/* 베이커리 */}
          <Route exact path="/bakery/joinbakerypage" component={JoinBakery} />
          <Route exact path="/bakery/myshoppage" component={MyShop} />
          <Route
            exact
            path="/bakery/changebakerypage"
            component={ChangeBakery}
          />
          <Route exact path="/bakery/menulistpage" component={MenuList} />
          <Route exact path="/bakery/addmanupage" component={AddMenu} />
        </Switch>
      </BrowserRouter>
    );
  }
}

export default App;
