package BLL.Polish;

import DAL.DAL_Factory;
import DAL.Polish.DAL_Polish;
import basicas.polish.Polish;

public class BLL_Polish {

	public void insert(Polish p) throws Exception {
		DAL_Polish dao = DAL_Factory.getDAL_Polish();

		// validações
		if (p.getName().trim().equals("")) {
			throw new Exception("Informe o NOME");
		}

		if (p.getBrand() == null) {
			throw new Exception("Selecione uma MARCA");
		}

		//
		/*Polish teste = dao.findByName(p.getName());
		if (teste != null
				&& teste.getBrand().getName() == p.getBrand().getName()) {
			throw new Exception(
					"Ja existe esmalte desta marca e nome no sistema");
		}*/

		if (p.getFinish() == null) {
			throw new Exception("Selecione um ACABAMENTO");
		}

		if (p.getColor().trim().equals("")) {
			throw new Exception("Informe a COR");
		}

		dao.insert(p);
	}

	public Polish update(Polish p) throws Exception {
		DAL_Polish dao = DAL_Factory.getDAL_Polish();

		// validações
		if (p.getName().trim().equals("")) {
			throw new Exception("Informe o NOME");
		}

		if (p.getBrand() == null) {
			throw new Exception("Selecione uma MARCA");
		}

		//
		Polish teste = dao.findByName(p.getName());
		if (teste != null && teste.getId() != p.getId()
				&& teste.getBrand().getName() == p.getBrand().getName()) {
			throw new Exception(
					"Ja existe esmalte desta marca e nome no sistema");
		}

		if (p.getFinish() == null) {
			throw new Exception("Selecione um ACABAMENTO");
		}

		if (p.getColor().trim().equals("")) {
			throw new Exception("Informe a COR");
		}

		return dao.update(p);
	}
	
	public void delete(Polish obj) throws Exception {
		DAL_Polish dao = DAL_Factory.getDAL_Polish();
		
		if (obj == null) {
			throw new Exception("Selecione um esmalte válido a ser excluído");
		}
		dao.delete(obj);
	}

}
