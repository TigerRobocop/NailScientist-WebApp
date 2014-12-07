package BLL.Polish;

import DAL.DAL_Factory;
import DAL.Polish.DAL_Brand;
import basicas.polish.Brand;


public class BLL_Brand {

	public void insert(Brand b) throws Exception {
		DAL_Brand dao = DAL_Factory.getDAL_Brand();
		if (b.getName().trim().equals("")) {
			throw new Exception("Informe o nome da MARCA");
		}
		Brand teste = dao.findByName(b.getName());
		if (teste != null) {
			throw new Exception("MARCA já existe no sistema");
		}
		dao.insert(b);
	}
	
	public Brand update(Brand b) throws Exception {
		DAL_Brand dao = DAL_Factory.getDAL_Brand();
		if (b.getName().trim().equals("")) {
			throw new Exception("Informe o nome da MARCA");
		}
		Brand teste = dao.findByName(b.getName());
		if (teste != null && teste.getId() != b.getId()) {
			throw new Exception("MARCA já existe no sistema");
		}
		return dao.update(b);
	}
	
	public void delete(Brand obj) throws Exception {
		DAL_Brand dao = DAL_Factory.getDAL_Brand();
		if (obj == null) {
			throw new Exception("Selecione uma MARCA válida para excluir");
		}
		dao.delete(obj);
	}

}
