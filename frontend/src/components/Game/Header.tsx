import React, { useContext } from "react";
import { View, Text, StyleSheet } from "react-native";

import { ThemeContext } from "@/config/Theme";

export default function Header() {
  const { theme } = useContext(ThemeContext);

  return (
    <View style={styles.container}>
      <Text style={{ ...styles.text, color: theme.text }}>18번 : 방 제목입니다</Text>
      <Text style={{ ...styles.text, color: theme.text }}>인원 : 6/6</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    marginVertical: 10,
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
  },
  text: {
    fontSize: 16,
  },
});
