package is.hotelzargo.presentacion.controller;

public abstract class Controller {

	private static Controller instance = null;
	
	public static ControllerImp getInstance(){
		if(instance == null){
			instance = new ControllerImp();
		}
		return (ControllerImp) instance;
	}
	
	public abstract Object event(Event event, Object data,Object returnData);
}
