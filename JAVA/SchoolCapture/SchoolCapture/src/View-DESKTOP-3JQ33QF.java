import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class View extends JPanel {

	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					
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
	public View() {
		this.setSize(800, 600);
		this.setVisible(true);

		DefaultTableModel mod = new DefaultTableModel(TTdata.subjects, TTdata.date) {
			// 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};

		table = new JTable(mod);
		table.setRowHeight(50); // 칸의 세로 크기

		table.setBounds(41, 41, 400, 400); // 표 x,y,표크기 x,y
		table.getTableHeader().setReorderingAllowed(false); // 이동 불가
		table.getTableHeader().setResizingAllowed(true); // 크기 수정 불가

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(40, 40, 400, 375);
		this.add(scrollPane);

	}
}