import Icon from "@expo/vector-icons/MaterialIcons";
import React, { useState } from "react";
import { TouchableOpacity } from "react-native";

import { MusicIconName } from "@/types/types";

export default function MusicToggleButton() {
  const [iconName, setIconName] = useState<MusicIconName>("music-note");

  const toggleMusicIcon = () => {
    setIconName(iconName === "music-note" ? "music-off" : "music-note");
  };

  return (
    <TouchableOpacity
      onPress={() => {
        toggleMusicIcon();
      }}>
      <Icon name={iconName} size={60} color="black" />
    </TouchableOpacity>
  );
}
