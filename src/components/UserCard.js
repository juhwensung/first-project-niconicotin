import PropTypes from "prop-types";
import "./body.css";

const UserCard = ({
  member_seq,
  name,
  nickname,
  email,
  phone,
  birthday,
  smoke_years,
  sign_date,
  onClick,
  children,
}) => {
  return (
    <>
      <div className="Unicobox" onClick={onClick}>
        <div className="UnicoboxTitle py-2 d-flex align-items-center">
          <div className="Ucardseq">{member_seq}</div>
          <div className="Ucardname">{name}</div>
          <div className="Ucardnick">{nickname}</div>
          <div className="Ucardid">{email}</div>
          <div className="Ucardphone">{phone}</div>
          <div className="Ucardbirth">{birthday}</div>
          <div className="Ucardsyear">{smoke_years}</div>
          <div className="Ucardsgyear">{sign_date}</div>
          {children && <div>{children}</div>}
        </div>
      </div>
    </>
  );
};

UserCard.protoType = {
  member_seq: PropTypes.any.isRequired,
  name: PropTypes.string.isRequired,
  nickname: PropTypes.any.isRequired,
  email: PropTypes.string.isRequired,
  phone: PropTypes.any.isRequired,
  birthday: PropTypes.any.isRequired,
  smoke_years: PropTypes.any.isRequired,
  sign_date: PropTypes.any.isRequired,
  children: PropTypes.element,
  onclick: PropTypes.func,
};

UserCard.defaultProps = {
  children: null,
  onclick: () => {},
};

export default UserCard;
