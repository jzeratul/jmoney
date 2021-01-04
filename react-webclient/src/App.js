import React, { useState, useEffect } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AuthService from "./services/AuthService";

import Login from "./components/Login";
import Register from "./components/Register";
import Dashboard from "./components/Dashboard";

const App = () => {
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
    }
  }, []);

  const logOut = () => {
    AuthService.logout();
  };

  return (
    <div>
      {currentUser ? (
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <Link to={"/jmoney"} className="navbar-brand"> JMoney </Link>
        <div className="navbar-nav mr-auto">
          <li className="nav-item"> <Link to={"/jmoney/dashboard"} className="nav-link"> Home </Link> </li>
          {currentUser && ( <li className="nav-item"> <Link to={"/user"} className="nav-link"> User </Link> </li> )}
        </div>

          <div className="navbar-nav ml-auto">
            <li className="nav-item">  {currentUser.username} </li>
            <li className="nav-item"> <a href="/jmoney/login" className="nav-link" onClick={logOut}> LogOut </a> </li>
          </div>
        ) : (
          <div className="navbar-nav ml-auto">
            <li className="nav-item"> <Link to={"/jmoney/login"} className="nav-link"> Login </Link> </li>
            <li className="nav-item"> <Link to={"/jmoney/register"} className="nav-link"> Sign Up </Link> </li>
          </div>

      </nav>
      )}

      <div className="container mt-3">
        <Switch>
          <Route exact path={["/jmoney", "/jmoney/dashboard"]} component={Dashboard} />
          <Route exact path="/jmoney/login" component={Login} />
          <Route exact path="/jmoney/register" component={Register} />
        </Switch>
      </div>
    </div>
  );
};

export default App;
