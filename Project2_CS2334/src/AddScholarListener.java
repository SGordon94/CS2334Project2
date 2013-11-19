import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddScholarListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		AddScholarView addScholarView = new AddScholarView();
		addScholarView.showAddScholarView();
		
	}

}
