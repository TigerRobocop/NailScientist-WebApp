package BLL.Person;

import DAL.DAL_Factory;
import DAL.Person.DAL_Employee;
import basicas.person.Employee;

public class BLL_Employee {

	public BLL_Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public void insert(Employee e) throws Exception {
		DAL_Employee dao = DAL_Factory.getDAL_Employee();
		//validações
		if (e.getName() == null) {
			throw new Exception("NOME nao pode estar nulo");
		}
		if (e.getName().trim().equals("")){
			throw new Exception("Informe o NOME");
		}
		
		if (e.getPhone() == null) {
			throw new Exception("TELEFONE nao pode estar nulo");
		}
		if (e.getPhone().trim().equals("")) {
			throw new Exception("Informe o TELEFONE");
		}
		if (e.getPhone().trim().length() < 13) {
			throw new Exception("Informe um TELEFONE valido");
		}		
		if (e.getPosition() == null) {
			throw new Exception("POSICAO não pode estar nulo");
		}
		if (e.getPosition().trim().equals("")) {
			throw new Exception("Informe a POSICAO");
		}
		if (e.getLogin() == null) {
			throw new Exception("LOGIN nao pode estar nulo");
		}
		
		Employee teste = dao.findByLogin(e.getLogin());
		if (teste != null) {
			throw new Exception("LOGIN já existe no sistema");
		}
		
		if (e.getLogin().trim().equals("")){
			throw new Exception("Informe o LOGIN");
		}
		if (e.getPassword() == null) {
			throw new Exception("SENHA nao pode estar nulo");
		}
		if (e.getPassword().trim().equals("")){
			throw new Exception("Informe o SENHA");
		}
		
		dao.insert(e);
	}
	
	public Employee update(Employee e) throws Exception {
		DAL_Employee dao = DAL_Factory.getDAL_Employee();
		if (e.getName().trim().equals("")){
			throw new Exception("Informe o NOME");
		}
		if (e.getPhone().trim().length() < 13) {
			throw new Exception("Informe um TELEFONE valido");
		}	
		if (e.getPosition().trim().equals("")) {
			throw new Exception("Informe a POSICAO");
		}
		if (e.getLogin().trim().equals("")){
			throw new Exception("Informe o LOGIN");
		}
		if (e.getPassword().trim().equals("")){
			throw new Exception("Informe o SENHA");
		}
		return dao.update(e);
	}

	
	public void delete(Employee e) throws Exception {
		DAL_Employee dao = DAL_Factory.getDAL_Employee();
		if (e == null) {
			throw new Exception("Funcionario a ser excluido nao pode estar nulo");
		}
		dao.delete(e);
	}
	
	
	public Employee isLoginValid(Employee e) throws Exception{
		DAL_Employee dao = DAL_Factory.getDAL_Employee();
		if (e.getLogin() == null) {
			throw new Exception("LOGIN nao pode estar nulo");
		}
		if (e.getLogin().trim().equals("")) {
			throw new Exception("Informe o LOGIN");
		}
		if (e.getPassword() == null) {
			throw new Exception("SENHA nao pode estar nulo");
		}
		if (e.getPassword().trim().equals("")) {
			throw new Exception("Informe a senha");
		}		
		
		Employee retorno = dao.isLoginValid(e);
		
		if (retorno == null) {
			throw new Exception("LOGIN invalido");
		}
		
		return retorno;
	}
	
	public Employee findByLogin(String login){
		DAL_Employee dao = DAL_Factory.getDAL_Employee();
		return dao.findByLogin(login);
	}
	

}
