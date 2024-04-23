import React from "react";

export const themes = {
  light: {
    kungya: "#FAFADD",
    kungyaYelloDark: "#B8B868",
    kungyaGreen: "#EFFADD",
    kungyaYello: "#FAFA8D",
    kungyaYelloLight: "#FAFAB5",
    white: "#FDFDFD",
    grey: "#C0C0C0",
    text: "#47473F",
    black: "#000000",
    kungyaGreenAccent: "#A0E664",
    kungyaRed: "#FFDCE0",
    kungyaRedDark: "#FF95A1",
    kungyaGreenAccent2: "#47A437",
    background: "rgba(255, 255, 255, 0)",
    gameBackground: "rgba(255,255,255,0.9)",
  },
  dark: {
    kungya: "#1B1D24",
    kungyaYelloDark: "#848998",
    kungyaGreen: "#EFFADD",
    kungyaYello: "#595D6E",
    kungyaYelloLight: "#575C6D",
    white: "#000000",
    grey: "#C0C0C0",
    text: "#FDFDFD",
    black: "#FDFDFD",
    kungyaGreenAccent: "#424754",
    kungyaRed: "#FFDCE0",
    kungyaRedDark: "#FF95A1",
    kungyaGreenAccent2: "#47A437",
    background: "rgba(0, 0, 0, 0.5)",
    gameBackground: "rgba(0, 0, 0, 0.5)",
  },
};

export const ThemeContext = React.createContext({
  theme: themes.light,
  toggleTheme: () => {},
});
