import React, { useEffect, useRef, useState } from "react";
import Card from "./UserCard";
import axios from "axios";
import { useNavigate, useLocation } from "react-router-dom";
import { useInView } from "react-intersection-observer";
import "./Ubody.css";

function UserList() {
  const [page, setPage] = useState(1);
  const [posts, setPosts] = useState([]);
  const [element, setElement] = useState(null);
  const [Loading, setLoading] = useState(true);
  const [searchText, setSearchText] = useState("");
  const location = useLocation();
  const pageEnd = useRef(null);
  const [ref, inView] = useInView();
  const navigate = useNavigate();

  const fetchData = async (page) => {
    setLoading(true);
    const result = await axios.get(
      `http://localhost:3001/member_table?_page=${page}&_limit=10`
    );
    setLoading(false);
    setPosts(
      (prev) => {
        if (!prev) return result.data;
        else return [...prev, ...result.data];
      },
      [searchText]
    );
  };

  const observer = useRef(
    new IntersectionObserver(
      (entries) => {
        const first = entries[0];
        if (first.isIntersecting) {
          setPage((prev) => prev + 1);
        }
      },
      { threshold: 1 }
    )
  );

  useEffect(() => {
    fetchData(page);
  }, [page]);

  useEffect(() => {
    const currentElement = element;
    const currentObserver = observer.current;

    if (currentElement) {
      currentObserver.observe(currentElement);
    }

    return () => {
      if (currentElement) {
        currentObserver.unobserve(currentElement);
      }
    };
  }, [element]);

  useEffect(() => {
    if (inView && !Loading) {
      setPage((prev) => prev + 1);
    }
  }, [inView, Loading]);

  const onSearch = (e) => {
    if (e.key === "Enter") {
      navigate(`${location.pathname}?page=1`);
      fetchData(1);
    }
  };

  return (
    <div>
      <div>
        <input
          className="UnicoSearch"
          type="text"
          placeholder="검색"
          value={searchText}
          onChange={(e) => setSearchText(e.target.value)}
          onKeyDown={onSearch}
        />
        <hr width="100%" />
        <div className="Unnbox">
          <div className="Utagtit">
            <p className="UnicoUesrSeq">유저 번호</p>
            <p className="UnicoUesrName">이름</p>
            <p className="UnicoUesrNick">닉네임</p>
            <p className="UnicoUesrID">아이디</p>
            <p className="UnicoUesrPhone">핸드폰 번호</p>
            <p className="UnicoUesrBirth">생일</p>
            <p className="UnicoUesrSYear">담배 연차</p>
            <p className="UnicoUesrSGYear">가입 날짜</p>
          </div>

          {posts.length === 0 ? (
            <div>No blog posts found</div>
          ) : (
            posts &&
            posts.map((member_table) => {
              return (
                <div ref={setElement}>
                  <Card
                    member_seq={member_table.member_seq}
                    name={member_table.name}
                    nickname={member_table.nickname}
                    email={member_table.email}
                    phone={member_table.phone}
                    birthday={member_table.birthday}
                    smoke_years={member_table.smoke_years}
                    sign_date={member_table.sign_date}
                    onClick={() =>
                      navigate(`/UserList/${member_table.member_seq}`)
                    }
                  ></Card>
                </div>
              );
            })
          )}
        </div>
        <div ref={pageEnd} />
      </div>
    </div>
  );
}

export default UserList;
