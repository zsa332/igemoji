import { SERVER_API, SOCKET_API, LOCAL_SERVER_API, LOCAL_SOCKET_API, NODE_ENV } from "@env";

export const getBaseServerUrl = () => {
  if (NODE_ENV === "development") {
    return LOCAL_SERVER_API;
  }
  return SERVER_API;
};

export const getBaseSocketServerUrl = () => {
  if (NODE_ENV === "development") {
    return LOCAL_SOCKET_API;
  }
  return SOCKET_API;
};
