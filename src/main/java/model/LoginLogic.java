package model;

public class LoginLogic {
	public boolean execute(User user) {
		String input_name = user.getName();
		String input_pass = user.getPass();
		
		if(input_name.equals("hyokatsu") && input_pass.equals("hyokatsu")) {
			return true;
			
		}
		return false;
	}
}
