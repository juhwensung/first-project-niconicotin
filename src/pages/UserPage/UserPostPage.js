import UserList from "../../components/UserList";
import NavBar from "../../components/NavBar";
import "./sub.css";

const UserPostPage = () => {
  return (
    <>
      <NavBar />
      <div className="container mt-3">
        <div>
          <div className="d-flex justify-content-between">
            <div className="nicosub">유저 정보</div>
          </div>
          <UserList />
        </div>
      </div>
    </>
  );
};

export default UserPostPage;
