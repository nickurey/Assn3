import java.util.ArrayList;

import Basic.Facility;
import Basic.Member;

public class AdminView {


	private Admin admin;
	private AdminController controller;
	private MainMenuView f1;
	private CreatememberView f2;
	private CreateBookingView fBookingView;
	private CreateFacilityView fCreateFacilityView;
	private DeleteMemberView fDelete;
	private ResetMemberPasswordView fResetPlayer;
	//private TopScoreView fTopScore;
	private ResetAdminPasswordView fResetAdmin;
	
	public AdminView(Admin admin, AdminController controller) {

		this.admin = admin;
		this.controller = controller;
		f1 = new MainMenuView(this);
		f1.pack();
		f1.setLocationRelativeTo(null);

	}
	public static void main(String []args){

	}
	
	public void show() {
		f1.setLocationRelativeTo(null);
		f1.setVisible(true);
		controller.saveMemberData();
	}
	
	public void run(){
		f1.setLocationRelativeTo(null);
		f1.setVisible(true);
	}
	public void frameCreateBooking(){
		fBookingView = new CreateBookingView(this);
		fBookingView.setLocationRelativeTo(null);
		fBookingView.setVisible(true);
		f1.setVisible(false);
	}
	
	public void frame1CallBack(){
		f2 = new CreatememberView(this);
		f2.pack();
		f2.setLocationRelativeTo(null);
		f2.setVisible(true);
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
		fResetAdmin = new ResetAdminPasswordView(this);
		fResetAdmin.pack();
		fResetAdmin.setLocationRelativeTo(null);
		fResetAdmin.setVisible(true);
		f1.setVisible(false);
	}

	void createPlayer(String name, String password) throws CreateMemberException {

		controller.createMember(name, password);
		controller.saveMemberData();
	}
	//runAddTransactionSQLrunAddTransactionSQL(Member member, Facility facility, int timeSlot, int paxCount) 
	void createBooking(int facility, int timeSlot, int paxCount){
		controller.createBooking(facility,  timeSlot, paxCount);
	}
	public void deletePlayer(String name) throws InvalidMemberException {
		controller.deleteMember(name);
		controller.saveMemberData();
		
	}
	public void mainMenu() {
		f1.setLocationRelativeTo(null);
		f1.setVisible(true);
		if (fDelete!= null) fDelete.setVisible(false);
		if (f2!= null) f2.setVisible(false);
		if (fResetPlayer!= null) fResetPlayer.setVisible(false);
		if (fCreateFacilityView!= null) fCreateFacilityView.setVisible(false);
		if (fResetAdmin!= null) fResetAdmin.setVisible(false);
		if (fBookingView!= null) fBookingView.setVisible(false);
		
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
	public void createFacility(String name, String facility, int pricePeak, int priceNonPeak, int cap) {
		// TODO Auto-generated method stub
		controller.createFacility(name, facility, pricePeak, priceNonPeak, cap);
		
	}



	
	
	
}
