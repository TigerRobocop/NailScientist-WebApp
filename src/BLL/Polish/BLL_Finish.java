package BLL.Polish;

import DAL.DAL_Factory;
import DAL.Polish.DAL_Finish;
import basicas.polish.Finish;

public class BLL_Finish {
	public void insert(Finish f) throws Exception {
		DAL_Finish dao = DAL_Factory.getDAL_Finish();
		if (f.getName().trim().equals("")) {
			throw new Exception("Informe o tipo do Acabamento");
		}
		
		Finish teste = dao.findByName(f.getName());
		if (teste != null) {
			throw new Exception("ACABAMENTO já existe no sistema");
		}
		dao.insert(f);
	}
	
	public Finish update(Finish f) throws Exception {
		DAL_Finish dao = DAL_Factory.getDAL_Finish();
		if (f.getName().trim().equals("")) {
			throw new Exception("Informe o tipo do Acabamento");
		}
		
		Finish teste = dao.findByName(f.getName());
		if (teste != null && teste.getId() != f.getId()) {
			throw new Exception("ACABAMENTO já existe no sistema");
		}
		
		return dao.update(f);
	}
	
	public void delete(Finish f) throws Exception {
		DAL_Finish dao = DAL_Factory.getDAL_Finish();
		if (f == null) {
			throw new Exception("Selecione um ACABAMENTO válido para excluir");
		}
		dao.delete(f);
	}


}
