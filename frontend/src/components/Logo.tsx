import React from "react";
import { StyleSheet, Image, Dimensions } from "react-native";

const { width: SCREENWIDTH, height: SCREENHEIGHT } = Dimensions.get("window");

export default function Logo() {
  return (
    <>
      <Image style={styles.logo} source={require("~/logo/logo.png")} />
    </>
  );
}

const styles = StyleSheet.create({
  logo: {
    marginTop: SCREENHEIGHT * (1 / 10),
  },
});
