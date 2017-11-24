import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class View extends JPanel {
	private JTable table;
	
	public View() {
		setBackground(Color.DARK_GRAY);
		this.setSize(800, 550);
		this.setVisible(true);
		setLayout(null);
		
		JButton refresh = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		refresh.setBackground(new Color(220, 220, 220));
		refresh.setBounds(630, 480, 97, 23);
		add(refresh);
		
		Loadfile lf = new Loadfile();
		lf.setBounds(0, 0, 800, 550);
		add(lf);
				
		DefaultTableModel mod = new DefaultTableModel(TTdata.subjects, TTdata.date) { // 셀 수정
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		table = new JTable(mod);
		table.setModel(mod);
		table.setRowHeight(55); // 칸의 세로 크기
		table.getTableHeader().setReorderingAllowed(false); // 이동 불가
		table.getTableHeader().setResizingAllowed(false); // 크기 수정 불가

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(150, 25, 500, 410);
		this.add(scrollPane);
		
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mod.fireTableDataChanged();
			}
		});
	}

}
