const Comment = ({ body, createdAt, userName, graid }) => {
  return (
    <li className="reviewlistitem">
      {/* <hr /> */}
      <div className="reviewHead">
        <h3 className="reviewMenu">{body}</h3>
        <span>{createdAt}</span>
      </div>
      <div className="starBox">
        <div>{graid}</div>
        <span>|</span>
        <span>{userName}</span>
      </div>
    </li>
  );
};

export default Comment;
