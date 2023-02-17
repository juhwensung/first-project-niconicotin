import CigarList from "../../../components/CigarList";
import NavBar from "../../../components/NavBar";
import "../sub.css";

const CigarPostPage = () => {
  return (
    <>
      <NavBar />
      <div className="container mt-3">
        <div>
          <div className="d-flex justify-content-between">
            <div className="nicosub">담배 정보</div>
          </div>
          <CigarList />
        </div>
      </div>
    </>
  );
};

export default CigarPostPage;
