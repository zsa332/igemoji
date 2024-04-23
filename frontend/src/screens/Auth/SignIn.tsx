import SignInComponent from "@/components/Auth/SignIn";
import { NavigationProps } from "@/types/types";

export default function SignIn({ navigation }: NavigationProps) {
  return (
    <>
      <SignInComponent navigation={navigation} />
    </>
  );
}
