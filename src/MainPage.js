import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import IndexPage from "./pages/UserPage/index(main)/IndexPage";
import Login from "./pages/UserPage/Login/Login";
import Signup from "./pages/UserPage/Login/Signup";
import HomePage from "./pages/UserPage/Home/HomePage";
import StarVeiw from "./pages/UserPage/VeiwPage/StarVeiw";
import VeiwVeiw from "./pages/UserPage/VeiwPage/VeiwVeiw";
import LikeVeiw from "./pages/UserPage/VeiwPage/LikeVeiw";
import FreeCreatePage from "./pages/UserPage/Freeposts/FreeCreatePage";
import AnnounCreatePage from "./pages/UserPage/Announcement/AnnounCreatePage";
import RequestCreatePage from "./pages/UserPage/Request/RequestCreatePage";
import FreeEditPage from "./pages/UserPage/Freeposts/FreeEditPage";
import AnnounEditPage from "./pages/UserPage/Announcement/AnnounEditPage";
import RequestEditPage from "./pages/UserPage/Request/RequestEditPage";
import FreePostPage from "./pages/UserPage/Freeposts/FreePostPage";
import Announcement from "./pages/UserPage/Announcement/Announcement";
import CigarRequest from "./pages/UserPage/Request/CigarRequest";
import AdminPage from "./pages/AdminPage/AdminPage";
import FreeShowPage from "./pages/UserPage/Freeposts/FreeShowPage";
import AnnounShowPage from "./pages/UserPage/Announcement/AnnounShowPage";
import RequestShowPage from "./pages/UserPage/Request/RequestShowPage";

import CigarPostPage from "./pages/UserPage/Cigar/CigarPostPage";
import UserPostPage from "./pages/UserPage/UserPostPage";
import Map from "./pages/MapPage/Map";

function MainPage() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<IndexPage />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/home" element={<HomePage />} />,
        <Route path="/StarVeiw/:id" element={<StarVeiw />} />
        <Route path="/VeiwVeiw/:id" element={<VeiwVeiw />} />
        <Route path="/LikeVeiw/:id" element={<LikeVeiw />} />
        <Route path="/FreeList" element={<FreePostPage />} />,
        <Route path="/Announcement" element={<Announcement />} />,
        <Route path="/CigarRequest" element={<CigarRequest />} />,
        <Route path="/Admin" element={<AdminPage />} />,
        <Route path="/FreeList/create" element={<FreeCreatePage />} />,
        <Route path="/CigarRequest/create" element={<RequestCreatePage />} />,
        <Route path="/Announcement/create" element={<AnnounCreatePage />} />,
        <Route path="/FreeList/:id/edit" element={<FreeEditPage />} />,
        <Route path="/CigarRequest/:id/edit" element={<RequestEditPage />} />,
        <Route path="/Announcement/:id/edit" element={<AnnounEditPage />} />,
        <Route path="/FreeList/:id" element={<FreeShowPage />} />,
        <Route path="/cigarRequest/:id" element={<RequestShowPage />} />,
        <Route path="/Announcement/:id" element={<AnnounShowPage />} />,
        <Route path="/CigarList" element={<CigarPostPage />} />,
        <Route path="/UserList" element={<UserPostPage />} />,
        <Route path="/map" element={<Map />} />
      </Routes>
    </Router>
  );
}

export default MainPage;
