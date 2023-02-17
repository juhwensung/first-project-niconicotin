import React, { useEffect, useState } from "react";
import Axios from "axios";
import { useNavigate } from "react-router-dom";
import Navbar from "../../../components/NavBar";
import DatePicker from "react-datepicker";

import "./Singup.css";
import "react-datepicker/dist/react-datepicker.css";
import axios from "axios";

function Signup() {
  let navigate = useNavigate();
  const [registerEmail, setRegisterEmail] = useState("");
  const [registerUser, setRegisterUser] = useState("");
  const [registerNick, setRegisterNick] = useState("");
  const [registerPassword, setRegisterPassword] = useState("");
  const [registerPasswordC, setRegisterPasswordC] = useState("");
  const [registerPhone, setRegisterPhone] = useState("010");
  const [registerPhone1, setRegisterPhone1] = useState("");
  const [registerPhone2, setRegisterPhone2] = useState("");
  const [registerBracket, setRegisterBracket] = useState(0);
  const [registerBirthday, setRegisterBirthday] = useState("");
  const [registerJumin1, setRegisterJumin1] = useState("");
  const [registerJumin2, setRegisterJumin2] = useState("");
  const [loginStatus, setLoginStatus] = useState(false);

  const [startDate, setStartDate] = useState(new Date());

  const register = () => {
    console.log("clicked");
    Axios.post("http://localhost:3001/users", {
      email: registerEmail,
      username: registerUser,
      nickname: registerNick,
      password: registerPassword,
      passwordC: registerPasswordC,
      phone: registerPhone,
      phone1: registerPhone1,
      phone2: registerPhone2,
      bracket: registerBracket,
      birthday: registerBirthday,
      Jumin1: registerJumin1,
      Jumin2: registerJumin2,
    }).then((response) => {
      setLoginStatus(false);
      console.log(response.data.message);
      navigate("/login");
    });
  };

  async function f2() {
    const res = await axios
      .get("https://jsonplaceholder.typicode.com/posts/1")
      .catch((err) => {
        console.log(err);
      });

    console.log(res);
  }

  // 이메일 유효성 검사
  const checkEmail = (e) => {
    var regExp =
      /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
    // 형식에 맞는 경우 true 리턴
    console.log("이메일 유효성 검사 :: ", regExp.test(e.target.value));
  };

  return (
    <>
      <Navbar />
      <div className="container-cat">
        <div className="register-form">
          <div className="register-text">니코니코틴 회원가입</div>
          <div className="center-register-form">
            <div className="nicoS-text" onBlur={checkEmail}>
              Email
            </div>
            <div className="nicoSWrap">
              <input
                type="text"
                className="nicoS-tbox"
                name="email"
                placeholder="Email"
                value={registerEmail}
                onChange={(e) => {
                  setRegisterEmail(e.target.value);
                }}
              />
              <button className="nicoSr-btn" onClick={f2}>
                중복 확인
              </button>
            </div>
            <div className="nicoS-text">비밀번호</div>
            <div className="nicoSWrap">
              <input
                type="password"
                className="nicoS-tbox"
                placeholder="Password"
                value={registerPassword}
                onChange={(e) => {
                  setRegisterPassword(e.target.value);
                }}
              />
            </div>
            <div className="nicoS-text">비밀번호 확인</div>
            <div className="nicoSWrap">
              <input
                type="password"
                className="nicoS-tbox"
                placeholder="Password Check"
                value={registerPasswordC}
                onChange={(e) => {
                  setRegisterPasswordC(e.target.value);
                }}
              />
            </div>
            <div className="nicoS-text">이름</div>
            <div className="nicoSWrap">
              <input
                type="text"
                className="nicoS-tbox"
                placeholder="이름"
                value={registerUser}
                onChange={(e) => {
                  setRegisterUser(e.target.value);
                }}
              />
            </div>
            <div className="nicoS-text">닉네임</div>
            <div className="nicoSWrap">
              <input
                type="text"
                className="nicoS-tbox"
                placeholder="닉네임"
                value={registerNick}
                onChange={(e) => {
                  setRegisterNick(e.target.value);
                }}
              />
              <button className="nicoSr-btn" onClick={f2}>
                중복 확인
              </button>
            </div>
            <div className="nicoS-text">흡연연차</div>
            <div className="nicoSWrap">
              <input
                type="number"
                className="nicoS-tbox"
                name="investment-bracket2"
                placeholder="0"
                value={registerBracket}
                onChange={(e) => {
                  setRegisterBracket(e.target.value);
                }}
              />
            </div>
            <div className="nicoS-text">연락처</div>
            <div className="nicoSWrap">
              <input
                type="text"
                className="nicoSp1-tbox"
                placeholder="010"
                value={registerPhone}
                onChange={(e) => {
                  setRegisterPhone(e.target.value);
                }}
              />
              -
              <input
                type="text"
                className="nicoSp-tbox"
                placeholder="1234"
                value={registerPhone1}
                onChange={(e) => {
                  setRegisterPhone1(e.target.value);
                }}
              />
              -
              <input
                type="text"
                className="nicoSp-tbox"
                placeholder="5678"
                value={registerPhone2}
                onChange={(e) => {
                  setRegisterPhone2(e.target.value);
                }}
              />
            </div>
            <div className="nicoS-text">BirthDay</div>
            <div className="nicoSWrap">
              <input
                className="nicoS-tbox"
                placeholder="BirthDay"
                value={registerBirthday}
                onChange={(e) => {
                  setRegisterBirthday(e.target.value);
                }}
              />
              <DatePicker
                className="nicoS-tbox"
                selected={startDate}
                onChange={(date) => setStartDate(date)}
              />
            </div>
            <div className="nicoS-text">주민등록번호</div>
            <div className="nicoSWrap">
              <input
                type="text"
                className="nicoSj1-tbox"
                placeholder="앞자리 6자리"
                value={registerJumin1}
                onChange={(e) => {
                  setRegisterJumin1(e.target.value);
                }}
              />
              -
              <input
                type="text"
                className="nicoSj-tbox"
                placeholder="뒷자리 7자리"
                value={registerJumin2}
                onChange={(e) => {
                  setRegisterJumin2(e.target.value);
                }}
              />
            </div>
            <div className="jumin">
              저희는 주민번호를 검사 용도로만 사용하고 수집을 하지 않습니다.
            </div>
            <div>
              <button className="btn nicoS-btn" onClick={register}>
                회원가입
              </button>
            </div>
            <div>
              <button to="/login" className="btn nicoS-btn" onClick={register}>
                취소
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Signup;
