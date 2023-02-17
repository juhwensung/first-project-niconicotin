import { useEffect, useState, useRef } from "react";
import Posts from "../CigarCard/Posts";
import "./cigarStar.css";
import LoadingSpinner from "../LoadingSpinner";
import { MultiSelect } from "@mantine/core";
import { FaHashtag } from "react-icons/fa";
import axios from "axios";
import { Link } from "react-router-dom";

export default function CigarList() {
  const [cigarposts, setCigarPosts] = useState([]);
  const [tag, setTag] = useState([]);
  const [userInput, setUserInput] = useState("");
  const [pageNumber, setPageNumber] = useState(0);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState(false);
  const [noData, setNoData] = useState(false);

  const fetchList = async (pageNumber) => {
    try {
      const res = await axios.get(
        `http://localhost:3001/CigarPosts?_page=${pageNumber}&_limit=18`
      );
      setCigarPosts((respond) => [...respond, ...res.data]);
      setLoading(true);
    } catch (err) {
      console.log(err);
    } finally {
      setMessage(false);
      setTimeout(() => {
        setNoData(true);
      }, 2000);
    }
  };

  useEffect(() => {
    fetchList(pageNumber);
  }, [pageNumber]);

  /* 옵져버 정의 */
  const pageEnd = useRef();
  let num = 0;

  const observer = new IntersectionObserver(
    (entries) => {
      if (entries[0].isIntersecting) {
        const first = entries[0];
        if (first.isIntersecting) {
          setPageNumber((prev) => prev + 1);
        }
      }
    },
    { threshold: 1 }
  );

  /* 옵져버 실행*/
  useEffect(() => {
    const currentElement = noData;
    const currentObserver = observer.current;

    if (currentElement) {
      currentObserver.observe(currentElement);
    }
    if (loading) {
      setTimeout(() => {
        observer.observe(pageEnd.current);
      }, 1500);

      setMessage(true);
    }
    return () => {
      if (currentElement) {
        currentObserver.unobserve(currentElement);
      }
      setMessage(true);
    };
  }, [loading]);

  useEffect(() => {
    axios.get(`/api/hash.do`).then((response) => {
      const dataTag = response.data;
      console.log(response.data);
      const array = dataTag.map((hashtag) => {
        return { value: hashtag.keyNum, label: `${hashtag.cigar_hash_tag}` };
      });
      setTag(array);
    });
  }, [userInput]);

  return (
    <>
      <div>
        <MultiSelect
          data={tag}
          placeholder="Dropdown rendered as div element"
          dropdownComponent="div"
          icon={<FaHashtag />}
          value={userInput}
          onChange={(e) => {
            setUserInput(e.target.value);
          }}
        />
      </div>
      <div className="cigarCarouselWrapper">
        <div className="cardlist">
          {cigarposts.map((cigarpost) => {
            return (
              <div className="cardpost">
                <Link to={`/VeiwVeiw/${cigarpost.id}`}>
                  <Posts
                    key={cigarpost.id}
                    cigartitle={cigarpost.cigartitle}
                    hashtag={cigarpost.hashtag}
                    thumbnail={cigarpost.thumbnail}
                    star={cigarpost.star}
                    like={cigarpost.like}
                    dislike={cigarpost.dislike}
                    view={cigarpost.view}
                  />
                </Link>
              </div>
            );
          })}
        </div>
      </div>
      {message ? <LoadingSpinner /> : ""}
      {noData ? <div>No blog posts found ...</div> : ""}
      <div ref={pageEnd}></div>
    </>
  );
}
