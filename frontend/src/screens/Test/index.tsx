import React from "react";
import { View } from "react-native";

import Button from "@/components/Button";
import MusicToggleButton from "@/components/MusicToggleButton";
import ThemeToggleButton from "@/components/ThemeToggleButton";

export default function Test() {
  return (
    <View>
      <ThemeToggleButton />
      <MusicToggleButton />
      <Button name="check" />
      <Button name="start" />
      <Button name="exit" />
      <Button name="mainStart" />
    </View>
  );
}
