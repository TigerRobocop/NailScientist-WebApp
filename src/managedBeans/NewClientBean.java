package managedBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import facade.Facade;
import basicas.person.Client;

@ManagedBean(name="signup")
@ViewScoped
public class NewClientBean implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -2874474511751635958L;
	
	Client client;
	
	public NewClientBean(){
		this.client = new Client();
	}
	
	

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public String registerNew(){
		try {
			Facade f = new Facade();
			f.insertCliente(client);
			
			return "welcome-cli.xhtml?faces-redirect=true";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
