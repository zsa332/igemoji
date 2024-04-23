type ThemeIconName = "sun-o" | "moon-o";

type MusicIconName = "music-note" | "music-off";

type ButtonName = "check" | "start" | "exit" | "mainStart";

interface NavigationProps {
  navigation: {
    navigate: (screen: string) => void;
  };
}

export type { ThemeIconName, MusicIconName, ButtonName, NavigationProps };
