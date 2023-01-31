import React from "react";
import "./App.css";
import Layout from "./components/Layout";
import {
  RecoilRoot
} from "recoil";
import { useEffect } from "react";
import axios from "axios";

function App() {
  useEffect(() => {
    axios.defaults.baseURL = `${process.env.REACT_APP_URL}`;
  }, []);
  //useEffect(() => {
  //   axios.defaults.baseURL = `${process.env.REACT_APP_URL}`;
  //}, []);

  return (
      <RecoilRoot>
        <Layout />
      </RecoilRoot>
  );
}

export default App;
