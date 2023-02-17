import { useState, useEffect } from "react";
import axios from "axios";
import Comment from "./Comment";

function Comments() {
  const [post, setPost] = useState([]);
  const [body, setBody] = useState("");
  const [userName, setUserName] = useState("");
  const [graid, setGraid] = useState(0);
const username = localStorage.getItem("username");

  const onSubmit = () => {
    axios.post("http://localhost:3001/comment", {
      userName: username,
      graid,
      body,
      createdAt: Date.now(),
    });
  };
  useEffect(() => {
    axios.get("http://localhost:3001/comment", {}).then((res) => {
      setPost(res.data);
    });
  }, []);

  const printDate = (timestamp) => {
    return new Date(timestamp).toLocaleString();
  };

  return (
    <>
      <form style={{ display: "flex" }}>
        <input
          type="text"
          onChange={(event) => {
            setBody(event.target.value);
          }}
        ></input>
        <hr />
        <input
          type="number"
          max="5"
          min="1"
          onChange={(event) => {
            setGraid(event.target.value);
          }}
        />
        <br />
        <button onClick={onSubmit}>Submit</button>
      </form>
      {post.map(({ bodyId, body, createdAt, userName, graid }) => (
        <div>
          <Comment
            key={bodyId}
            body={body}
            createdAt={printDate(createdAt)}
            graid={graid}
            userName={userName}
          />
          <hr />
        </div>
      ))}
    </>
  );
}

export default Comments;
