import React from "react";

import Jars from "../components/Jars";
import Income from "../components/Income";
import Forecasts from "../components/Forecasts";
import Finance from "../components/Finance";
import HowToSearch from "../components/HowToSearch";

const routes = {
  "/jmoney/jars": () => <Jars />,
  "/jmoney/income": () => <Income />,
  "/jmoney/forecasts": () => <Forecasts />,
  "/jmoney/finance": () => <Finance />,
  "/jmoney/howtosearch": () => <HowToSearch />,
};
export default routes;
