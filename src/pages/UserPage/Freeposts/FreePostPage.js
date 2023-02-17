import { Link } from "react-router-dom";
import FreeList from "../../../components/FreeList";
import NavBar from "../../../components/NavBar";
import "../sub.css";

const FreePostPage = () => {
  return (
    <>
      <NavBar />
      <div className="container mt-3">
        <div>
          <div className="d-flex justify-content-between">
            <div className="nicosub">자유 게시판</div>
            <div>
              <Link to="/FreeList/create" className="btn nico-btn">
                작 성
              </Link>
            </div>
          </div>
          <FreeList />
        </div>
      </div>
    </>
  );
};

export default FreePostPage;
