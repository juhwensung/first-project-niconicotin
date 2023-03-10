import React, { useEffect, useRef, useState } from "react";
import Card from "../ACard";
import axios from "axios";
import { useNavigate, useLocation, Link } from "react-router-dom";
import { useInView } from "react-intersection-observer";
import "../Abody.css";
import { bool } from "prop-types";

function FreeList({ isAdmin }) {
  const [page, setPage] = useState(1);
  const [posts, setPosts] = useState([]);
  const [element, setElement] = useState(null);
  const [Loading, setLoading] = useState(true);
  const [searchText, setSearchText] = useState("");
  const location = useLocation();
  const pageEnd = useRef(null);
  const [ref, inView] = useInView();
  const navigate = useNavigate();

  const deletes = (e, id) => {
    e.stopPropagation();
    axios.delete(`http://localhost:3001/freeposts/${id}`).then(() => {
      setPosts((prevPosts) => {
        return prevPosts.filter((posts) => {
          return posts.id !== id;
        });
      });
      navigate("/FreeList");
    });
  };

  const fetchData = async (page) => {
    setLoading(true);
    const result = await axios.get(
      `http://localhost:3001/freeposts?_page=${page}&_limit=10`
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
          className="AnicoSearch"
          type="text"
          placeholder="??????"
          value={searchText}
          onChange={(e) => setSearchText(e.target.value)}
          onKeyDown={onSearch}
        />
        <hr width="100%" />
        <div className="Annbox">
          <div className="Atagtit">
            <p className="AnicoBodySeq">??? ??????</p>
            <p className="AnicoBodySub">??? ???</p>
            <p className="AnicoBodyUser">?????????</p>
          </div>

          {posts.length === 0 ? (
            <div>No blog posts found</div>
          ) : (
            posts &&
            posts.map((freeposts) => {
              return (
                <div ref={setElement}>
                  <Card
                    id={freeposts.id}
                    title={freeposts.title}
                    name={freeposts.name}
                    onClick={() => navigate(`/FreeList/${freeposts.id}`)}
                  >
                    {isAdmin ? (
                      <div>
                        <button className="Adbtn" onClick={deletes}>
                          ??????
                        </button>

                        <button
                          className="Arbtn"
                          onClick={(e) => {
                            e.stopPropagation();
                          }}
                        >
                          ??????
                        </button>
                      </div>
                    ) : null}
                  </Card>
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
FreeList.protoType = {
  isAdmin: bool,
};

FreeList.defaultProp = {
  isAdmin: false,
};
export default FreeList;
