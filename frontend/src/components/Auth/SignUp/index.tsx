import React, { useContext, useState } from "react";
import {
  TextInput,
  View,
  Dimensions,
  Text,
  TouchableWithoutFeedback,
  Keyboard,
  StyleSheet,
  Alert, // Alert import 추가
} from "react-native";

import Background from "../../Background";
import Logo from "../../Logo";
import ModalBox from "../../ModalBox";

import Font from "@/config/Font";
import { ThemeContext } from "@/config/Theme";
import { NavigationProps } from "@/types/types";

const { width: SCREENWIDTH, height: SCREENHEIGHT } = Dimensions.get("window");

export default function SignUp({ navigation }: NavigationProps) {
  const { theme } = useContext(ThemeContext);
  const [inputValue, setInputValue] = useState("");
  const [isDuplicate, setIsDuplicate] = useState(false);
  const [isInvalidLength, setIsInvalidLength] = useState(false);

  const handleTextChange = (text: string) => {
    // 입력값 길이 확인
    setInputValue(text);
    if (text.length === 0) {
      setIsInvalidLength(false);
    } else if (text.length >= 2 && text.length <= 8) {
      setIsInvalidLength(false);
    } else {
      setIsInvalidLength(true);
    }
  };

  const checkDuplicate = () => {
    // 중복 확인 로직 (임시로 구현)
    const isDuplicateValue = false; // 중복이면 true, 중복이 아니면 false
    setIsDuplicate(isDuplicateValue);
  };

  const handleLoginAxios = () => {
    if (!inputValue || isInvalidLength || isDuplicate) {
      // validation이 맞지 않으면 알람창 띄우기
      Alert.alert("경고", "올바른 닉네임을 입력하세요.");
    } else {
      // validation이 맞으면 다음 단계로 이동
      navigation.navigate("Main");
    }
  };

  const handleBlur = () => {
    Keyboard.dismiss(); // 키보드 닫기
  };

  return (
    <TouchableWithoutFeedback onPress={handleBlur}>
      <View style={{ flex: 1 }}>
        <Background>
          <Logo />
          <View style={{ position: "absolute", bottom: SCREENHEIGHT * 0.1 }}>
            <ModalBox title="signup" onPress={handleLoginAxios}>
              <View>
                <TextInput
                  style={{
                    ...styles.textInput,
                    backgroundColor: theme.white,
                    borderColor: isDuplicate || isInvalidLength ? "red" : "transparent",
                  }}
                  placeholder="사용할 닉네임을 입력하세요"
                  onChangeText={handleTextChange}
                  value={inputValue}
                  onBlur={handleBlur}
                />
                <Validation isInvalidLength={isInvalidLength} isDuplicate={isDuplicate} />
              </View>
            </ModalBox>
          </View>
        </Background>
      </View>
    </TouchableWithoutFeedback>
  );
}

// 유효성 검사 컴포넌트
interface ValidationProps {
  isInvalidLength: boolean;
  isDuplicate: boolean;
}

const Validation = ({ isInvalidLength, isDuplicate }: ValidationProps) => {
  return (
    <>
      {isInvalidLength && (
        <Text style={{ color: "red", fontSize: 12 }}>
          닉네임은 2글자 이상, 8글자 이하이어야 합니다.
        </Text>
      )}
      {isDuplicate && (
        <Text style={{ color: "red", fontSize: 12 }}>이미 사용 중인 닉네임입니다.</Text>
      )}
    </>
  );
};

const styles = StyleSheet.create({
  textInput: {
    width: SCREENWIDTH * 0.7,
    borderWidth: 1,
    borderRadius: 5,
    paddingHorizontal: 10,
  },
});
