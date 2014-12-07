package managedBeans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import facade.Facade;
import basicas.person.Employee;

@ManagedBean(name = "loginBeanEmp")
@SessionScoped
public class LoginBeanEmp implements Serializable {

	private static final long serialVersionUID = 5924349451286753168L;

	private String username;
	private String password;
	Employee teste;
	Employee emp;
	private boolean loggedIn = false;

	public String doLogin() {
		try {
			Facade f = new Facade();
			Employee emp = new Employee();
			emp.setLogin(username);
			emp.setPassword(password);

			emp = f.isLoginValid(emp);

			System.out.println(emp.getLogin());

			if (emp != null) {
				loggedIn = true;

				// FacesContext fc = FacesContext.getCurrentInstance();

				return "welcome-emp.xhtml?faces-redirect=true";

			}

		} catch (Exception e) {
			// Set login ERROR
			FacesMessage msg = new FacesMessage("Login error! "
					+ e.getMessage(), "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		// To to login page
		return "index";

	}

	/**
	 * Logout operation.
	 * 
	 * @return
	 */
	public String doLogout() {
		// Set the paremeter indicating that user is logged in to false
		loggedIn = false;

		username = "";
		password = "";
		// Set logout message
		FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "index?faces-redirect=true";

	}

	// ------------------------------
	// Getters & Setters

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Employee getEmp() {
		try {
			Facade f = new Facade();
			teste.setLogin(username);
			teste.setPassword(password);
			emp = f.isLoginValid(teste);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return emp;
	}

	public void setEmp(Employee emp) {
		emp.setLogin(username);
		emp.setPassword(password);
		this.emp = emp;
	}

}
