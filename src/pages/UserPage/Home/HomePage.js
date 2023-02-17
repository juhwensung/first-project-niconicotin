import Caulacel from "../../../components/Caulacel";
import NavBar from "../../../components/NavBar";
import { Tab, Tabs, TabList, TabPanel } from "react-tabs";
import "react-tabs/style/react-tabs.css";
import "./homePage.css";
import CigarStar from "../../../components/cigarList/CigarStar";
import CigarViews from "../../../components/cigarList/CigarViews";
import CigarLike from "../../../components/cigarList/CigarLike";

const HomePage = () => {
  return (
    <div className="homepage">
      <NavBar />
      <div>
        <div className="homecaulacel">
          <p>담배 랭킹</p>
          <Caulacel />
        </div>

        <hr className="hrline" />
        <Tabs className="menuTabs">
          <TabList className="tabList">
            <Tab>별점순</Tab>
            <Tab>조회순</Tab>
            <Tab>좋아요순</Tab>
          </TabList>

          <TabPanel>
            <CigarStar />
          </TabPanel>
          <TabPanel>
            <CigarViews />
          </TabPanel>
          <TabPanel>
            <CigarLike />
          </TabPanel>
        </Tabs>
      </div>
    </div>
  );
};

export default HomePage;
