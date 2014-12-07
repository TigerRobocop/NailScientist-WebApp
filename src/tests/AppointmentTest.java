package tests;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import basicas.person.Client;
import basicas.person.Employee;
import basicas.service.Appointment;
import facade.Facade;

public class AppointmentTest {
	
	@Test
	public void testRequestAppt(){
		Facade f = new Facade();
		Appointment appt = new Appointment();
		try {
			Employee emp = f.findByNameEmployee("LIVIA");
			Client cli = f.findClientByLogin("juli");
			
			
			appt.setEmployee(emp);
			appt.setClient(cli);
			appt.setStartAppointment(new Date());
			
			Calendar cal = Calendar.getInstance(); // creates calendar
			cal.setTime(appt.getStartAppointment()); // sets calendar time/date
			cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour			
			appt.setEndAppointment(cal.getTime());
			
			appt.setTimeScheduling(new Date());
			
			f.requestAppointment(appt);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertEquals("no good", "juli", appt.getClient().getLogin());

	}

}
