import axios from "axios";
import { useEffect, useState } from "react";
import LoadingSpinner from "../../../components/LoadingSpinner";
import { useParams } from "react-router-dom";
import NavBar from "../../../components/NavBar";
import Comments from "../../../components/Review/Comments";
const StarVeiw = () => {
  const { id } = useParams();
  const [post, setPost] = useState(null);
  const [loading, setLoading] = useState(true);
  const [CommentLists, setCommentLists] = useState([]);

  const getPost = (id) => {
    axios.get(`http://localhost:3001/cigarposts/${id}`).then((res) => {
      setPost(res.data);
      console.log(res.data);
      setLoading(false);
    });
  };

  useEffect(() => {
    getPost(id);
  }, [id]);

  if (loading) {
    return <LoadingSpinner />;
  }
  const updateComment = (newComment) => {
    setCommentLists(CommentLists.concat(newComment));
  };
  return (
    <div>
      <NavBar />
      <div className="container mt-3">
        <div className="d-flex">
          <img src={post.thumbnail} />
          <p>{post.cigartitle}</p>
          <p>{post.hashtag}</p>
        </div>
      </div>
      <Comments />
    </div>
  );
};
export default StarVeiw;
