package DAL.Service;

import javax.persistence.EntityManager;

import basicas.service.Appointment;
import DAL.DAL_Generic;

public class DAL_Appointment extends DAL_Generic<Appointment> {

	public DAL_Appointment(EntityManager em) {
		super(em);
	}

}
