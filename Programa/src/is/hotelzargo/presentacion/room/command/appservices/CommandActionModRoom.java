package is.hotelzargo.presentacion.room.command.appservices;

import is.hotelzargo.negocio.exception.RoomAppServicesException;
import is.hotelzargo.negocio.facade.Facade;
import is.hotelzargo.negocio.factory.BusinessFactory;
import is.hotelzargo.negocio.room.transfer.RoomTransfer;
import is.hotelzargo.presentacion.commandfactory.Command;

public class CommandActionModRoom implements Command {

	private RoomTransfer roomTransfer;
	
	public CommandActionModRoom(RoomTransfer t){
		roomTransfer = t;
	}
	
	@Override
	public Object execute() {
		
		Facade facade = BusinessFactory.getInstance().getFacade();
		
		try {
			facade.modRoom(this.roomTransfer);
		} catch (RoomAppServicesException e) {
			e.printStackTrace();
		}
		return null;
	}

}