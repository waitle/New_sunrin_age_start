import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

@SuppressWarnings("serial")
public class View extends JPanel {
	private JTable table;
	
	public View() {
		setBackground(Color.DARK_GRAY);
		this.setSize(800, 550);
		this.setVisible(true);
		setLayout(null);
		
		JButton refresh = new JButton("\uC0C8\uB85C\uACE0\uCE68");
		refresh.setFocusPainted(false);
		refresh.setBorderPainted(false);
		refresh.setBackground(new Color(220, 220, 220));
		refresh.setBounds(630, 480, 97, 23);
		add(refresh);
		
		Loadfile lf = new Loadfile();
		lf.setBounds(0, 0, 800, 550);
		add(lf);
				
		DefaultTableModel mod = new DefaultTableModel(TTdata.subjects, TTdata.date) { // �� ����
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		table = new JTable(mod);
		table.setFont(new Font("���� ����", Font.PLAIN, 15));
		table.setModel(mod);
		table.setRowHeight(55); // ĭ�� ���� ũ��
		table.getTableHeader().setReorderingAllowed(false); // �̵� �Ұ�
		table.getTableHeader().setResizingAllowed(false); // ũ�� ���� �Ұ�
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(150, 25, 500, 410);
		this.add(scrollPane);
		
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		 
		// DefaultTableCellHeaderRenderer�� ������ ��� ���ķ� ����
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		 
		// ������ ���̺��� ColumnModel�� ������
		TableColumnModel tcmSchedule = table.getColumnModel();
		 
		// �ݺ����� �̿��Ͽ� ���̺��� ��� ���ķ� ����
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
		tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mod.fireTableDataChanged();
			}
		});
	}

}