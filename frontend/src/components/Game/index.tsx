import React, { useEffect } from "react";

import Background from "./Background";
import Chat from "./Chat";
import Content from "./Content";
import Header from "./Header";

import { gameSocket } from "@/sockets";
import { View } from "react-native";

const { connect, subscribe, send, disconnect } = gameSocket;

export default function Game() {
  // const onConnect = () => {
  //   console.log("소켓 연결 성공");
  //   subscribe(`/sub/`, (message) => {
  //     const data = JSON.parse(message.body);
  //     console.log("소켓메세지: ", data);
  //   });
  // };

  // useEffect(() => {
  //   connect(onConnect);
  //   return () => {
  //     disconnect();
  //   };
  // }, []);

  return (
    <Background>
      <Header />
      <Content />
      <Chat />
    </Background>
  );
}
