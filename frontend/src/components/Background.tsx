import React, { useContext, ReactNode } from "react";
import { View, ImageBackground, StyleSheet, Dimensions } from "react-native";

import { ThemeContext } from "@/config/Theme";
const { width: SCREENWIDTH, height: SCREENHEIGHT } = Dimensions.get("window");

interface BackgroundProps {
  children: ReactNode;
}

export default function Background({ children }: BackgroundProps) {
  const { theme } = useContext(ThemeContext);
  return (
    <ImageBackground
      style={styles.backgroundImage}
      source={require("~/background/mainBackground.png")}>
      <View style={{ ...styles.container, backgroundColor: theme.background }}>{children}</View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: "center",
  },
  backgroundImage: {
    flex: 1,
    // position: "absolute",
    height: SCREENHEIGHT,
    // width: SCREENWIDTH,
  },
});
