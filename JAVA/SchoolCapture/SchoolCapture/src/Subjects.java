import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class Subjects extends JPanel {

	File path = new File(".");

	public Subjects() {
		setBackground(Color.DARK_GRAY);
		this.setSize(800, 550);
		this.setVisible(true);
		setLayout(null);

		Loadfile lf = new Loadfile();
		add(lf);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 800, 550);
		add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(153, 204, 255));
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		
		
		//�Ϲ�ȭ
		/*
		 for(int i=0;i<15;i++)
		{
			String subjectname = new String();
			if(!subjectname.equals(""))
			{
				JButton button = new JButton(subjectname);
				button.setFocusPainted(false);
				button.setBorderPainted(false);
				button.setBackground(new Color(220, 220, 220));
				button.setForeground(Color.DARK_GRAY);
				button.setFont(new Font("���� ����", Font.PLAIN, 20));
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ViewPic thumb = new ViewPic(path + "\\UploadedFiles\\" + subjectname, subjectname);
						removeAll();
						add(thumb);
						revalidate();
						repaint();
					}
				});
				try{

					button.setBounds(56+(i%4)*172, 10+(i/4)*130, 160, 120);
					panel.add(button);	
				}
				catch(Exception e)
				{
					
				}
			}
		}

		 */
		
		
		
		JButton korean = new JButton("\uBB38\uD559");
		korean.setFocusPainted(false);
		korean.setBorderPainted(false);
		korean.setBackground(new Color(51, 204, 255));
		korean.setForeground(Color.WHITE);
		korean.setFont(new Font("���� ����", Font.PLAIN, 20));
		
		korean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewPic korean = new ViewPic(path + "\\UploadedFiles\\" + "����", "����");
				removeAll();
				add(korean);
				revalidate();
				repaint();
			}
		});
		korean.setBounds(56, 10, 160, 120);
		panel.add(korean);

		JButton design = new JButton("\uB514\uC77C");
		design.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPic design = new ViewPic(path + "\\UploadedFiles\\" + "����", "����");
				removeAll();
				add(design);
				revalidate();
				repaint();
			}
		});
		design.setFocusPainted(false);
		design.setBorderPainted(false);
		design.setBackground(new Color(51, 204, 255));
		design.setForeground(Color.WHITE);
		design.setFont(new Font("���� ����", Font.PLAIN, 20));
		design.setBounds(228, 10, 160, 120);
		panel.add(design);

		JButton game = new JButton("\uAC9C\uD504");
		game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPic game = new ViewPic(path + "\\UploadedFiles\\" + "����", "����");
				removeAll();
				add(game);
				revalidate();
				repaint();
			}
		});
		game.setFocusPainted(false);
		game.setBorderPainted(false);
		game.setBackground(new Color(51, 204, 255));
		game.setForeground(Color.WHITE);
		game.setFont(new Font("���� ����", Font.PLAIN, 20));
		game.setBounds(400, 10, 160, 120);
		panel.add(game);

		JButton math = new JButton("\uBBF8\uC801");
		math.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPic math = new ViewPic(path + "\\UploadedFiles\\" + "����", "����");
				removeAll();
				add(math);
				revalidate();
				repaint();
			}
		});
		math.setFocusPainted(false);
		math.setBorderPainted(false);
		math.setBackground(new Color(51, 204, 255));
		math.setForeground(Color.WHITE);
		math.setFont(new Font("���� ����", Font.PLAIN, 20));
		math.setBounds(572, 10, 160, 120);
		panel.add(math);

		JButton money = new JButton("\uC0C1\uACBD");
		money.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPic money = new ViewPic(path + "\\UploadedFiles\\" + "���", "���");
				removeAll();
				add(money);
				revalidate();
				repaint();
			}
		});
		money.setFocusPainted(false);
		money.setBorderPainted(false);
		money.setBackground(new Color(51, 204, 255));
		money.setForeground(Color.WHITE);
		money.setFont(new Font("���� ����", Font.PLAIN, 20));
		money.setBounds(56, 140, 160, 120);
		panel.add(money);

		JButton english = new JButton("\uC601\uC5B4");
		english.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPic english = new ViewPic(path + "\\UploadedFiles\\" + "����", "����");
				removeAll();
				add(english);
				revalidate();
				repaint();
			}
		});
		english.setFocusPainted(false);
		english.setBorderPainted(false);
		english.setBackground(new Color(51, 204, 255));
		english.setForeground(Color.WHITE);
		english.setFont(new Font("���� ����", Font.PLAIN, 20));
		english.setBounds(228, 140, 160, 120);
		panel.add(english);

		JButton web1 = new JButton("\uC6F9\uD5041");
		web1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPic web1 = new ViewPic(path + "\\UploadedFiles\\" + "����1", "����1");
				removeAll();
				add(web1);
				revalidate();
				repaint();
			}
		});
		web1.setFocusPainted(false);
		web1.setBorderPainted(false);
		web1.setBackground(new Color(51, 204, 255));
		web1.setForeground(Color.WHITE);
		web1.setFont(new Font("���� ����", Font.PLAIN, 20));
		web1.setBounds(400, 140, 160, 120);
		panel.add(web1);

		JButton web2 = new JButton("\uC6F9\uD5042");
		web2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPic web2 = new ViewPic(path + "\\UploadedFiles\\" + "����2", "����2");
				removeAll();
				add(web2);
				revalidate();
				repaint();
			}
		});
		web2.setFocusPainted(false);
		web2.setBorderPainted(false);
		web2.setBackground(new Color(51, 204, 255));
		web2.setForeground(Color.WHITE);
		web2.setFont(new Font("���� ����", Font.PLAIN, 20));
		web2.setBounds(572, 140, 160, 120);
		panel.add(web2);

		JButton music = new JButton("\uC74C\uC545");
		music.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPic music = new ViewPic(path + "\\UploadedFiles\\" + "����", "����");
				removeAll();
				add(music);
				revalidate();
				repaint();
			}
		});
		music.setFocusPainted(false);
		music.setBorderPainted(false);
		music.setBackground(new Color(51, 204, 255));
		music.setForeground(Color.WHITE);
		music.setFont(new Font("���� ����", Font.PLAIN, 20));
		music.setBounds(56, 270, 160, 120);
		panel.add(music);

		JButton japan = new JButton("\uC77C\uBCF8\uC5B4");
		japan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPic japan = new ViewPic(path + "\\UploadedFiles\\" + "�Ϻ���", "�Ϻ���");
				removeAll();
				add(japan);
				revalidate();
				repaint();
			}
		});
		japan.setFocusPainted(false);
		japan.setBorderPainted(false);
		japan.setBackground(new Color(51, 204, 255));
		japan.setForeground(Color.WHITE);
		japan.setFont(new Font("���� ����", Font.PLAIN, 20));
		japan.setBounds(228, 270, 160, 120);
		panel.add(japan);

		JButton JAVA = new JButton("\uC790\uBC14");
		JAVA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPic JAVA = new ViewPic(path + "\\UploadedFiles\\" + "�ڹ�", "�ڹ�");
				removeAll();
				add(JAVA);
				revalidate();
				repaint();
			}
		});
		JAVA.setFocusPainted(false);
		JAVA.setBorderPainted(false);
		JAVA.setBackground(new Color(51, 204, 255));
		JAVA.setForeground(Color.WHITE);
		JAVA.setFont(new Font("���� ����", Font.PLAIN, 20));
		JAVA.setBounds(400, 270, 160, 120);
		panel.add(JAVA);

		JButton loot = new JButton("\uC9C4\uB85C");
		loot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPic loot = new ViewPic(path + "\\UploadedFiles\\" + "����", "����");
				removeAll();
				add(loot);
				revalidate();
				repaint();
			}
		});
		loot.setFocusPainted(false);
		loot.setBorderPainted(false);
		loot.setBackground(new Color(51, 204, 255));
		loot.setForeground(Color.WHITE);
		loot.setFont(new Font("���� ����", Font.PLAIN, 20));
		loot.setBounds(572, 270, 160, 120);
		panel.add(loot);

		JButton chemi = new JButton("\uD654\uD559");
		chemi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewPic chemi = new ViewPic(path + "\\UploadedFiles\\" + "ȭ��", "ȭ��");
				removeAll();
				add(chemi);
				revalidate();
				repaint();
			}
		});
		chemi.setFocusPainted(false);
		chemi.setBorderPainted(false);
		chemi.setBackground(new Color(51, 204, 255));
		chemi.setForeground(Color.WHITE);
		chemi.setFont(new Font("���� ����", Font.PLAIN, 20));
		chemi.setBounds(56, 400, 160, 120);
		panel.add(chemi);

	}
}
