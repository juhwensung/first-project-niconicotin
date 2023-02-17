import AnnouncementForm from "../../../components/AnnounForm";
import NavBar from "../../../components/NavBar";

const AnnounEditPage = () => {
  return (
    <div>
      <NavBar />
      <div className="container mt-3">
        <AnnouncementForm editing={true} />
      </div>
    </div>
  );
};

export default AnnounEditPage;
