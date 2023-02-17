import React, { useEffect, useState } from "react";
import { SectionsContainer, Section } from "react-fullpage";
import Caulacel from "../../../components/Caulacel";
import { Link } from "react-router-dom";
import "./indexPage.css";

//풀페이지 적용
export default function IndexPage() {
  const [, setPosition] = useState(0);
  function onScroll() {
    setPosition(window.scrollY);
  }
  useEffect(() => {
    window.addEventListener("scroll", onScroll);
    return () => {
      window.removeEventListener("scroll", onScroll);
    };
  }, []);
  let options = {
    anchors: ["sectionOne", "sectionTwo", "sectionThree"],
    delay: 500,
  };
  return (
    <SectionsContainer {...options}>
      <Section className="Index1Back">
        <div className="Index1">
          <h1>흡연자의, 흡연자를 의한, 흡연자를 위한 담배리뷰사이트</h1>
          <p>이 세상 모든 담배를 리뷰해주는 사이트</p>
        </div>
      </Section>
      <Section className="Index2Back">
        <div className="Index2">실시간으로 인기 담배를 확인 하세요</div>
        <div className="caulacel">
          <Caulacel />
        </div>
      </Section>
      <Section className="Index3Back">
        <div className="Index3">
          흡연구역을 확인하고 흡연구역에서만 <br /> 흡연하는 흡연자가 되세요
          <br />
          <Link className="loginLink" to="/login">
            지금 바로 시작하기
          </Link>
        </div>
      </Section>
    </SectionsContainer>
  );
}
