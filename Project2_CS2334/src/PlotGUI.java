import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;


public class PlotGUI extends JFrame {
	
	public PlotGUI(){
		
	}
	
	private class PublicationTypePanel extends JPanel{
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
		}
		
	}
	
	private class PublicationPerYear extends JPanel{
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
		}
		
	}
	
	private class ConferencePaperPerYearPanel extends JPanel{
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
		}
		
	}
	
	private class JournalArticlesPerYearPanel extends JPanel{
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
		}
		
	}
	
	private class CoAuthorsPerPublicationPanel extends JPanel{
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
		}
		
	}
}
