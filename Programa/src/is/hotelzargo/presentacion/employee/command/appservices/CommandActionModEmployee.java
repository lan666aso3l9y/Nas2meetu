package is.hotelzargo.presentacion.employee.command.appservices;

import is.hotelzargo.negocio.employee.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.exception.EmployeeAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.presentacion.commandfactory.Command;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

public class CommandActionModEmployee implements Command {

	private EmployeeTransfer employeeTransfer;
	
	public CommandActionModEmployee(EmployeeTransfer t){
		employeeTransfer = t;
	}
	
	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.modEmployee(employeeTransfer);
			Controller.getInstance().event(Event.MESSAGE,"Empleado modificado",null);
		} catch (EmployeeAppServicesException e) {
			Controller.getInstance().event(Event.ERROR,e.getMessage(),null);
		}
		return null;
	}

}
