import { useParams } from "react-router-dom";
import axios from "axios";
import { useEffect, useState } from "react";
import LoadingSpinner from "../../../components/LoadingSpinner";
import { Link } from "react-router-dom";
import NavBar from "../../../components/NavBar";

const RequestShowPage = () => {
  const { id } = useParams();
  const [post, setPost] = useState(null);
  const [loading, setLoading] = useState(true);

  const getPost = (id) => {
    axios.get(`http://localhost:3001/cigarrequest/${id}`).then((res) => {
      setPost(res.data);
      setLoading(false);
    });
  };

  useEffect(() => {
    getPost(id);
  }, [id]);

  const printDate = (timestamp) => {
    return new Date(timestamp).toLocaleString();
  };

  if (loading) {
    return <LoadingSpinner />;
  }
  return (
    <div>
      <NavBar />
      <div className="container mt-3">
        <div className="d-flex">
          <h1 className="flex-grow-1">{post.title}</h1>
          <div>
            <Link className="btn btn-dark" to={`/cigarRequest/${id}/edit`}>
              edit
            </Link>
          </div>
        </div>
        <small className="text-muted">
          작성 날짜:{printDate(post.createdAt)}
        </small>
        <hr />
        <p>{post.body}</p>
      </div>
    </div>
  );
};

export default RequestShowPage;
