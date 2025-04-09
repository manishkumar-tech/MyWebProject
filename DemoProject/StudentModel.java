
public class StudentModel {
String email;
String password;

public StudentModel() {
	
}

public StudentModel(String email,String password) {
	this.email=email;
	this.password=password;
}


public void setEmail(String email) {
	this.email=email;
}

public String getEmail() {
	return email;
}

public void setPassword(String password) {
	this.password=password;
}

public String getPassword() {
	return password;
}


}
