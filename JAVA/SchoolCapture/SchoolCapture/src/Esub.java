import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Esub extends JPanel {
	private JTextField name;
	private JTextField teacher;
	private JTextField email;
	private JTextField phone;
	private JTable table;
	static String[][] subjects = new String[15][4];// 표 상으로 보여주기 위한 이차원 배열
	Vector<String> date = new Vector<String>(4);
	String[]raw = new String[4];
	Vector<String []> data = new Vector<String []>(15);
	final static String[] cal = { "과목", "선생님", "이메일", "연락처" };

	/**
	 * Create the this.
	 */
	public Esub() {
		setBackground(Color.DARK_GRAY);
		this.setSize(800, 550);
		this.setVisible(true);
		setLayout(null);

		name = new JTextField();
		name.setEditable(false);
		name.setColumns(10);
		name.setBounds(332, 352, 116, 21);
		this.add(name);

		teacher = new JTextField();
		teacher.setEditable(false);
		teacher.setColumns(10);
		teacher.setBounds(332, 383, 116, 21);
		this.add(teacher);

		email = new JTextField();
		email.setEditable(false);
		email.setColumns(10);
		email.setBounds(332, 414, 116, 21);
		this.add(email);

		phone = new JTextField();
		phone.setEditable(false);
		phone.setColumns(10);
		phone.setBounds(332, 445, 116, 21);
		this.add(phone);

		JLabel Lname = new JLabel("\uACFC\uBAA9\uBA85");
		Lname.setForeground(Color.WHITE);
		Lname.setBounds(243, 355, 57, 15);
		this.add(Lname);

		JLabel Lteacher = new JLabel("\uC120\uC0DD\uB2D8 \uC131\uD568");
		Lteacher.setForeground(Color.WHITE);
		Lteacher.setBounds(243, 386, 69, 15);
		this.add(Lteacher);

		JLabel Lemail = new JLabel("\uC774\uBA54\uC77C");
		Lemail.setForeground(Color.WHITE);
		Lemail.setBounds(243, 417, 57, 15);
		this.add(Lemail);

		JLabel Lphone = new JLabel("\uC5F0\uB77D\uCC98");
		Lphone.setForeground(Color.WHITE);
		Lphone.setBounds(243, 448, 57, 15);
		this.add(Lphone);

		// 삭제버튼
		JButton delete = new JButton("\uC0AD\uC81C");
		delete.setEnabled(false);
		delete.setForeground(Color.DARK_GRAY);
		delete.setBackground(Color.LIGHT_GRAY);
		delete.setBounds(505, 505, 97, 23);
		this.add(delete);

		// 확인버튼
		JButton confirm = new JButton("\uD655\uC778");
		confirm.setEnabled(false);
		confirm.setForeground(Color.DARK_GRAY);
		confirm.setBackground(Color.LIGHT_GRAY);
		confirm.setBounds(614, 505, 97, 23);
		this.add(confirm);

		
		
		DefaultTableModel mod = new DefaultTableModel(data, date) { // 셀 수정
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		date.add("과목명");
		date.add("선생님");
		date.add("이메일");
		date.add("연락처");
		// 시간표
		table = new JTable(mod);
		table.setModel(mod);
		table.setBounds(1, 201, 669, 36);
		table.getTableHeader().setReorderingAllowed(false); // 이동 불가
		table.getTableHeader().setResizingAllowed(true); // 크기 수정 불가
		table.setRowHeight(55); // 칸의 세로 크기

		JScrollPane scrollPaneR = new JScrollPane(table);
		scrollPaneR.setBounds(59, 35, 671, 273);
		add(scrollPaneR);

		Loadfile lf = new Loadfile();
		add(lf);

		// 확인버튼을 눌렀을때
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				raw[0]=name.getText();// 필드의 내용들을 요소에 추가
				raw[1]=teacher.getText();
				raw[2]=email.getText();
				raw[3]=phone.getText();
				data.add(raw);
				
				name.setText("");
				teacher.setText("");
				email.setText("");
				phone.setText("");
				
				mod.fireTableDataChanged();
			}
			
		});

		// 취소버튼을 눌렀을때
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name.setText("");
				teacher.setText("");
				email.setText("");
				phone.setText("");

				mod.fireTableDataChanged();
			}
		});
		// 시간표 줄을 클릭했을때
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				if (row >= 0 && col >= 0) {
					name.setText(table.getValueAt(row, 0).toString());
					teacher.setText(table.getValueAt(row, 1).toString());
					email.setText(table.getValueAt(row, 2).toString());
					phone.setText(table.getValueAt(row, 3).toString());

				}
			}
		});

	}
}
