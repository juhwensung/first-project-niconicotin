import { Link } from "react-router-dom";
import AnnouncementList from "../../../components/AnnouncementList";
import NavBar from "../../../components/NavBar";

const Announcement = () => {
  return (
    <>
      <NavBar />
      <div className="container mt-3">
        <div>
          <div className="d-flex justify-content-between">
            <div className="nicosub">공지사항</div>
            <div>
              <Link to="/announcement/create" className="btn nico-btn">
                작 성
              </Link>
            </div>
          </div>
          <AnnouncementList />
        </div>
      </div>
    </>
  );
};

export default Announcement;
