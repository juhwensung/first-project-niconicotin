import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";

const Logout = () => {
  const navigate = useNavigate();
  // eslint-disable-next-line
  const [cookies, setCookie] = useCookies([]);

  const handleLogout = () => {
    localStorage.clear();
    navigate("/login");
  };
  return (
    <>
      <button className="niconav-btn" id="logoutBtn" onClick={handleLogout}>
        Logout
      </button>
    </>
  );
};
export default Logout;
