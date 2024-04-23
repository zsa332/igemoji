import React from "react";
import { View, Text } from "react-native";

import Button from "@/components/Button";
import MusicToggleButton from "@/components/MusicToggleButton";
import ThemeToggleButton from "@/components/ThemeToggleButton";

export default function RoomList() {
  return (
    <View>
      <ThemeToggleButton />
      <MusicToggleButton />
      <Button name="check" />
      <Text>This is the SignIn screen</Text>
    </View>
  );
}
