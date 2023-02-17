import { Link, NavLink } from "react-router-dom";
import Logout from "../pages/UserPage/Login/Logout";
import nico from "./img/N33.png";
import "./Nav.css";

const NavBar = () => {
  return (
    <nav className="navbar bg-black" data-bs-theme="dark">
      <div className="container">
        <Link className="navbar-brand" to="/">
          <img src={nico} width="auto" height="30px" />
        </Link>
        <ul className="navbar-nav" style={{ flexDirection: "row" }}>
          <li className="nav-item me-3">
            <NavLink
              className={({ isActive }) =>
                "nav-link" + (isActive ? " active" : "")
              }
              aria-current="page"
              to="/home"
            >
              <p className="nicoft">메인페이지</p>
            </NavLink>
          </li>
          <li className="nav-item me-3">
            <NavLink
              className={({ isActive }) =>
                "nav-link" + (isActive ? " active" : "")
              }
              aria-current="page"
              to="/FreeList"
            >
              <p className="nicoft">자유게시판</p>
            </NavLink>
          </li>
          <li className="nav-item me-3">
            <NavLink
              className={({ isActive }) =>
                "nav-link" + (isActive ? " active" : "")
              }
              aria-current="page"
              to="/CigarRequest"
            >
              <p className="nicoft">담배요청</p>
            </NavLink>
          </li>
          <li className="nav-item me-3">
            <NavLink
              className={({ isActive }) =>
                "nav-link" + (isActive ? " active" : "")
              }
              aria-current="page"
              to="/AnnounCement"
            >
              <p className="nicoft">공지사항</p>
            </NavLink>
          </li>
          <li className="nav-item">
            <NavLink
              className={({ isActive }) =>
                "nav-link" + (isActive ? " active" : "")
              }
              aria-current="page"
              to="/map"
            >
              <p className="nicoft">흡연구역</p>
            </NavLink>
          </li>
          <li className="nav-item">
            <NavLink
              className={({ isActive }) =>
                "nav-link" + (isActive ? " active" : "")
              }
              aria-current="page"
              to="/Admin"
            >
              <p className="nicoft">&nbsp;관리자 페이지</p>
            </NavLink>
          </li>
          <Logout />
        </ul>
      </div>
    </nav>
  );
};

export default NavBar;
