/*global kakao*/
import React, { Component } from "react";
import jsonData from "./map_info.json";
import $ from "jquery";
import "./map.css";
import Navbar from "../../components/NavBar";

class Map extends Component {
  componentDidMount() {
    let mapContainer = document.getElementById("map"),
      mapOption = {
        center: new kakao.maps.LatLng(37.5383742, 127.0822077),
        level: 6,
      };
    let map = new kakao.maps.Map(mapContainer, mapOption);

    var clusterer = new kakao.maps.MarkerClusterer({
      map: map,
      averageCenter: true,
      minLevel: 5,
    });

    var markers = $(jsonData.positions).map(function (i, position) {
      // var imageSrc = require("./img/hop0" + (i + 1) + ".png");
      // if (position.finish === "완치") {
      //   imageSrc = require("./img/clearp0" + (i + 1) + ".png");
      // }
      // var imageSize = new kakao.maps.Size(60, 70);
      // var imageOption = { offset: new kakao.maps.Point(27, 69) };

      // var markerImage = new kakao.maps.MarkerImage(
      //   imageSrc,
      //   imageSize,
      //   imageOption
      // );
      var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(position.lat, position.lng),
        // image: markerImage,
      });

      var content =
        '<div class="overlaybox">' +
        '    <div class="boxtitle">' +
        "흡연구역</div>" +
        '    <div class="boxtitle2" style="margin-left: 220px;"><font color="white"">❌</font></div>' +
        '    <div class="first first_' +
        '">' +
        '        <div class="triangle text">' +
        "</div>" +
        '        <div class="movietitle text">' +
        "</div>" +
        "    </div>" +
        '    <ul style = "overflow:scroll">' +
        '        <li class="up">' +
        '            <span class="title" style="font-weight:bold;color:white">' +
        '            <span class="number">흡연장 이름:</span>' +
        "</span>" +
        "</span>" +
        "        </li>" +
        '        <li class="up">' +
        '            <span class="title" style="font-weight:bold;color:white; height:55px;">' +
        '            <span class="number">흡연장 주소: </span>' +
        '            <span class="number" style="white-space: normal ; ">주소값 자리</span>' +
        "</span>" +
        "</span>" +
        "        </li>" +
        "    </ul>" +
        "</div>";

      var lat = Number(position.lat);
      var lng = Number(position.lng);
      var lat_string = lat.toString();
      var lng_string = lng.toString();

      var customOverlay = new kakao.maps.CustomOverlay({
        position: new kakao.maps.LatLng(lat_string, lng_string),
        content: content,
        xAnchor: 0.25,
        yAnchor: 0.95,
      });

      var clickHandler1 = function (event) {
        customOverlay.setMap(map);

        $(".boxtitle2").click(function () {
          customOverlay.setMap(null);
        });

        $(".first_" + position.num).css({
          background: "url(" + position.hospi_img + ")",
          "background-size": "247px 247px",
        });
      };

      kakao.maps.event.addListener(marker, "click", clickHandler1);
      return marker;
    });
    clusterer.addMarkers(markers);
  }

  render() {
    return (
      <div>
        <Navbar />
        <div id="map" style={{ width: "100%", height: "700px" }} />
      </div>
    );
  }
}

export default Map;
