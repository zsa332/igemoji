module.exports = {
  root: true,
  extends: ["universe/native", "prettier"],
  rules: {
    "react-hooks/exhaustive-deps": "warn",
    "prettier/prettier": [
      "error",
      {
        endOfLine: "auto",
      },
    ],
  },
};
