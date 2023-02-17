import RequestForm from "../../../components/RequestForm";
import NavBar from "../../../components/NavBar";

const RequestEditPage = () => {
  return (
    <div>
      <NavBar />
      <div className="container mt-3">
        <RequestForm editing={true} />
      </div>
    </div>
  );
};

export default RequestEditPage;
