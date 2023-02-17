import PropTypes from "prop-types";
import "./body.css";

const Card = ({ id, title, date, name, hit, hashtag, onClick, children }) => {
  return (
    <>
      <div className="Anicobox" onClick={onClick}>
        <div className="AnicoboxTitle py-2 d-flex align-items-center">
          <div className="Acardseq">{id}</div>
          <div className="Acardtitle">{title}</div>
          <div className="Acardhash">{hashtag}</div>
          <div className="Acarddate">{date}</div>
          <div className="Acarduser">{name}</div>
          <div className="Acardhit">{hit}</div>
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
