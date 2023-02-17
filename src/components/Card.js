import PropTypes from "prop-types";
import "./body.css";

const Card = ({ id, title, date, name, hit, hashtag, onClick, children }) => {
  return (
    <>
      <div className="nicobox" onClick={onClick}>
        <div className="nicoboxTitle py-2 d-flex align-items-center">
          <div className="cardseq">{id}</div>
          <div className="cardtitle">{title}</div>
          <div className="carddate">{date}</div>
          <div className="carduser">{name}</div>
          <div className="carduser">{hashtag}</div>
          <div className="cardhit">{hit}</div>
          {children && <div>{children}</div>}
        </div>
      </div>
    </>
  );
};

Card.protoType = {
  id: PropTypes.any.isRequired,
  title: PropTypes.string.isRequired,
  date: PropTypes.any.isRequired,
  name: PropTypes.string.isRequired,
  hit: PropTypes.any.isRequired,
  hashtag: PropTypes.any.isRequired,
  children: PropTypes.element,
  onclick: PropTypes.func,
};

Card.defaultProps = {
  children: null,
  onclick: () => {},
};

export default Card;
