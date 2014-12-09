package managedBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import facade.Facade;
import basicas.service.Appointment;

@ManagedBean(name = "confirmAppt")
@ViewScoped
public class CalendarBean implements Serializable {

	private static final long serialVersionUID = 1778611320935836740L;
	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();
	List<Appointment> listAppts;
	Appointment appt;
	
	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();
		this.listAppts = getAppointments();

		if (listAppts.size() > 0) {
			for (Appointment sc : listAppts) {
				eventModel.addEvent(new DefaultScheduleEvent(sc.getClient()
						.getName(), sc.getStartAppointment(), sc
						.getEndAppointment(), sc));
			}
		}

	}

	private List<Appointment> getAppointments() {
		List<Appointment> retorno = null;
		try {
			Facade f = new Facade();
			retorno = f.listUnconfirmed();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return retorno;
	}

	public String confirmAppointment() {
		try {
			Facade f = new Facade();
			appt = f.findScheduling(appt.getId());

			appt.setConfirmed(true);

			f.confirmAppointment(appt);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "emp-confirmAppts.xhtml";
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

	public void addEvent(ActionEvent actionEvent) {
		if (event.getId() == null)
			eventModel.addEvent(event);
		else
			eventModel.updateEvent(event);

		event = new DefaultScheduleEvent();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
		appt = (Appointment) event.getData();
	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
		
	}

	public void onEventMove(ScheduleEntryMoveEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event moved", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Event resized", "Day delta:" + event.getDayDelta()
						+ ", Minute delta:" + event.getMinuteDelta());

		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public List<Appointment> getListAppts() {
		return listAppts;
	}

	public void setListAppts(List<Appointment> listAppts) {
		this.listAppts = listAppts;
	}

	public Appointment getAppt() {
		return appt;
	}

	public void setAppt(Appointment appt) {
		this.appt = appt;
	}
}