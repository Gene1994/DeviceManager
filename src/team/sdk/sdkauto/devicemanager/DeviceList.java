package team.sdk.sdkauto.devicemanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.sun.jna.NativeLong;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.miginfocom.swing.MigLayout;
import team.sdk.sdkauto.UI.StartUI;
import team.sdk.sdkauto.bean.Device;
import team.sdk.sdkauto.util.JdbcUtil;

/**
 * <p>
 * Title: DeviceList<��p>
 * <p>
 * Description: <��p>
 * 
 * @author quzhe
 * 
 *         2017��11��20��
 */
public class DeviceList {
	/**
	 * 
	 */
	private static final long serialVersionUID = -311019214878889971L;

	// rowData�������������
	// columnNames�������
	Vector<String> columnNames;
	Vector rowData = new Vector();

	// �豸����
	public String deviceId;
	public String type;
	public String model;
	public String ip;
	public int port;
	public String userName;
	public String password;
	public String language;
	public String comment;
	public String position;
	public boolean status;

	private static JFrame frame = new JFrame();// ����ģʽ������ģʽ
	private static JPanel panel_center;// ����ģʽ������ģʽ
	private JPanel panel_north;
	private JTable jt = null;
	private JTable jt_checked = null; // �������ɸѡ��ı��
	private JScrollPane jsp = null;
	private JButton btn_check;
	private JButton btnExcel;
	private JLabel label;
	private JComboBox<Object> comboBox;
	private static boolean ONLINE_FLAG;
	private DefaultTableModel tableModel;
	private DefaultTableModel tableModel_checked;
	private TableRowSorter<DefaultTableModel> sorter;
	private JdbcUtil jdbcUtil = new JdbcUtil();

	String index = null;
	String sql = null;
	public List<Map<String, Object>> mapList = null;

	int index_1;

	public DeviceList(String s) {
		frame.setBounds(100, 347, 1550, 410);
		// frame.setSize(1500, 300);
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage(DeviceList.class.getResource("/resources/device_icon.png")));
		frame.setTitle("\u8BBE\u5907\u5217\u8868");
		frame.setLayout(new BorderLayout(0, 0));

		ONLINE_FLAG = true;
		columnNames = new Vector<String>();
		// ��������
		columnNames.add("״̬");
		columnNames.add("�豸ID");
		columnNames.add("�豸����");
		columnNames.add("�豸�ͺ�");
		columnNames.add("IP");
		columnNames.add("�˿ں�");
		columnNames.add("�û���");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("λ��");
		columnNames.add("��ע");
		columnNames.add("");// �޸��豸��Ϣ��ť(update)
		columnNames.add("");// ɾ���豸��Ϣ��ť(delete)

		panel_north = new JPanel();
		frame.add(panel_north, BorderLayout.NORTH);
		panel_north.setLayout(new MigLayout("", "[62px][62px][57px][136px][136px][][]", "[23px]"));

		label = new JLabel("\u5728\u7EBF\u72B6\u6001\uFF1A");
		panel_north.add(label, "cell 0 0,grow");

		comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] { "\u5728\u7EBF", "\u4E0D\u5728\u7EBF" }));
		panel_north.add(comboBox, "cell 1 0,alignx left,growy");
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (comboBox.getSelectedIndex() == 0) {
					ONLINE_FLAG = true;
				} else if (comboBox.getSelectedIndex() == 1) {
					ONLINE_FLAG = false;
				}
			}
		});

		btn_check = new JButton("\u7B5B\u9009");
		panel_north.add(btn_check, "cell 2 0,alignx left,aligny top");
		btn_check.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jt.setVisible(false);
				sorter = new TableRowSorter<DefaultTableModel>(tableModel);
				if (ONLINE_FLAG) {
					sorter.setRowFilter(RowFilter.regexFilter("#ONLINE#"));
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("#OFFLINE#"));
				}
				jt.setRowSorter(sorter); // ΪJTable����������

				int jR = jt.getRowCount();
				int jC = jt.getColumnCount();
				Object[][] obj = new Object[jR][jC];
				for (int ir = 0; ir < jR; ir++) {
					for (int ic = 0; ic < jC - 2; ic++) {
						obj[ir][ic] = jt.getValueAt(ir, ic);
					}
				}
				String[] name = { "״̬", "�豸ID", "�豸����", "�豸�ͺ�", "IP", "�˿ں�", "�û���", "����", "����", "λ��", "��ע" };
				tableModel_checked = new DefaultTableModel(obj, name);
				jt_checked = new JTable(tableModel_checked);

				// ���ñ����Ⱦ��
				jt_checked.getColumnModel().getColumn(0).setCellRenderer(new MyRenderStatus());

				// ��ʼ�� jsp
				jt_checked.setVisible(true);
				jsp.setViewportView(jt_checked);
			}
		});

		btnExcel = new JButton("\u5BFC\u51FAExcel");
		panel_north.add(btnExcel, "cell 4 0,growx,aligny top");
		btnExcel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String excel_Url = "..\\SDKAuto\\Generate Files\\Excel\\Device_" + System.currentTimeMillis() + ".xls";
				File file = new File(excel_Url);
				try {
					if (jt_checked != null) {
						exportTable(jt_checked, file);
					} else {
						exportTable(jt, file);
					}
					JOptionPane.showMessageDialog(null, "�����ɹ�");
				} catch (IOException e1) {
				}
			}
		});
		// ������������list
		List<Object> paramList = new ArrayList<Object>();
		switch (DeviceSelect.index) {
		// byType
		case 1:
			index = s;
			sql = "select * from device where type = ?";
			// ������
			paramList.add(index);
			break;
		// byModel
		case 2:
			// ģ������
			index = "%" + s + "%";
			sql = "select * from device where model like ?";
			// ������
			paramList.add(index);
			break;
		// byIp
		case 3:
			index = s;
			sql = "select * from device where ip = ?";
			// ������
			paramList.add(index);
			break;
		// byComment
		case 4:
			// ģ������
			index = "%" + s + "%";
			sql = "select * from device where comment like ?";
			// ������
			paramList.add(index);
			break;
		// selectAll
		case 5:
			sql = "select * from device";
			break;
		}

		try {
			jdbcUtil.getConnection(); // ��ȡ���ݿ�����
			mapList = jdbcUtil.findResult(sql.toString(), paramList);
		} catch (SQLException exception) {
			System.out.println(this.getClass() + "ִ�в�ѯ�����׳��쳣��");
			exception.printStackTrace();
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn();
			}
		}
	}

	public void show() {
		for (Device d : DeviceSelect.vectors_device) {
			rowData.add(d.getHang());
		}
		tableModel = new DefaultTableModel(rowData, columnNames);
		jt = new JTable(tableModel);
		jt.setPreferredScrollableViewportSize(new Dimension(1500, 300));
		
		// �޸�
		MyEvent eventModify = new MyEvent() {
			@Override
			public void invoke(ActionEvent e) {
				MyButton button = (MyButton) e.getSource();
				int row = button.getRow();
				deviceId = (String) jt.getValueAt(row, 1);
				type = (String) jt.getValueAt(row, 2);
				model = (String) jt.getValueAt(row, 3);
				ip = (String) jt.getValueAt(row, 4);
				port = (int) jt.getValueAt(row, 5);
				userName = (String) jt.getValueAt(row, 6);
				password = (String) jt.getValueAt(row, 7);
				language = (String) jt.getValueAt(row, 8);
				position = (String) jt.getValueAt(row, 9);
				comment = (String) jt.getValueAt(row, 10);

				String sql = "update device set deviceid=?,type=?,model=?,ip=?,port=?,username=?,password=?,language=?,position=?,comment=? where deviceid=?";
				// ������������list
				List<Object> paramList = new ArrayList<Object>();
				// ������
				paramList.add(deviceId);
				paramList.add(type);
				paramList.add(model);
				paramList.add(ip);
				paramList.add(port);
				paramList.add(userName);
				paramList.add(password);
				paramList.add(language);
				paramList.add(position);
				paramList.add(comment);
				paramList.add(deviceId);

				boolean bool = false;
				try {
					jdbcUtil.getConnection(); // ��ȡ���ݿ�����
					bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
				} catch (SQLException e1) {
					System.out.println(this.getClass() + "ִ���޸Ĳ����׳��쳣��");
					e1.printStackTrace();
				} finally {
					if (jdbcUtil != null) {
						jdbcUtil.releaseConn();
					}
				}
				System.out.println("ִ���޸ĵĽ����" + bool);
				if (bool) {
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				} else {
					JOptionPane.showMessageDialog(null, "�޸�ʧ��", "����", JOptionPane.ERROR_MESSAGE);
				}
			}

		};

		// ɾ��
		MyEvent eventDelete = new MyEvent() {
			@Override
			public void invoke(ActionEvent e) {
				MyButton button = (MyButton) e.getSource();
				int row = button.getRow();
				deviceId = (String) jt.getValueAt(row, 1);

				String sql = "delete from device where deviceid=?";
				// ������������list
				List<Object> paramList = new ArrayList<Object>();
				// ������
				paramList.add(deviceId);

				boolean bool = false;
				try {
					jdbcUtil.getConnection(); // ��ȡ���ݿ�����
					bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
				} catch (SQLException exception) {
					System.out.println(this.getClass() + "ִ��ɾ�������׳��쳣��");
					exception.printStackTrace();
				} finally {
					if (jdbcUtil != null) {
						jdbcUtil.releaseConn();
					}
				}
				System.out.println("ִ��ɾ���Ľ����" + bool);
				if (bool) {
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				} else {
					JOptionPane.showMessageDialog(null, "ɾ��ʧ��", "����", JOptionPane.ERROR_MESSAGE);
				}
			}

		};



		// ���ñ����Ⱦ��
		jt.getColumnModel().getColumn(0).setCellRenderer(new MyRenderStatus());
		// ���ñ����Ⱦ��
		jt.getColumnModel().getColumn(11).setCellRenderer(new MyRenderModify());
		// ���ñ��ı༭��
		jt.getColumnModel().getColumn(11).setCellEditor(new MyRenderModify(eventModify));

		// ���ñ����Ⱦ��
		jt.getColumnModel().getColumn(12).setCellRenderer(new MyRenderDelete());
		// ���ñ��ı༭��
		jt.getColumnModel().getColumn(12).setCellEditor(new MyRenderDelete(eventDelete));

		// ��ʼ�� jsp
		jsp = new JScrollPane(jt);
		// jsp.setSize(1400, 300);
		jsp.setViewportView(jt);
		// ����ģʽ �ӳټ���
		if (panel_center == null) {
			panel_center = new JPanel();
		}
		panel_center.removeAll();
		// ��jsp���뵽jpanel
		panel_center.add(jsp, BorderLayout.CENTER);
		panel_center.repaint();
		// this.setSize(1500, 300);
		frame.add(panel_center);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}

	class MyRenderModify extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

		private MyButton button;

		private MyEvent event;

		public MyRenderModify() {
			button = new MyButton("�޸�");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int res = JOptionPane.showConfirmDialog(null, "ȷ���޸ĸ��豸��", "�Ƿ����", JOptionPane.YES_NO_OPTION);
					if (res == JOptionPane.YES_OPTION) {
						event.invoke(e);
					} else {
						return;
					}
				}
			});
		}

		public MyRenderModify(MyEvent e) {
			this();
			this.event = e;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			// ��Ԫ����ʾ
			// button.setToolTipText("");
			// ����ɫ
			button.setBackground(Color.BLACK);
			// ǰ��ɫ
			button.setForeground(Color.green);
			return button;
		}

		/*
		 * ��д�༭������������һ����ť��JTable
		 */
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// setClickCountToStart(1);
			// �����������İ�ť���ڵ��к��зŽ�button����

			button.setRow(row);
			button.setColumn(column);
			// ����ɫ
			button.setBackground(Color.BLACK);
			// ǰ��ɫ
			button.setForeground(Color.green);
			return button;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.CellEditor#getCellEditorValue()
		 */
		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	class MyRenderDelete extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
		private MyButton button;
		private MyEvent event;

		public MyRenderDelete() {
			button = new MyButton("ɾ��");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int res = JOptionPane.showConfirmDialog(null, "ȷ��ɾ�����豸��", "�Ƿ����", JOptionPane.YES_NO_OPTION);
					if (res == JOptionPane.YES_OPTION) {
						event.invoke(e);
					} else {
						return;
					}
				}
			});
		}

		public MyRenderDelete(MyEvent e) {
			this();
			this.event = e;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			// ��Ԫ����ʾ
			// button.setToolTipText("");
			// ����ɫ
			button.setBackground(Color.BLACK);
			// ǰ��ɫ
			button.setForeground(Color.red);
			return button;
		}

		/*
		 * ��д�༭������������һ����ť��JTable
		 */
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// setClickCountToStart(1);
			// �����������İ�ť���ڵ��к��зŽ�button����
			button.setRow(row);
			button.setColumn(column);
			// ����ɫ
			button.setBackground(Color.BLACK);
			// ǰ��ɫ
			button.setForeground(Color.red);
			return button;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see javax.swing.CellEditor#getCellEditorValue()
		 */
		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	class MyRenderStatus implements TableCellRenderer {
		private JLabel jl;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// TODO Auto-generated method stub
			if (value.toString().equals("#ONLINE#")) {
				jl = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage(StartUI.class.getResource("/resources/online.jpg"))));
			} else {
				jl = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit()
						.getImage(StartUI.class.getResource("/resources/offline.jpg"))));
			}
			return jl;
		}
	}

	class MyButton extends JButton {
		private int row;
		private int column;

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getColumn() {
			return column;
		}

		public void setColumn(int column) {
			this.column = column;
		}

		public MyButton() {
		}

		public MyButton(String name) {
			super(name);
		}
	}

	class MyLabel extends JLabel {
		private int row;
		private int column;

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getColumn() {
			return column;
		}

		public void setColumn(int column) {
			this.column = column;
		}

		public MyLabel() {
		}

		public MyLabel(String name) {
			super(name);
		}
	}

	abstract class MyEvent {
		public abstract void invoke(ActionEvent e);
	}

	/**
	 * ����jtable��excel
	 * 
	 * @param table
	 *            Ҫ������jtable
	 * @param file
	 *            Ҫ��������file
	 * @throws IOException
	 *             IO�쳣
	 */
	public static void exportTable(JTable table, File file) throws IOException {
		try {
			OutputStream out = new FileOutputStream(file);
			TableModel model = table.getModel();
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			// �����ֱ���д������
			WritableSheet ws = wwb.createSheet("Device", 0);
			// ��ӱ���
			for (int i = 0; i < model.getColumnCount() - 1; i++) {
				jxl.write.Label labelN = new jxl.write.Label(i, 0, model.getColumnName(i + 1));
				try {
					ws.addCell(labelN);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// �����
			for (int i = 0; i < model.getColumnCount() - 1; i++) {
				for (int j = 0; j < model.getRowCount(); j++) {
					try {
						jxl.write.Label labelN = new jxl.write.Label(i, j + 1, model.getValueAt(j, i + 1).toString());
						ws.addCell(labelN);
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					} catch (NullPointerException e) {
					}
				}
			}
			wwb.write();
			try {
				wwb.close();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "��������ǰ��رչ�����");
		}
	}
}