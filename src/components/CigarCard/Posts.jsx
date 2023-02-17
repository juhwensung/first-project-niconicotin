import "./posts.css";
import { AiFillStar } from "react-icons/ai";
import { BsEye } from "react-icons/bs";
import { LikeOutlined, DislikeOutlined } from "@ant-design/icons";
export default function Posts({
  thumbnail,
  hashtag,
  cigartitle,
  star,
  view,
  like,
  dislike,
}) {
  return (
    <>
      <div>
        <div className="cigarcard">
          <img className="cigarcard-header" src={thumbnail} />

          <p className="hashtag">{hashtag}</p>

          <h3 className="title">{cigartitle}</h3>
          <AiFillStar />
          <span>{star}</span>
          <LikeOutlined />
          <span>{like}</span>
          <DislikeOutlined />
          <span>{dislike}</span>
          <BsEye />
          <span>{view}</span>
        </div>
      </div>
    </>
  );
}
