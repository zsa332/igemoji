import React, { useContext, useState } from "react";
import { View, StyleSheet, TextInput, TouchableOpacity, Image } from "react-native";

import { ThemeContext } from "@/config/Theme";

export default function Chat() {
  const { theme } = useContext(ThemeContext);
  const [message, setMessage] = useState("");

  const sendMessage = () => {
    // 메시지 전송 로직
    console.log("Send message:", message);
    setMessage("");
  };

  return (
    <View style={styles.container}>
      <TextInput
        style={{ ...styles.input, borderColor: theme.grey, color: theme.text }}
        placeholder="메시지를 입력하세요"
        value={message}
        onChangeText={setMessage}
      />
      <TouchableOpacity
        onPress={sendMessage}
        style={{
          ...styles.sendButton,
          borderColor: theme.grey,
          backgroundColor: theme.kungyaYello,
        }}>
        <Image
          source={require("~/sendButton.png")}
          style={styles.sendButtonImage}
          resizeMode="center"
        />
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    marginVertical: 10,
    bottom: 0,
  },
  input: {
    flex: 1,
    borderWidth: 1,
    borderRadius: 10,
    height: 40,
    paddingHorizontal: 10,
    marginRight: 10,
  },
  sendButton: {
    borderWidth: 1,
    borderRadius: 10,
    height: 40,
    width: 40,
    alignItems: "center",
    justifyContent: "center",
  },
  sendButtonImage: {
    width: 25,
    height: 25,
  },
});
