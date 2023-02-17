import React, { useState } from "react";
import Axios from "axios";
import SingleComment from "./SingleComment";
import ReplyComment from "./ReplyComment";

function Comment(props) {
  const [commentValue, setcommentValue] = useState("");

  const handleChange = (event) => {
    setcommentValue(event.currentTarget.value);
  };

  const onsubmit = (event) => {
    event.preventDefault();

    Axios.post("http://localhost:3001/comment").then((response) => {
      if (response.data.success) {
        console.log(response.data.result);
      } else {
        alert("커멘트를 저장하지 못했습니다.");
      }
    });
  };
  return (
    <div>
      <br />
      <p>Replies</p>
      <hr />

      {/* Comment Lists */}
      {props.commentList &&
        props.commentList.map(
          (comment, index) =>
            !comment.responseTo && (
              <React.Fragment>
                <SingleComment
                  refreshFunction={props.refreshFunction}
                  comment={comment}
                  postId={props.postId}
                  key={index}
                />
                <ReplyComment
                  refreshFunction={props.refreshFunction}
                  commentList={props.commentList}
                  parentCommentId={comment._id}
                  postId={props.postId}
                  key={index}
                />
              </React.Fragment>
            )
        )}
      {/* Root Comment Form */}

      <form style={{ display: "flex" }} onSubmit={onsubmit}>
        <textarea
          style={{ width: "100%", borderRadius: "5px" }}
          onChange={handleChange}
          value={commentValue}
          placeholder="코멘트를 작성해 주세요"
        />
        <br />
        <button style={{ width: "20%", height: "52px" }} onClick={onsubmit}>
          Submit
        </button>
      </form>
    </div>
  );
}

export default Comment;
