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
          <Route
            exact
            path="/member/changegradeconfirmpage"
            component={ChangeGradeConfirm}
          />
          {/* 베이커리 */}
          <Route exact path="/bakery/joinbakerypage" component={JoinBakery} />
          <Route exact path="/bakery/myshoppage" component={MyShop} />
        </Switch>
      </BrowserRouter>
    );
  }
}

export default App;
