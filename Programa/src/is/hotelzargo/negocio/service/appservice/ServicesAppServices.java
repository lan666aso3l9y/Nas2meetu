package is.hotelzargo.negocio.service.appservice;

import java.util.Vector;

import is.hotelzargo.negocio.exception.ServicesAppServicesException;
import is.hotelzargo.negocio.service.transfer.ServiceTransfer;

public interface ServicesAppServices {

	void addService(ServiceTransfer t)throws ServicesAppServicesException;

	void delService(int id)throws ServicesAppServicesException;;

	Vector<ServiceTransfer> listService()throws ServicesAppServicesException;;

	void modService(ServiceTransfer t)throws ServicesAppServicesException;;

}
