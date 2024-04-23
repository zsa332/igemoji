import Icon from "@expo/vector-icons/FontAwesome";
import React, { useContext, useState } from "react";
import { TouchableOpacity } from "react-native";

import { ThemeContext } from "@/config/Theme";
import { ThemeIconName } from "@/types/types";

export default function ThemeToggleButton() {
  const { toggleTheme } = useContext(ThemeContext);
  const [iconName, setIconName] = useState<ThemeIconName>("sun-o");

  const toggleThemeIcon = () => {
    setIconName(iconName === "sun-o" ? "moon-o" : "sun-o");
  };

  return (
    <TouchableOpacity
      onPress={() => {
        toggleTheme();
        toggleThemeIcon();
      }}>
      <Icon name={iconName} size={60} color="black" />
    </TouchableOpacity>
  );
}
