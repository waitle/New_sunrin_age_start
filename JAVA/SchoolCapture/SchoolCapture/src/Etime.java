import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class Etime extends JPanel {
	private JTable table;
	private JTextField currentlessoncount;
	private JTextField txtYyyymd;
	String [] inputstring;

	/**
	 * Create the panel.
	 */
	public Etime() {
		setBackground(Color.DARK_GRAY);
		this.setSize(800, 600);
		this.setVisible(true);
		setLayout(null);
		
		Loadfile lf = new Loadfile();
		add(lf);
		
		//시간표
		DefaultTableModel model = new DefaultTableModel(TTdata.subjects, TTdata.date) { // 셀 수정
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		table = new JTable(model);
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		table.setRowHeight(55); // 칸의 세로 크기
		table.getTableHeader().setReorderingAllowed(false); // 이동 불가
		table.getTableHeader().setResizingAllowed(false); // 크기 수정 불가

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(150, 25, 500, 273);
		this.add(scrollPane);

		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		 
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		 
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcmSchedule = table.getColumnModel();
		 
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
		tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		//스태틱 선택
		JRadioButton static_status = new JRadioButton("static");
		static_status.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		static_status.setForeground(Color.WHITE);
		static_status.setBackground(Color.DARK_GRAY);
		static_status.setSelected(true);
		static_status.setBounds(150, 320, 130, 30);
		this.add(static_status);

		//인스턴트 선택
		JRadioButton instant_status = new JRadioButton("instant");
		instant_status.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		instant_status.setForeground(Color.WHITE);
		instant_status.setBackground(Color.DARK_GRAY);
		instant_status.setBounds(450, 320, 130, 30);
		this.add(instant_status);

		//수업시간 선택라벨
		JLabel time = new JLabel("\uC218\uC5C5\uC2DC\uAC04");
		time.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		time.setForeground(Color.WHITE);
		time.setBounds(150, 400, 80, 30);
		this.add(time);

		//40분 선택
		JRadioButton R40 = new JRadioButton("40\uBD84");
		R40.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		R40.setForeground(Color.WHITE);
		R40.setBackground(Color.DARK_GRAY);
		R40.setBounds(238, 400, 62, 30);
		this.add(R40);

		//45분 선택
		JRadioButton R45 = new JRadioButton("45\uBD84");
		R45.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		R45.setForeground(Color.WHITE);
		R45.setBackground(Color.DARK_GRAY);
		R45.setBounds(296, 400, 62, 30);
		this.add(R45);

		//50분 선택
		JRadioButton R50 = new JRadioButton("50\uBD84");
		R50.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		R50.setForeground(Color.WHITE);
		R50.setBackground(Color.DARK_GRAY);
		R50.setSelected(true);
		R50.setBounds(362, 400, 62, 30);
		this.add(R50);

		ButtonGroup choose = new ButtonGroup();
		choose.add(static_status);
		choose.add(instant_status);

		ButtonGroup group = new ButtonGroup();
		group.add(R40);
		group.add(R45);
		group.add(R50);

		//수업시수
		currentlessoncount = new JTextField();
		currentlessoncount.setForeground(Color.DARK_GRAY);
		currentlessoncount.setText(TTdata.getlessoncount());//미리셋팅
		currentlessoncount.setBounds(260, 437, 120, 21);
		currentlessoncount.setEditable(false);
		this.add(currentlessoncount);
		currentlessoncount.setColumns(10);
		

		//수업시수 라벨
		JLabel InputLessonCount = new JLabel("\uC218\uC5C5\uC2DC\uC218");
		InputLessonCount.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		InputLessonCount.setForeground(Color.WHITE);
		InputLessonCount.setBounds(150, 440, 57, 15);
		add(InputLessonCount);
		
		//확인버튼
		JButton button = new JButton("확인");
		button.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setForeground(new Color(20, 20, 20));
		button.setBackground(new Color(240, 240, 240));
		button.setBounds(530, 480, 100, 30);
		this.add(button);

		//인스턴트되는 날짜라벨
		JLabel Editdate = new JLabel("\uC218\uC815\uD560 \uB0A0\uC9DC");
		Editdate.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		Editdate.setForeground(Color.WHITE);
		Editdate.setBounds(150, 375, 80, 15);
		add(Editdate);

		//인스턴트되는 날짜
		txtYyyymd = new JTextField();
		txtYyyymd.setForeground(Color.DARK_GRAY);
		txtYyyymd.setBounds(260, 372, 116, 21);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();//현재날짜로 미리셋팅
		String strToday = sdf.format(c1.getTime());
		txtYyyymd.setText(strToday);
		if(static_status.isSelected())
			txtYyyymd.setEditable(false);
		add(txtYyyymd);
		txtYyyymd.setColumns(10);

		// 확인버튼을 눌렀을때
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (static_status.isSelected())// 스태틱 모드일경우
				{
					// 수업시간 저장
					if (R40.isSelected()) {
						TTdata.runtime = 40;
					} else if (R45.isSelected()) {
						TTdata.runtime = 45;
					} else {
						TTdata.runtime = 50;
					}
					// 레슨카운트 저장
					TTdata.runtime = Integer.parseInt(currentlessoncount.getText());

				}
				else if (instant_status.isSelected())// 인스턴트 모드일경우
				{
					System.out.println("static date added");
					TTdataException temp = new TTdataException();
					// 수업시간 복사
					if (R40.isSelected()) {
						temp.runtime = 40;
					} else if (R45.isSelected()) {
						temp.runtime = 45;
					} else {
						temp.runtime = 50;
					}
					// 레슨카운트 복사
					temp.ExceptedDATE = txtYyyymd.getText();
					System.out.println("put date " + temp.ExceptedDATE);
					temp.lessoncount = Integer.parseInt(currentlessoncount.getText());
					for(int i=0;i<TTdataException.ekscnr.size();i++)//이미 추가되어있는 날짜는 삭제
					{
						if(TTdataException.ekscnr.get(i).ExceptedDATE.equals(temp.ExceptedDATE))
						{
							TTdataException.ekscnr.remove(i);
							System.out.println("exception overlaped");
						}
					}
					
					TTdataException.ekscnr.add(temp);//단축데이터에 추가
				}
			}
		});
		static_status.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(static_status.isSelected())
				{
					System.out.println("mouse clicked and set mod as static");
					txtYyyymd.setEditable(false);
					remove(txtYyyymd);
					add(txtYyyymd);
					repaint();		
				}
			}
		});
		instant_status.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(instant_status.isSelected())
				{
					System.out.println("mouse clicked and set mod as instant");
					remove(txtYyyymd);
					txtYyyymd.setEditable(true);
					add(txtYyyymd);
					repaint();	
				}
			}
		});
	}

}