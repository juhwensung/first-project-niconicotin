import { useParams } from "react-router-dom";
import axios from "axios";
import { useEffect, useState } from "react";
import LoadingSpinner from "../../../components/LoadingSpinner";
import { Link, useNavigate } from "react-router-dom";
import NavBar from "../../../components/NavBar";
import "../Showpage.css";
import "../sub.css";
import { bool } from "prop-types";

const FreeShowPage = ({ isAdmin }) => {
  const { id } = useParams();
  const [post, setPost] = useState(null);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  const deletes = () => {
    axios.delete(`http://localhost:3001/freeposts/${id}`).then(() => {
      navigate("/FreeList");
    });
  };

  const getPost = (id) => {
    axios.get(`http://localhost:3001/freeposts/${id}`).then((res) => {
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
        <div className="shows">자유게시판</div>
        <div className="showu">작성자: {post.name}</div>
        <div className="bshowt">
          <h1 className="showt">제목 : {post.title}</h1>
        </div>
        <div className="nicoshowbody">
          <p className="nicoshowtext">{post.body}</p>
        </div>
        {isAdmin ? (
          <div>
            <button className="btn nico-btn" onClick={deletes}>
              삭제
            </button>
            <Link className="btn nico-btn" to={`/FreeList/${id}/edit`}>
              수정
            </Link>
          </div>
        ) : null}
        <small className="nicodate">작성일 : {printDate(post.createdAt)}</small>
      </div>
    </div>
  );
};
FreeShowPage.protoType = {
  isAdmin: bool,
};

FreeShowPage.defaultProp = {
  isAdmin: false,
};
export default FreeShowPage;
