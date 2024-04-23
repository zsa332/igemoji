import React, { useContext } from "react";
import { View, StyleSheet, Dimensions, Text, Image } from "react-native";
import AwesomeButton from "react-native-really-awesome-button";

import Button from "@/components/Button";
import Font from "@/config/Font";
import { ThemeContext } from "@/config/Theme";

const { width: SCREENWIDTH, height: SCREENHEIGHT } = Dimensions.get("window");

interface MainModalProps {
  title: keyof typeof modalTitle;
  children: React.ReactNode;
  onPress: () => void;
}

const modalTitle = {
  hostWaiting: "방장의 게임 시작을 기다리고 있습니다.",
  playerWaiting: "방 설정",
  signin: "SNS 로그인",
  signup: "닉네임 설정",
} as const;

const modalHeight = {
  hostWaiting: 400,
  playerWaiting: 150,
  signin: 200,
  signup: 200,
} as const;

export default function ModalBox({ title, children, onPress }: MainModalProps) {
  const { theme } = useContext(ThemeContext);
  return (
    <View
      style={{
        height: modalHeight[title],
      }}>
      <View
        style={{
          ...styles.modalContent,
          backgroundColor: theme.kungya,
          height: modalHeight[title],
        }}>
        <Text style={{ ...Font.modalTitle, color: theme.text }}>{modalTitle[title]}</Text>
        {children}
        <ModalButton title={title} onPress={onPress} />
      </View>
      <View
        style={{
          ...styles.modalBackContent,
          backgroundColor: theme.kungyaYelloDark,
          height: modalHeight[title],
        }}
      />
    </View>
  );
}

function ModalButton({ title, onPress }: { title: keyof typeof modalTitle; onPress: () => void }) {
  if (title === "hostWaiting") {
    return (
      <View style={{ width: SCREENWIDTH * 0.4 }}>
        <Button name="exit" onPress={onPress} />
      </View>
    );
  } else if (title === "playerWaiting") {
    return (
      <View style={{ flexDirection: "row", justifyContent: "space-around" }}>
        <View style={{ width: SCREENWIDTH * 0.3 }}>
          <Button name="start" onPress={onPress} />
        </View>
        <View style={{ width: SCREENWIDTH * 0.3 }}>
          <Button name="exit" onPress={onPress} />
        </View>
      </View>
    );
  } else if (title === "signup") {
    return (
      <View style={{ width: SCREENWIDTH * 0.7 }}>
        <Button name="check" onPress={onPress} />
      </View>
    );
  } else {
    return (
      <AwesomeButton
        width={SCREENWIDTH * (7 / 10)}
        height={45}
        backgroundColor="#FEE500"
        backgroundDarker="#8B8000"
        borderRadius={10}
        onPress={onPress}
        style={styles.awesomeButton}>
        <Image style={styles.kakaoLogin} source={require("~/kakao/kakaoLogo.png")} />
        <Text style={{ opacity: 0.85, fontSize: 16 }}>카카오 로그인</Text>
      </AwesomeButton>
    );
  }
}

const styles = StyleSheet.create({
  modalContent: {
    width: SCREENWIDTH * 0.8,
    borderRadius: 10,
    alignItems: "center",
    justifyContent: "space-between",
    padding: 20,
  },
  modalBackContent: {
    width: SCREENWIDTH * 0.8,
    borderRadius: 10,
    padding: 20,
    position: "absolute",
    top: 4,
    zIndex: -1,
  },
  kakaoLogin: {
    width: 20,
    height: 20,
    marginRight: 8,
  },
  awesomeButton: {
    flexDirection: "row",
    alignItems: "center",
  },
});
