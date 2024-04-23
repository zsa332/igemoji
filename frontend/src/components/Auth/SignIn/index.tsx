import React, { useContext } from "react";
import { Text, View, Dimensions } from "react-native";

import Background from "../../Background";
import Logo from "../../Logo";
import ModalBox from "../../ModalBox";

import Font from "@/config/Font";
import { ThemeContext } from "@/config/Theme";
import { NavigationProps } from "@/types/types";
const { width: SCREENWIDTH, height: SCREENHEIGHT } = Dimensions.get("window");

export default function SignIn({ navigation }: NavigationProps) {
  const { theme } = useContext(ThemeContext);
  const handleSignInAxios = () => {
    navigation.navigate("SignUp");
  };
  return (
    <Background>
      <Logo />
      <View style={{ position: "absolute", bottom: SCREENHEIGHT * 0.1 }}>
        <ModalBox title="signin" onPress={handleSignInAxios}>
          <Text style={{ ...Font.modalContent, color: theme.text }}>
            카카오 로그인으로 간편하게 시작하기
          </Text>
        </ModalBox>
      </View>
    </Background>
  );
}
