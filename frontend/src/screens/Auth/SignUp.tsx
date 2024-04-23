import SignUpComponent from "@/components/Auth/SignUp";
import { NavigationProps } from "@/types/types";

export default function SignUp({ navigation }: NavigationProps) {
  return (
    <>
      <SignUpComponent navigation={navigation} />
    </>
  );
}
