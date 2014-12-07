package BLL.Scheduling;

import java.io.Serializable;

import DAL.DAL_Factory;
import DAL.Service.DAL_Scheduling;
import basicas.service.Appointment;

public class BLL_Scheduling {

	
	public void insert(Appointment sc){
		DAL_Scheduling dao = DAL_Factory.getDAL_Scheduling();
		dao.insert(sc);
	}
	
	public Appointment findById(Serializable key){
		DAL_Scheduling dao = DAL_Factory.getDAL_Scheduling();
		return dao.findByID(key);
	}
	
	public Appointment update(Appointment sc){
		DAL_Scheduling dao = DAL_Factory.getDAL_Scheduling();
		return dao.update(sc);
	}
	
	
	
	
}
