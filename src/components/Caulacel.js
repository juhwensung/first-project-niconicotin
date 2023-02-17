import React from "react";
import axios from "axios";
import { useEffect, useState } from "react";
import Posts from "./CigarCard/Posts";
import styled from "styled-components";
import Slider from "react-slick";

const StyledSlider = styled(Slider)`
  .slick-prev {
    left: -4rem;
    z-index: 1000;
  }

  .slick-next {
    right: -3rem;
    z-index: 1000;
  }

  .slick-dots li {
    width: 6px;
    height: 6px;
    margin: 0 10.5px;
  }

  .slick-dots li button {
    width: 6px;
    height: 6px;
  }

  .slick-dots li button:before {
    width: 6px;
    height: 6px;
    color: white;
  }

  .slick-dots li.slick-active button:before {
    color: white;
  }

  li {
    margin: 0px;
    padding: 0px;
  }
`;

function Caulacel() {
  const [cigarposts, setCigarPosts] = useState([]);
  const limit = 10;

  useEffect(() => {
    axios
      .get("http://localhost:3001/cigarposts", {
        params: {
          _limit: limit,
        },
      })
      .then((res) => {
        setCigarPosts(res.data);
      });
  }, []);

  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 5,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
    arrows: true,
  };

  return (
    <div className="cigarCarouselWrapper">
      <div
        className="carouselTitle"
        style={{ color: "lightgray", fontSize: "30px" }}
      ></div>
      <StyledSlider {...settings}>
        {cigarposts.map((cigarpost) => {
          return (
            <Posts
              key={cigarpost.cigarid}
              cigartitle={cigarpost.cigartitle}
              category={cigarpost.category}
              thumbnail={cigarpost.thumbnail}
            />
          );
        })}
      </StyledSlider>
    </div>
  );
}

export default Caulacel;
