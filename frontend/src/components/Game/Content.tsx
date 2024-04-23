import React, { useContext, useEffect, useRef, useState } from "react";
import { View, Text, StyleSheet, ScrollView } from "react-native";

import Font from "@/config/Font";
import { ThemeContext } from "@/config/Theme";

export default function Content() {
  const { theme } = useContext(ThemeContext);
  const scrollViewRef = useRef<ScrollView>(null);
  const [messages, setMessages] = useState([
    "대원 : 이거 타이타닉 아님?",
    "대원 : 이거 타이타닉 아님?",
    "대원 : 이거 타이타닉 아님?",
    "대원 : 이거 타이타닉 아님?",
    "대원 : 이거 타이타닉 아님?",
    "대원 : 이거 타이타닉 아님?",
    "대원 : 이거 타이타닉 아님?",
  ]);

  useEffect(() => {
    scrollViewRef.current?.scrollToEnd({ animated: true });
  }, [messages]);

  return (
    <View style={styles.container}>
      <View style={styles.hostWaiting}>
        <Text>대기중</Text>
      </View>
      <ScrollView
        showsVerticalScrollIndicator={false}
        ref={scrollViewRef}
        style={styles.chatBox}
        contentContainerStyle={styles.chatContentContainer}>
        {messages.map((message, index) => (
          <Text
            key={index}
            style={{
              ...Font.messages,
              color: theme.text,
              opacity:
                index === messages.length - 1 || index === messages.length - 2
                  ? 1
                  : index === messages.length - 3 || index === messages.length - 4
                    ? 0.5
                    : 0.3,
            }}>
            {message}
          </Text>
        ))}
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    // borderWidth: 1,
  },
  chatBox: {
    position: "absolute",
    bottom: 0,
    maxHeight: 100,
    width: "100%",
    // borderWidth: 1,
  },
  hostWaiting: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  chatContentContainer: {
    justifyContent: "flex-end",
  },
});
