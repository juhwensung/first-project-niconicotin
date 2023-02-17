import { Link } from "react-router-dom";
import CigarRequestList from "../../../components/CigarRequestList";
import NavBar from "../../../components/NavBar";

const CigarRequest = () => {
  return (
    <>
      <NavBar />
      <div className="container mt-3">
        <div>
          <div className="d-flex justify-content-between">
            <div className="nicosub">담배 요청</div>
            <div>
              <Link to="/FreeList/create" className="btn nico-btn">
                작 성
              </Link>
            </div>
          </div>
          <CigarRequestList />
        </div>
      </div>
    </>
  );
};

export default CigarRequest;
