import React, { Component } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";

//전환용 import
import Main from "./component/Main";
import Join from "./component/Join";
import Login from "./component/Login";
import BakeryMap from "./component/BakeryMap";

class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <Switch>
          <Route exact path="/" component={Main} />
          <Route exact path="/service/bakeryMap" component={BakeryMap} />
          <Route exact path="/member/joinpage" component={Join} />
          <Route exact path="/member/loginpage" component={Login} />
        </Switch>
      </BrowserRouter>
    );
  }
}

export default App;
