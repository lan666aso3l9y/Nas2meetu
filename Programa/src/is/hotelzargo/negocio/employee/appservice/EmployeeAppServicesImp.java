package is.hotelzargo.negocio.employee.appservice;

import java.util.Vector;

import is.hotelzargo.integracion.employee.dao.EmployeeDAO;
import is.hotelzargo.integracion.exception.EmployeeIntegrationException;
import is.hotelzargo.integracion.factory.DAOFactory;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransferAdmin;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransferServices;
import is.hotelzargo.negocio.exception.EmployeeAppServicesException;

public class EmployeeAppServicesImp implements EmployeeAppServices {

	@Override
	public void addEmployee(EmployeeTransfer t) throws EmployeeAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		if(t instanceof EmployeeTransferAdmin ) {
			
			checkDataEmployeeAdmin(t);
			
			try{
				
				if (!dao.searchEmployee(t.getDNI())){
					if (dao.existsShift(t.getShift())){
							dao.createEmployeeAdmin(t);
					}
					else{
						throw new EmployeeAppServicesException("El turno del empleado no existe");
					}
				}
				else{
					throw new EmployeeAppServicesException("DNI empleado repetido");
				}

			} catch (EmployeeIntegrationException e) {				
				throw new EmployeeAppServicesException(e.getMessage());
			}
					
		} else {
			
			checkDataEmployeeServices(t);
			
			try{
				if (!dao.searchEmployee(t.getDNI())){
					if (dao.existsShift(t.getShift())){
						dao.createEmployeeServices(t);
					}
					else{
						throw new EmployeeAppServicesException("El turno del empleado no existe");
					}
				}
				else{
						throw new EmployeeAppServicesException("DNI empleado repetido");
				} 
			} catch (EmployeeIntegrationException e) {				
				throw new EmployeeAppServicesException(e.getMessage());
			}
		}
	
		
	}
	
	@Override
	public void delEmployee(int id) throws EmployeeAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		try {
			if (dao.searchEmployeeByID(id)){
				dao.deleteEmployee(id);
			}
			else{
				throw new EmployeeAppServicesException("Empleado inexistente con ese ID");
			}
		} catch (EmployeeIntegrationException e) {
			throw new EmployeeAppServicesException(e.getMessage());
		}
		
	}

	
	
	@Override
	public Vector<EmployeeTransfer> listEmployee() throws EmployeeAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		try {
			return dao.listEmployee();
		} catch (EmployeeIntegrationException e) {
			throw new EmployeeAppServicesException(e.getMessage());
		}
		
	}

	@Override
	public void modEmployee(EmployeeTransfer t) throws EmployeeAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		
		if(t instanceof EmployeeTransferAdmin ) {
			
			checkDataEmployeeAdmin(t);
			
			try{
				if (dao.searchEmployeeByID(t.getId())){
					if (dao.existsShift(t.getShift())){
							dao.updateEmployeeAdmin(t);
					}
					else{
						throw new EmployeeAppServicesException("El turno del empleado a modificar no existe");
					}
				}
				else{
					throw new EmployeeAppServicesException("El empleado a modificar no existe");
				}
						
			} catch (EmployeeIntegrationException e) {
				
				throw new EmployeeAppServicesException(e.getMessage());
			}
					
		} else {
			
			checkDataEmployeeServices(t);
			
			try{

				if (dao.searchEmployeeByID(t.getId())){
					if (dao.existsShift(t.getShift())){
						dao.updateEmployeeServices(t);
					}
					else{
						throw new EmployeeAppServicesException("El turno del empleado a modificar no existe");
					}
				}
				else{
					throw new EmployeeAppServicesException("El empleado a modificar no existe");
				}

			} catch (EmployeeIntegrationException e) {				
				throw new EmployeeAppServicesException(e.getMessage());
			}
		}
		
	}
	
	private void checkDataEmployeeServices(EmployeeTransfer t) throws EmployeeAppServicesException {
		
		String phone = ((EmployeeTransferServices)t).getPhone();
		
		if(((EmployeeTransferServices)t).getDNI().length() != 9) 
			throw new EmployeeAppServicesException("Sin DNI");
		if(((EmployeeTransferServices)t).getName().length() < 3)
			throw new EmployeeAppServicesException("Sin nombre");
		if	(((EmployeeTransferServices)t).getSurname().length() < 3)
			throw new EmployeeAppServicesException("Sin apellido");
		if (phone.length() != 9)
			throw new EmployeeAppServicesException("Telefono no valido");
		if(((EmployeeTransferServices)t).getPay() == 0)
			throw new EmployeeAppServicesException("Sin sueldo");
		
	}

	private void checkDataEmployeeAdmin(EmployeeTransfer t) throws EmployeeAppServicesException {
		
		String phone = ((EmployeeTransferAdmin)t).getPhone();
		
		if(((EmployeeTransferAdmin)t).getDNI().isEmpty()) 
			throw new EmployeeAppServicesException("Sin DNI");
		if(((EmployeeTransferAdmin)t).getName().isEmpty())
			throw new EmployeeAppServicesException("Sin nombre");
		if(((EmployeeTransferAdmin)t).getSurname().isEmpty())
			throw new EmployeeAppServicesException("Sin apellido");
		if(((EmployeeTransferAdmin)t).getPassword().isEmpty())
			throw new EmployeeAppServicesException("Sin contrasegna");
		if ((phone.length() != 9))//||(phone.indexOf("9") == -1)||(phone.indexOf("6") == -1))
			throw new EmployeeAppServicesException("Sin tlf");
		if(((EmployeeTransferAdmin)t).getPay() == 0)
			throw new EmployeeAppServicesException("Sin sueldo");
		
		
	}

}
