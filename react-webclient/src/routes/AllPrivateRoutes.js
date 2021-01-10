import React from "react";

import DashboardContents from "../components/DashboardContents";
import Jars from "../components/Jars";
import Income from "../components/Income";
import Forecasts from "../components/Forecasts";
import Finance from "../components/Finance";
import HowToSearch from "../components/HowToSearch";

const routes = {
  "/jmoney/dashboard": () => <DashboardContents />,
  "/jmoney/dashboard/jars": () => <Jars />,
  "/jmoney/dashboard/income": () => <Income />,
  "/jmoney/dashboard/forecasts": () => <Forecasts />,
  "/jmoney/dashboard/finance": () => <Finance />,
  "/jmoney/dashboard/howtosearch": () => <HowToSearch />,
};
export default routes;
