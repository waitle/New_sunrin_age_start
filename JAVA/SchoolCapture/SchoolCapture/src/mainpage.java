import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class mainpage extends JFrame{

	
	private JPanel contentPane;
	public Subjects subject = null;//과목을 보여주는 패널
	public View timetable = null;//시간표를 보여주는 패널
	public Esub sSet = null;//과목을 설정하는 패널
	public Etime tSet = null;//시간표를 설정하는 패널

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainpage frame = new mainpage();
					Loadfile lf = new Loadfile();
					frame.getContentPane().add(lf);//드래그앤 드롭 활성화
					
					frame.timetable = new View();
					frame.tSet = new Etime();
					frame.subject = new Subjects();
					frame.sSet = new Esub();
					
					frame.getContentPane();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the frame.
	 */
	public mainpage() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("./"));
		setTitle("School Capture(Beta)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		JMenuBar menuBar = new JMenuBar();//메뉴 바 추가
		setJMenuBar(menuBar);
		
		JMenuItem [] mainItems = new JMenuItem[2];//메뉴바 아이템들 미리추가
		String [] mainTitles = {"Subjects", "Timetable"};
		JMenuItem [] settingsItems = new JMenuItem[2];
		String [] settingsTitles = {"Subjects Setting", "Timetable Setting"};
		
		JMenu main = new JMenu("Main");//메인 메뉴추가
		menuBar.add(main);
		
		MenuActionListener listener = new MenuActionListener();//메뉴액션
		for(int i=0; i<mainItems.length; i++){
			mainItems[i] = new JMenuItem(mainTitles[i]);
			mainItems[i].addActionListener(listener);//메뉴아이템에 리스너 링크
			main.add(mainItems[i]);//메뉴에 메뉴아이템 추가
		}
		JMenu settings = new JMenu("Settings");//세팅메뉴 추가
		menuBar.add(settings);
		
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
				change("SSet");
				break;
			case "Timetable Setting"://Settings-Timetable settings
				change("TSet");
				break;
			}
				

		}
		
	}
	
	public void change(String panelName) {
		if(panelName.equals("Subjects")){
			getContentPane().removeAll();
			getContentPane().add(subject);
			revalidate();
			repaint();
		}
		if(panelName.equals("Timetable")){
			contentPane.removeAll();
			contentPane.add(timetable);
			revalidate();
			repaint();
		}
		else if(panelName.equals("SSet")){
			getContentPane().removeAll();
			getContentPane().add(sSet);
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
