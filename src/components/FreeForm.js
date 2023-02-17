import React, { useEffect } from "react";
import { useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import propTypes from "prop-types";
// import ImgUplode from "./ImgUplode";

const FreeForm = ({ editing }) => {
  const navigate = useNavigate();
  const { id } = useParams();

  const [title, setTitle] = useState("");
  const [originalTitle, setOriginalTitle] = useState("");
  const [body, setBody] = useState("");
  const [originalbody, setOriginalBody] = useState("");
  const [thumail, setThumail] = useState();
  const [originalThumail, setOriginalThumail] = useState();

  const [titleError, setTitleError] = useState(false);
  const [bodyError, setBodyError] = useState(false);

  useEffect(() => {
    if (editing) {
      axios.get(`http://localhost:3001/posts/${id}`).then((res) => {
        setTitle(res.data.title);
        setOriginalTitle(res.data.title);
        setBody(res.data.body);
        setOriginalBody(res.data.body);
        setThumail(res.data.thumail);
        setOriginalThumail(res.data.thumail);
      });
    }
  }, [id, editing]);

  const isEdited = () => {
    return (
      title !== originalTitle ||
      body !== originalbody ||
      thumail !== originalThumail
    );
  };

  const goBack = () => {
    if (editing) {
      navigate(`/FreeList/${id}`);
    } else {
      navigate("/FreeList");
    }
  };

  const validateForm = () => {
    let validated = true;
    if (title === "") {
      setTitleError(true);
      validated = false;
    }
    if (body === "") {
      setBodyError(true);
      validated = false;
    }
    return validated;
  };

  const onSubmit = () => {
    setTitleError(false);
    setBodyError(false);
    if (validateForm()) {
      if (editing) {
        axios
          .patch(`http://localhost:3001/posts/${id}`, {
            title,
            body,
            thumail,
          })
          .then((res) => {
            navigate(`/blogs/${id}`);
          });
      } else {
        axios
          .post("http://localhost:3001/posts", {
            title,
            body,
            thumail,

            createdAt: Date.now(),
          })
          .then(() => {
            navigate("/FreeList");
          });
      }
    }
  };

  return (
    <>
      <div className="container mt-3">
        <div className="container">
          <h1>{editing ? "Edit" : "자유게시판"} </h1>
          <div className="mb-3">
            <label className="form-label">제목</label>
            <input
              className={`form-control ${titleError ? "border-danger" : ""}`}
              value={title}
              onChange={(event) => {
                setTitle(event.target.value);
              }}
            />
            {titleError && (
              <div className="text-danger">텍스트를 입력해 주세요</div>
            )}
          </div>
          <div className="mb-3">
            <label className="form-label">body</label>
            <textarea
              className={`form-control ${bodyError ? "border-danger" : ""}`}
              rows={20}
              value={body}
              onChange={(event) => {
                setBody(event.target.value);
              }}
            />
            {bodyError && (
              <div className="text-danger ">텍스트를 입력해 주세요</div>
            )}
          </div>

          <button
            className="btn btn-primary"
            onClick={onSubmit}
            disabled={editing && !isEdited()}
          >
            {editing ? "Edit" : "작성"}
          </button>
          <button className="btn btn-primary ms-2" onClick={goBack}>
            취소
          </button>
        </div>
      </div>
    </>
  );
};

FreeForm.propType = {
  editing: propTypes.bool,
};

FreeForm.defaultProps = {
  editing: false,
};

export default FreeForm;
