import React, { Component } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";

//전환용 import
import Main from "./component/Main";
import Join from "./component/Join";
import Login from "./component/Login";
import Mypage from "./component/Mypage";
import Modify from "./component/Modify";
import Quit from "./component/Quit";

class App extends Component {
  render() {
    const isLogin = false;
    return (
      
      <BrowserRouter>
        <Switch>
          <Route exact path="/" component={Main} />
          <Route exact path="/member/join1" component={Join} />
          <Route exact path="/member/login1" component={Login} />
          <Route exact path="/member/mypage1" component={Mypage}/>
          <Route exact path="/member/changemember1" component={Modify}/>
          <Route exact path="/member/quit1" component={Quit}/>
        </Switch>
      </BrowserRouter>
     );
  }
}

export default App;
