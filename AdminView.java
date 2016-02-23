import java.util.ArrayList;

import Basic.Facility;
import Basic.Member;
import Basic.User;
import Common.CreateMemberException;

public class AdminView {


	private User loginUser;
	private AdminController controller;
	private MainMenuView f1;
	private CreateBookingView fBookingView;
	private CreateFacilityView fCreateFacilityView;
	private UpdateFacilityView fUpdateFacility;
	private DeleteMemberView fDelete;
	private ResetMemberPasswordView fResetPlayer;
	private SearchFacilityView fSearchFacility;
	private ChangePasswordView fResetAdmin;
	
	
	public AdminView(User a, AdminController controller) {

		this.loginUser = a;
		this.controller = controller;
		f1 = new MainMenuView(this);
		f1.pack();
		f1.setLocationRelativeTo(null);

	}
	public static void main(String []args){

	}
	public boolean isAdmin(){
		if (loginUser.getRole().equalsIgnoreCase("admin")) return true;
		else return false;
	}
	public User getLoginUser(){
		return loginUser;
	}
	public void show() {
		f1.setLocationRelativeTo(null);
		f1.setVisible(true);
		controller.saveMemberData();
	}
	public void showMenuMember() {
		
	}
	
	public void run(){
		f1.setLocationRelativeTo(null);
		f1.setVisible(true);
	}
	public void frameCreateBooking(){
		fBookingView = new CreateBookingView(this, 0);
		fBookingView.setLocationRelativeTo(null);
		fBookingView.setVisible(true);
		f1.setVisible(false);
	}
	
	
	public void frameDelete(){
		fDelete = new DeleteMemberView(this);
		fDelete.pack();
		fDelete.setLocationRelativeTo(null);
		fDelete.setVisible(true);
		f1.setVisible(false);
		
	}
	public void frameResetPlayer(){
		fResetPlayer = new ResetMemberPasswordView(this);
		fResetPlayer.pack();
		fResetPlayer.setLocationRelativeTo(null);
		fResetPlayer.setVisible(true);
		f1.setVisible(false);
		
	}

	public void frameChangeAdminPassword(){
		fResetAdmin = new ChangePasswordView(this);
		fResetAdmin.pack();
		fResetAdmin.setLocationRelativeTo(null);
		fResetAdmin.setVisible(true);
		f1.setVisible(false);
	}


	//runAddTransactionSQLrunAddTransactionSQL(Member member, Facility facility, int timeSlot, int paxCount) 
	void createBooking(String date, int facility, int timeSlot, int paxCount, int price){
		controller.createBooking(date, facility,  timeSlot, paxCount, price);
	}
	public void deletePlayer(String name) throws InvalidMemberException {
		controller.deleteMember(name);
		controller.saveMemberData();
		
	}
	public void mainMenu() {
		f1.setLocationRelativeTo(null);
		f1.setVisible(true);
		if (fDelete!= null) fDelete.setVisible(false);
		if (fResetPlayer!= null) fResetPlayer.setVisible(false);
		if (fCreateFacilityView!= null) fCreateFacilityView.setVisible(false);
		if (fResetAdmin!= null) fResetAdmin.setVisible(false);
		if (fBookingView!= null) fBookingView.setVisible(false);
		if (fSearchFacility!= null) fSearchFacility.setVisible(false);
		
		
	}
	public void resetPlayerPassword(String name, String password) throws CreateMemberException {
		controller.resetMemberPassword(name, password);
		controller.saveMemberData();
		
		
	}
//	public ArrayList<Player> getTopScorer() {
//		
//		return controller.getTopScorer();
//	}
//	
	public void changeAdminPassword(String password1, String password2) throws InvalidPasswordException{
		controller.changeAdminPassword(password1, password2);
	}
	public void logOut(){
		f1.setVisible(false);
		AdminModule newModule = new AdminModule();
		newModule.run();
		
	}
	public void frameCreateFacility() {
		// TODO Auto-generated method stub
		fCreateFacilityView = new CreateFacilityView(this);
		fCreateFacilityView.setLocationRelativeTo(null);
		fCreateFacilityView.setVisible(true);
		f1.setVisible(false);
		
	}
	public void frameUpdateFacility(int idToUpdate) {
		// TODO Auto-generated method stub
		fUpdateFacility = new UpdateFacilityView(this, idToUpdate);
		fUpdateFacility.setLocationRelativeTo(null);
		fUpdateFacility.setVisible(true);
		fSearchFacility.setVisible(false);
		f1.setVisible(false);
		
	}
	//UpdateFacilityView
	public void createFacility(String name, String facility, int pricePeak, int priceNonPeak, int cap) {
		// TODO Auto-generated method stub
		controller.createFacility(name, facility, pricePeak, priceNonPeak, cap);
		
	}
	public void frameSearchFacility() {
		// TODO Auto-generated method stub
		fSearchFacility = new SearchFacilityView(this);
		fSearchFacility.setLocationRelativeTo(null);
		fSearchFacility.setVisible(true);
		if (fUpdateFacility!= null) fUpdateFacility.setVisible(false);
		f1.setVisible(false);
		
	}
	public void updateFacility(int idToUpdate, String name, String facility, int pricePeak, int priceNonPeak, int cap) {
		// TODO Auto-generated method stub
		controller.updateFacility(idToUpdate,  name,  facility,  pricePeak,  priceNonPeak, cap);
	}
	public void frameCreateBooking(int idToBook) {
		// TODO Auto-generated method stub
		fBookingView = new CreateBookingView(this, idToBook);
		fBookingView.setLocationRelativeTo(null);
		fBookingView.setVisible(true);
		f1.setVisible(false);
	}




	
	
	
}
