import React from "react";
import { useState, useEffect } from "react";
import Axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import Navbar from "../../../components/NavBar";
import "./Login.css";

const Login = () => {
  let navigate = useNavigate();
  const [loginUser, setLoginUser] = useState("");
  const [loginPassword, setLoginPassword] = useState("");
  const [loginStatus, setLoginStatus] = useState(false);

  Axios.defaults.withCredentials = true;

  const login = () => {
    // console.log("clicked")
    Axios.post("api/loginOk.do", {
      username: loginUser,
      password: loginPassword,
    }).then((response) => {
      console.log("react login", response);
      if (response.data) {
        setLoginStatus(true);
        console.log(response.data);
        localStorage.setItem("emailss", response.data.emailss);
        localStorage.setItem("member_seq", response.data.member_seq);
        localStorage.setItem("name", response.data.name);
        localStorage.setItem("phone", response.data.phone);
        localStorage.setItem("nickname", response.data.nickname);
        localStorage.setItem("smoke_years", response.data.smoke_years);
        localStorage.setItem("birthday", response.data.birthday);
      } else {
        setLoginStatus(false);
        
      }
    });
  };

  const getUser = () => {
    const user = localStorage.getItem("emailss");
    return user;
  };

  useEffect(() => {
    const token = localStorage.getItem("member_seq");
    const userID = localStorage.getItem("emailss");

    if (token && userID) {
      setLoginStatus(true);
    }

    if (loginStatus) {
      navigate("/home");
    }
  }, [loginStatus]);
  return (
    <>
      <Navbar />
      <div className="container-cat">
        <div className="login-form">
          <div className="login-text">니코니코틴 로그인</div>
          <div className="center-form">
            <div className="nico-id">이메일</div>
            <div className="inputWrap">
              <input
                type="text"
                className="login-id"
                placeholder="Email"
                value={loginUser}
                onChange={(e) => {
                  setLoginUser(e.target.value);
                }}
              />
            </div>

            <div className="nico-pw">비밀번호</div>
            <div className="inputWrap">
              <input
                type="password"
                className="login-pw"
                placeholder="비밀번호"
                value={loginPassword}
                onChange={(e) => {
                  setLoginPassword(e.target.value);
                }}
              />
            </div>
            <div>
              <button className="btn login-btn" onClick={login}>
                로그인
              </button>
            </div>
            <div>
              <Link to="/signup" className="btn join-btn">
                회원가입
              </Link>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Login;
