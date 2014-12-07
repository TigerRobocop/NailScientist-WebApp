package managedBeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import basicas.person.Client;
import facade.Facade;

@ManagedBean
@SessionScoped
public class LoginBeanCli implements Serializable {
	private static final long serialVersionUID = 5924349451286753168L;

	private String username;
	private String password;
		private boolean loggedIn = false;

	public String doLogin() {
		try {
			
			Facade f = new Facade();
			Client cli = new Client();
			cli.setLogin(username);
			cli.setPassword(password);

			cli = f.findClientByLogin(username);

			System.out.println(cli.getLogin());

			if (cli != null) {
				loggedIn = true;
				// FacesContext fc = FacesContext.getCurrentInstance();

				return "welcome-cli.xhtml?faces-redirect=true";
			}

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Login error! "
					+ e.getMessage(), "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		// Set login ERROR
		FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);

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
}
