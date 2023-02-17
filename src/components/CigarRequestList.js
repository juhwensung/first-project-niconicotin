import React, { useEffect, useRef, useState } from "react";
import Card from "./Card";
import axios from "axios";
import { useNavigate, useLocation } from "react-router-dom";
import { useInView } from "react-intersection-observer";
import "./body.css";

function CigarRequestList() {
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
      `http://localhost:3001/cigarrequest?_page=${page}&_limit=10`
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
          className="nicoSearch"
          type="text"
          placeholder="검색"
          value={searchText}
          onChange={(e) => setSearchText(e.target.value)}
          onKeyDown={onSearch}
        />
        <hr width="100%" />
        <div className="nnbox">
          <div className="tagtit">
            <p className="nicoBodySeq">글 번호</p>
            <p className="nicoBodySub">제 목</p>
            <p className="nicoBodyHit">조회수</p>
            <p className="nicoBodyUser">작성자</p>
            <p className="nicoBodyDate">작성일</p>
          </div>
          {posts.length === 0 ? (
            <div>No blog posts found</div>
          ) : (
            posts &&
            posts.map((cigarpost) => {
              return (
                <div ref={setElement}>
                  <Card
                    key={cigarpost.id}
                    title={cigarpost.title}
                    name={cigarpost.name}
                    onClick={() => navigate(`/cigarrequest/${cigarpost.id}`)}
                  ></Card>
                </div>
              );
            })
          )}
          <div ref={pageEnd} />
        </div>
      </div>
    </div>
  );
}

export default CigarRequestList;
