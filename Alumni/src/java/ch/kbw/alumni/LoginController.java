package ch.kbw.alumni;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

//import javax.faces.bean.SessionScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;


@Named
@ManagedBean(name = "user")
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = -2324232432423423432L;
	private String name, password, input, passwordInput;
	
	private boolean isLoggedIn;
	private String message;
	//private ArrayList<Address> adresse = new ArrayList<>();
	private int count, value;
	private String output, property, progress;
	private boolean hide = true;
	int i = 0;

	public LoginController() {
		super();
		name = null;
		password = null;
		isLoggedIn = false;
		//adresse.add(new Address(0, "", ""));
		message = "";
		count = 0;
		setProgress("0%");
	}

	// Methodes
	public String checkUser() {

		if (checkAccout() && !getName().equals("")) {
			isLoggedIn = true;
		}
		return "index.xhtml?faces-redirect=true";
	}

	public String logout() {
		isLoggedIn = false;
		return "logout.xhtml?faces-redirect=true";
	}

	// private boolean validateUser() {
	// AddressService as = new AddressService();
	// for (Address adress : as.getAllAddresses()) {
	// System.out.println(adress.getName());
	// if (getName().equals(adress.getName()) &&
	// getPassword().equals(adress.getPassword()) ) {
	// return true;
	// }
	// }
	//
	// return false;
	//
	// }

	// Getters

	public String getName() {
		return name;
	}

	public String getPasswordInput() {
		return passwordInput;
	}

	public void setPasswordInput(String passwordInput) {
		this.passwordInput = passwordInput;
	}

	public String getPassword() {

		return password;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

//	public boolean goodPassword(String password) {
//		if ((pc.checkPassword(password) * 100) / 5 > 50) {
//			return true;
//		}
//		return false;
//	}

//	public String addAccount() throws Exception {
//		
//		CaptchaAction ca = new CaptchaAction();
//		message="";
//		if(ca.getCaptchaValue().toLowerCase().equals(getInput().toLowerCase()) && goodPassword(getPassword())){
//			addingAccount(getName(), getPassword());
//			setName("");
//			
//			return "index.xhtml?faces-redirect=true";
//		}else{
//			if(!goodPassword(getPassword())){
//				message += "Password has to have more than 50%! \n";
//			}
//			
//			if(!ca.getCaptchaValue().toLowerCase().equals(getInput().toLowerCase())){
//
//				message += "Captcha is not Equal!";
//			}
//			
//			return "register.xhtml?faces-redirect=true";
//		}
//		
//	}
//
//	private void addingAccount(String name, String pw) {
//		adresse.add(new Address(i++, name, pw));
//	}

	private boolean checkAccout() {
//		if (adresse.size() != 0) {
//			for (Address adress : adresse) {
//				System.out.println(adress.getName());
//				if (getName().equals(adress.getName()) && getPassword().equals(adress.getPassword())) {
//					return true;
//				}
//			}
//		}
		return false;

	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	//// password

	public void hide() {
		if (hide) {
			hide = false;
			setProperty(
					"<h:inputText value=\"#{passwordController.password}\" id=\"passwordInput\"  ><f:ajax event=\"keyup\" listener=\"#{passwordController.ajaxListener}\"execute=\"passwordInput\" render=\"output\" /></h:inputText>");
		} else {
			hide = true;
			setProperty(
					"<h:inputSecret value=\"#{passwordController.password}\" id=\"passwordInput\"  ><f:ajax event=\"keyup\" listener=\"#{passwordController.ajaxListener}\"execute=\"passwordInput\" render=\"output\" /></h:inputSecret>");
		}
	}

	public void checkThis(String text) {
		setPassword(text);
	}

	public double getPercentage() {
		return (checkPassword(getPassword()) * 1.0 / 5) * 100;
	}

	public double getPercentage(String text) {
		return (checkPassword(text) / 5) * 100;
	}

	public void drawProgressBar() {

		setOutput(String.valueOf(getPercentage()) + "%");
		setValue((int) getPercentage());
		String lenght = "";
		for (int i = 0; i < getValue() / 2; i++) {
			lenght += "|";
		}
		lenght += getOutput();
		for (int i = 0; i < getValue() / 2; i++) {
			lenght += "|";
		}
		setProgress(lenght);
	}

	public void ajaxListener(AjaxBehaviorEvent event) {
		drawProgressBar();

	}

	public int checkPassword(String password) {
		count = 0;
		boolean bigmatches = false;
		if (password.matches(".*[A-Z]+.*")) {
			bigmatches = true;
			count++;
		}
		if (password.matches(".*[a-z]+.*")) {

			count++;
		}
		if (password.matches(".*[0-9]+.*")) {

			count++;
		}
		if (password.matches(".*[!�$%&=?+#^�?].*") && bigmatches) {
			count++;
		}
		if (password.length() > 5) {
			count++;
		}
		return count;
	}

	public String getSuperPassword() {
		String end = "";
		Random rand = new Random();
		String code = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!+#&%=?";
		for (int i = 0; i < 10; i++) {
			int sub = rand.nextInt(code.length());
			end += code.substring(sub, sub + 1);
		}
		if (getPercentage(end) >= 50) {
			return end;
		} else {
			return getSuperPassword();
		}
	}

	public void generate() {

		if (getPasswordInput().isEmpty()) {

			setPassword(getSuperPassword());
			setPasswordInput(getSuperPassword());

		} else {
			generatePasswordFromText();

		}
		drawProgressBar();
	}

	public void generatePasswordFromText() {
		String[] inText = getPasswordInput().split(" ");
		String passW = "";
		for (int i = 0; i < inText.length; i++) {
			if (inText[i].matches("^[0-9]*$")) {
				passW += inText[i];
			} else {
				passW += inText[i].substring(0, 1);
			}
		}
		setPasswordInput(passW);
		setPassword(passW);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public boolean isHide() {
		return hide;
	}

	public void setHide(boolean hide) {
		this.hide = hide;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

}
