import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class mainpage extends JFrame{

	
	private JPanel contentPane;
	public Subjects subject = null;//������ �����ִ� �г�
	public View timetable = null;//�ð�ǥ�� �����ִ� �г�
	public Esub sSet = null;//������ �����ϴ� �г�
	public Etime tSet = null;//�ð�ǥ�� �����ϴ� �г�

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainpage frame = new mainpage();
					Loadfile lf = new Loadfile();
					Subjects EE = new Subjects();
					frame.getContentPane().add(lf);//�巡�׾� ��� Ȱ��ȭ
					
					frame.timetable = new View();
					frame.tSet = new Etime();
					frame.subject = new Subjects();
					frame.sSet = new Esub();
					frame.getContentPane().add(EE);
					
					frame.getContentPane();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainpage() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(mainpage.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		setTitle("School Capture(Release)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();//�޴� �� �߰�
		setJMenuBar(menuBar);
		
		JMenuItem [] mainItems = new JMenuItem[2];//�޴��� �����۵� �̸��߰�
		String [] mainTitles = {"Subjects", "Timetable"};
		JMenuItem [] settingsItems = new JMenuItem[3];
		String [] settingsTitles = {"Subjects Setting", "Timetable Setting", "Easter Egg"};
		
		JMenu main = new JMenu("Main");//���� �޴��߰�
		JMenu settings = new JMenu("Settings");//���ø޴� �߰�
		menuBar.add(main);
		menuBar.add(settings);

		MenuActionListener listener = new MenuActionListener();//�޴��׼�
		for(int i=0; i<mainItems.length; i++){
			mainItems[i] = new JMenuItem(mainTitles[i]);
			mainItems[i].addActionListener(listener);//�޴������ۿ� ������ ��ũ
			main.add(mainItems[i]);//�޴��� �޴������� �߰�
		}
		
		for(int i=0; i<settingsItems.length; i++){
			settingsItems[i] = new JMenuItem(settingsTitles[i]);
			settingsItems[i].addActionListener(listener);
			settings.add(settingsItems[i]);
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
	}
	public class MenuActionListener extends JFrame implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String tmp = e.getActionCommand();
			
			switch(tmp){
			case "Subjects" : //Main-subjects
				change("Subjects");
				break;
			case "Timetable" : //Main-Timetable
				change("Timetable");
				break;
			case "Subjects Setting"://settings-Subject settings
				JOptionPane.showMessageDialog(null, "�� ����� ���� �����߿� �ֽ��ϴ�");
				//change("SSet"); // update needed
				break;
			case "Timetable Setting"://Settings-Timetable settings
				change("TSet");
				break;
			case "Easter Egg":
				JOptionPane.showMessageDialog(null, "���α׷� ������� \n 2018.11.14 24:00");
			}
				

		}
		
	}
	
	public void change(String panelName) {
		if(panelName.equals("Subjects")){
			contentPane.removeAll();
			contentPane.add(subject);
			revalidate();
			repaint();
		}
		else if(panelName.equals("Timetable")){
			contentPane.removeAll();
			contentPane.add(timetable);
			revalidate();
			repaint();
		}
		else if(panelName.equals("SSet")){
			contentPane.removeAll();
			contentPane.add(sSet);
			revalidate();
			repaint();
		}
		else if(panelName.equals("TSet")){
			contentPane.removeAll();
			contentPane.add(tSet);
			revalidate();
			repaint();
		}
		else{
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
		}
	}
	
	
}