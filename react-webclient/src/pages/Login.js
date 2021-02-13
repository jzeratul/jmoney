import React, { useState, useRef } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

import AuthService from "../services/AuthService";
import { Card, Col, Container, Row } from "react-bootstrap";
import { IconUserPlus } from "@tabler/icons";

const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        {" "}
        This field is required!{" "}
      </div>
    );
  }
};

const Login = (props) => {
  const form = useRef();
  const checkBtn = useRef();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const onChangeUsername = (e) => {
    const username = e.target.value;
    setUsername(username);
  };

  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  };

  const handleLogin = (e) => {
    e.preventDefault();

    setMessage("");
    setLoading(true);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {
      AuthService.login(username, password).then(
        () => {
          props.history.push("/jmoney/dashboard");
          window.location.reload();
        },
        (error) => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          setLoading(false);
          setMessage(resMessage);
        }
      );
    } else {
      setLoading(false);
    }
  };

  return (
    // <main>
    //   <Container>
    //     <Row className="justify-content-md-center">
    //       <Col xs lg="2"></Col>
    //       <Col md="auto">
    //         <Card style={{ width: "18rem" }}>
    //           <Card.Header>
    //             <IconUserPlus
    //               size={36}
    //               color="red"
    //               stroke={3}
    //               strokeLinejoin="miter"
    //             />
    //           </Card.Header>
    //           <Card.Body>
    //             <Form onSubmit={handleLogin} ref={form}>
    //               <div className="form-group">
    //                 <label htmlFor="username">Username</label>
    //                 <Input
    //                   type="text"
    //                   className="form-control"
    //                   name="username"
    //                   value={username}
    //                   onChange={onChangeUsername}
    //                   validations={[required]}
    //                 />
    //               </div>

    //               <div className="form-group">
    //                 <label htmlFor="password">Password</label>
    //                 <Input
    //                   type="password"
    //                   className="form-control"
    //                   name="password"
    //                   value={password}
    //                   onChange={onChangePassword}
    //                   validations={[required]}
    //                 />
    //               </div>

    //               <div className="form-group">
    //                 <button
    //                   className="btn btn-primary btn-block"
    //                   disabled={loading}
    //                 >
    //                   {loading && (
    //                     <span className="spinner-border spinner-border-sm"></span>
    //                   )}
    //                   <span>Login</span>
    //                 </button>
    //               </div>

    //               <div className="form-group">
    //                 <a className="btn btn-link btn-block" href="/jmoney">
    //                   <span>Back</span>
    //                 </a>
    //               </div>

    //               {message && (
    //                 <div className="form-group">
    //                   <div className="alert alert-danger" role="alert">
    //                     {message}
    //                   </div>
    //                 </div>
    //               )}
    //               <CheckButton style={{ display: "none" }} ref={checkBtn} />
    //             </Form>
    //           </Card.Body>
    //         </Card>
    //       </Col>
    //       <Col xs lg="2"></Col>
    //     </Row>
    //   </Container>
    // </main>


    <main className="hold-transition login-page">

      <div className="login-box">
        <div className="login-logo">
          <a href="../../index2.html"><b>Admin</b>LTE</a>
        </div>
        <div className="card">
          <div className="card-body login-card-body">
            <p className="login-box-msg">Sign in to start your session</p>

            <Form action="../../index3.html" method="post">
              <div className="input-group mb-3">
                <input type="email" className="form-control" placeholder="Email" />
                <div className="input-group-append">
                  <div className="input-group-text">
                    <span className="fas fa-envelope"></span>
                  </div>
                </div>
              </div>
              <div className="input-group mb-3">
                <input type="password" className="form-control" placeholder="Password" />
                <div className="input-group-append">
                  <div className="input-group-text">
                    <span className="fas fa-lock"></span>
                  </div>
                </div>
              </div>
              <div className="row">
                <div className="col-8">
                  <div className="icheck-primary">
                    <input type="checkbox" id="remember" />
                    <label for="remember">
                      Remember Me
              </label>
                  </div>
                </div>
                <div className="col-4">
                  <button type="submit" className="btn btn-primary btn-block">Sign In</button>
                </div>
              </div>
            </Form>

            <div className="social-auth-links text-center mb-3">
              <p>- OR -</p>
              <a href="#" className="btn btn-block btn-primary">
                <i className="fab fa-facebook mr-2"></i> Sign in using Facebook
                    </a>
              <a href="#" className="btn btn-block btn-danger">
                <i class="fab fa-google-plus mr-2"></i> Sign in using Google+
                    </a>
            </div>

            <p className="mb-1">
              <a href="forgot-password.html">I forgot my password</a>
            </p>
            <p className="mb-0">
              <a href="register.html" className="text-center">Register a new membership</a>
            </p>
          </div>
        </div>
      </div>

    </main >

  );
};

export default Login;
