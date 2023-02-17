import FreeForm from "../../../components/FreeForm";
import NavBar from "../../../components/NavBar";

const FreeEditPage = () => {
  return (
    <div>
      <NavBar />
      <div className="container mt-3">
        <FreeForm editing={true} />
      </div>
    </div>
  );
};

export default FreeEditPage;
