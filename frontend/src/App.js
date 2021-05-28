import React, { Component } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";

//전환용 import
import Navbar from "./component/Navbar";
import Join from "./component/Join";
import Login from "./component/Login";


class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <Switch>
          <Route exact path="/" component= {Navbar} />
          <Route exact path="/member/join" component= {Join}/>
          <Route exact path="/member/login" component= {Login}/>
        </Switch>
      </BrowserRouter>
    );
  }
}

export default App;
