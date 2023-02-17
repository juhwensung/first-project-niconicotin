import { useParams } from "react-router-dom";
import axios from "axios";
import { useEffect, useState } from "react";
import LoadingSpinner from "../../../components/LoadingSpinner";
import NavBar from "../../../components/NavBar";
import "../Showpage.css";
import "../sub.css";

const CigarShowPage = () => {
  const { id } = useParams();
  const [post, setPost] = useState(null);
  const [loading, setLoading] = useState(true);

  const getPost = (id) => {
    axios.get(`http://localhost:3001/cigarposts/${id}`).then((res) => {
      setPost(res.data);
      setLoading(false);
    });
  };

  useEffect(() => {
    getPost(id);
  }, [id]);

  if (loading) {
    return <LoadingSpinner />;
  }
  return (
    <div>
      <NavBar />
      <div className="container mt-3">
        <div className="d-flex">
          <h1 className="flex-grow-1">{post.title}</h1>
        </div>
        <hr width="100%" />
        <div className="nicoshowbody">
          <p className="nicoshowtext">{post.body}</p>
        </div>
      </div>
    </div>
  );
};

export default CigarShowPage;
