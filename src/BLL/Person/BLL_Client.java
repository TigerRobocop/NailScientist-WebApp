package BLL.Person;

import DAL.DAL_Factory;
import DAL.Person.DAL_Client;

import basicas.person.Client;


public class BLL_Client {

	public void insert(Client e) throws Exception {
		DAL_Client dao = DAL_Factory.getDAL_Client();
		dao.insert(e);
	}

	public Client update(Client e) throws Exception {
		DAL_Client dao = DAL_Factory.getDAL_Client();
		if (e.getName().trim().equals("")) {
			throw new Exception("Informe o NOME");
		}
		if (e.getPhone().trim().length() < 13) {
			throw new Exception("Informe um TELEFONE valido");
		}
		if (e.getLogin().trim().equals("")) {
			throw new Exception("Informe o LOGIN");
		}
		if (e.getPassword().trim().equals("")) {
			throw new Exception("Informe o SENHA");
		}
		return dao.update(e);
	}
	
	public void delete(Client e) throws Exception {
		DAL_Client dao = DAL_Factory.getDAL_Client();

		if (e == null) {
			throw new Exception("Cliente a ser excluido nao pode estar nulo");
		}
		dao.delete(e);
	}
	
	public Client findByLogin(String login){
		DAL_Client dao = DAL_Factory.getDAL_Client();
		return dao.findByLogin(login);
	}
	
	public Client isLoginValid(Client c) throws Exception{
		DAL_Client dao = DAL_Factory.getDAL_Client();
		if (c.getLogin() == null) {
			throw new Exception("LOGIN nao pode estar nulo");
		}
		if (c.getLogin().trim().equals("")) {
			throw new Exception("Informe o LOGIN");
		}
		if (c.getPassword() == null) {
			throw new Exception("SENHA nao pode estar nulo");
		}
		if (c.getPassword().trim().equals("")) {
			throw new Exception("Informe a senha");
		}		
		
		Client retorno = dao.isLoginValid(c);
		
		if (retorno == null) {
			throw new Exception("LOGIN invalido");
		}
		
		return retorno;
	}
}
