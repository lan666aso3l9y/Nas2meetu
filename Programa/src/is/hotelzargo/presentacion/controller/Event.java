package is.hotelzargo.presentacion.controller;

public enum Event {
	SHOW_CLIENT_FRAME,
	SHOW_ROOM_FRAME,
	SHOW_EMPLOYEE_FRAME,
	SHOW_BOOK_FRAME,
	SHOW_SHIFT_FRAME,
	SHOW_SERVICES_FRAME,
	
	ADD_BOOK,
	ADD_CLIENT,
	ADD_EMPLOYEE,
	ADD_ROOM,
	ADD_SERVICE,
	ADD_SHIFT,
	
	DELETE_BOOK,
	DELETE_CLIENT,
	DELETE_EMPLOYEE,
	DELETE_ROOM,
	DELETE_SERVICE,
	DELETE_SHIFT,
	
	LIST_BOOK,
	LIST_CLIENT,
	LIST_EMPLOYEE,
	LIST_ROOM,
	LIST_SERVICE,
	LIST_SHIFT,
	
	MOD_BOOK,
	MOD_CLIENT,
	MOD_EMPLOYEE,
	MOD_ROOM,
	MOD_SERVICE,
	MOD_SHIFT,
	
	FIND_BOOK,
	CONFIRM_BOOK,
	
	ERROR,
	MESSAGE
}
