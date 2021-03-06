package is.hotelzargo.integracion.book.dao;

import java.sql.Date;
import java.util.Vector;

import is.hotelzargo.integracion.exception.BookIntegrationException;
import is.hotelzargo.negocio.book.transfer.BookTransfer;

public interface BookDAO {

	public void createBook(BookTransfer t) throws BookIntegrationException;
	
	public void deleteBook(int id) throws BookIntegrationException;
	
	public void updateBook(BookTransfer t) throws BookIntegrationException;
	
	public BookTransfer getBook(int id) throws BookIntegrationException;
	
	public boolean searchBook(int id) throws BookIntegrationException;
		
	public Vector<BookTransfer> listBook() throws BookIntegrationException;
	
	public void confirmBook(int id) throws BookIntegrationException;
	
	public Vector<Integer> findBook(Date checkIn,Date checkOut) throws BookIntegrationException;

	public boolean emptyRooms(Vector<Integer> idRoom,Date in,Date out) throws BookIntegrationException;

	public boolean existsClient(int idClient) throws BookIntegrationException;

	public boolean existsRooms(Vector<Integer> idRoom) throws BookIntegrationException;

	public boolean existsServices(Vector<Integer> services) throws BookIntegrationException;
	
}
