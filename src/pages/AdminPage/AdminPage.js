import { Link } from "react-router-dom";
import FreeList from "../../components/AdminFreeList";
import CigarList from "../../components/CigarList";
import Request from "../../components/AdminCigarRequestList";
import Announcement from "../../components/AdminAnnouncementList";
import UserList from "../../components/UserList";
import NavBar from "../../components/NavBar";
import "./AdminCard.css";

// import { Link } from "react-router-dom";
// import FreeList from "../../components/Admin/AdminFree";
// import CigarList from "../../components/CigarList";
// import Request from "../../components/Admin/AdminRequest";
// import Announcement from "../../components/Admin/AdminAnnoun";
// import UserList from "../../components/UserList";
// import NavBar from "../../components/NavBar";
// import "./AdminCard.css";

const AdminPage = () => {
  return (
    <div className="Adminpage">
      <NavBar />

      <div className="Adminpage">
        <div className="AdminSub">관리자 페이지</div>
        <div className="AdminLine"></div>
        <>
          <div>
            <div className="AdminCard">
              <Link to="/FreeList" className="btn AdminButton">
                자유 게시판
              </Link>
              <div>
                <FreeList isAdmin={true} />
              </div>
            </div>

            <div className="AdminCard">
              <Link to="/CigarRequest" className="btn AdminButton">
                담배 요청
              </Link>
              <div>
                <Request isAdmin={true} />
              </div>
            </div>

            <div className="AdminCard">
              <Link to="/Announcement" className="btn AdminButton">
                공지사항
              </Link>
              <div>
                <Announcement isAdmin={true} />
              </div>
            </div>
            <div className="AdminCard">
              {/* <Link to="/CigarRequest" className="btn AdminButton"> */}
              <Link to="/home" className="btn AdminButton">
                담배 정보
              </Link>
              <div>
                <CigarList isAdmin={true} />
              </div>
            </div>
          </div>
          <div>
            <div className="AdminUserCard">
              <Link to="/UserList" className="btn AdminButton">
                유저 정보
              </Link>
              <div>
                <UserList isAdmin={true} />
              </div>
            </div>
          </div>
        </>
      </div>
    </div>
  );
};

export default AdminPage;
