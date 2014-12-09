package managedBeans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import basicas.person.Employee;
import basicas.service.Appointment;
import facade.Facade;

@ManagedBean(name="appt")
public class AppointmentBean {

	List<Employee> listEmployee;
	Employee emp;
	Appointment newAppt = new Appointment();
	Date timeAppt;
	
	
	

	@ManagedProperty("#{loginBeanCli}")
    private LoginBeanCli login; // +setter (no getter!)

	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();
/*
	@PostConstruct
	public void init() {
		emp = new Employee();
		eventModel = new DefaultScheduleModel();

	}
*/
	

	public void addEvent(ActionEvent actionEvent) {
		if (event.getId() == null)
			eventModel.addEvent(event);
		else
			eventModel.updateEvent(event);

		event = new DefaultScheduleEvent();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
		newAppt.setStartAppointment((Date)selectEvent.getObject());
	}
	
	 private void addMessage(FacesMessage message) {
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }

	public String requestAppointment() {
		try {
			Facade f = new Facade();

			newAppt.setTimeScheduling(new Date());

			newAppt.setClient(login.client);
			newAppt.setEmployee(emp);
			
			
			System.out.println(timeAppt.toString());
			System.out.println(newAppt.getEmployee().getName());
			System.out.println(newAppt.getClient().getName());
			
			newAppt.setStartAppointment(timeAppt);
			

			Calendar cal = Calendar.getInstance(); // creates calendar
			cal.setTime(newAppt.getStartAppointment()); // sets calendar time/date
			cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
			newAppt.setEndAppointment(cal.getTime());

			f.requestAppointment(newAppt);
			
			return "welcome-cli.xhtml";

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "index";
	}




	public List<Employee> getAllEmployees() {
		List<Employee> retorno = null;
		try {

			Facade f = new Facade();
			retorno = f.listAllEmployee();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return retorno;
	}

	// gets and sets

	public List<Employee> getListEmployee() {
		return getAllEmployees();
	}

	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}
	
	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}
	
	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	
	public Appointment getNewAppt() {
		return newAppt;
	}

	public void setNewAppt(Appointment newAppt) {
		this.newAppt = newAppt;
	}
	
	public void setLogin(LoginBeanCli login) {
		this.login = login;
	}
	
	public Date getTimeAppt() {
		return timeAppt;
	}

	public void setTimeAppt(Date timeAppt) {
		this.timeAppt = timeAppt;
	}

}
