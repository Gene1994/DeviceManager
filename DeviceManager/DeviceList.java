package DeviceManager;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import JDBC.JdbcUtil;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;

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
public class DeviceList extends JFrame {
	// rowData�������������
	// columnNames�������
	Vector columnNames;
	Vector rowData = new Vector();
	JTable jt = null;
	JScrollPane jsp = null;

	// �豸����
	public String deviceId;
	public String type;
	public String model;
	public String ip;
	public int port;
	public String userName;
	public String password;
	public String language;
	public String isOnline;
	public int errorCode;
	public String comment;
	public String position;

	public DeviceList() {
		setTitle("\u8BBE\u5907\u5217\u8868");
		columnNames = new Vector();
		// ��������
		columnNames.add("�豸ID");
		columnNames.add("�豸����");
		columnNames.add("�豸�ͺ�");
		columnNames.add("IP");
		columnNames.add("�˿ں�");
		columnNames.add("�û���");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("isOnline");// �����޸ĳɡ�״̬��
		columnNames.add("errorCode");
		columnNames.add("λ��");
		columnNames.add("��ע");
		columnNames.add("");// �޸��豸��Ϣ��ť(update)
		columnNames.add("");// ɾ���豸��Ϣ��ť(delete)

	}

	public void select(String s) {
		switch (DeviceSelect.index) {
		// byType
		case 1:
			String index = s;
			String sql = "select * from device where type = ?";

			// ������������list
			List<Object> paramList = new ArrayList<Object>();
			// ������
			paramList.add(index);
			JdbcUtil jdbcUtil = null;
			try {
				jdbcUtil = new JdbcUtil();
				jdbcUtil.getConnection(); // ��ȡ���ݿ�����
				List<Map<String, Object>> mapList = jdbcUtil.findResult(sql.toString(), paramList);
				for (int i = 0; i < mapList.size(); i++) {

					Map<String, Object> map = mapList.get(i);
					try {
						// �豸����
						deviceId = (String) map.get("deviceid");
						type = (String) map.get("type");
						model = (String) map.get("model");
						ip = (String) map.get("ip");
						port = (int) map.get("port");
						userName = (String) map.get("username");
						password = (String) map.get("password");
						language = (String) map.get("language");
						// boolean isOnline1 = ((String) map.get("isOnline")) ==
						// "true" ? true : false;
						isOnline = (String) map.get("isonline");
						errorCode = (int) map.get("errorcode");
						position = (String) map.get("position");
						comment = (String) map.get("comment");
					} catch (Exception e) {

					}

					Vector hang = new Vector();
					hang.add(deviceId);
					hang.add(type);
					hang.add(model);
					hang.add(ip);
					hang.add(port);
					hang.add(userName);
					hang.add(password);
					hang.add(language);
					hang.add(isOnline);
					hang.add(errorCode);
					hang.add(position);
					hang.add(comment);

					rowData.add(hang);

				}
			} catch (SQLException e) {
				System.out.println(this.getClass() + "ִ�в�ѯ�����׳��쳣��");
				e.printStackTrace();
			} finally {
				if (jdbcUtil != null) {
					jdbcUtil.releaseConn();
				}
			}
			// ��ʼ��JTable
			jt = new JTable(rowData, columnNames);

			// �޸�
			MyEvent e = new MyEvent() {
				@Override
				public void invoke(ActionEvent e) {
					MyButton button = (MyButton) e.getSource();
					int row = button.getRow();
					deviceId = (String) jt.getValueAt(row, 0);
					type = (String) jt.getValueAt(row, 1);
					model = (String) jt.getValueAt(row, 2);
					ip = (String) jt.getValueAt(row, 3);
					port = (int) jt.getValueAt(row, 4);
					userName = (String) jt.getValueAt(row, 5);
					password = (String) jt.getValueAt(row, 6);
					language = (String) jt.getValueAt(row, 7);
					// boolean isOnline1 = ((String) map.get("isOnline")) ==
					// "true" ? true : false;
					isOnline = (String) jt.getValueAt(row, 8);
					errorCode = (int) jt.getValueAt(row, 9);
					position = (String) jt.getValueAt(row, 10);
					comment = (String) jt.getValueAt(row, 11);

					String sql = "update device set deviceid=?,type=?,model=?,ip=?,port=?,username=?,password=?,language=?,isonline=?,errorcode=?,position=?,comment=? where deviceid=?";
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
					paramList.add(isOnline);
					paramList.add(errorCode);
					paramList.add(position);
					paramList.add(comment);
					paramList.add(deviceId);

					JdbcUtil jdbcUtil = null;
					boolean bool = false;
					try {
						jdbcUtil = new JdbcUtil();
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
			MyEvent e1 = new MyEvent() {
				@Override
				public void invoke(ActionEvent e) {
					MyButton button = (MyButton) e.getSource();
					int row = button.getRow();
					deviceId = (String) jt.getValueAt(row, 0);

					String sql = "delete from device where deviceid=?";
					// ������������list
					List<Object> paramList = new ArrayList<Object>();
					// ������
					paramList.add(deviceId);

					JdbcUtil jdbcUtil = null;
					boolean bool = false;
					try {
						jdbcUtil = new JdbcUtil();
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
			jt.getColumnModel().getColumn(12).setCellRenderer(new MyRender());
			// ���ñ��ı༭��
			jt.getColumnModel().getColumn(12).setCellEditor(new MyRender(e));

			// ���ñ����Ⱦ��
			jt.getColumnModel().getColumn(13).setCellRenderer(new MyRenderDelete());
			// ���ñ��ı༭��
			jt.getColumnModel().getColumn(13).setCellEditor(new MyRenderDelete(e1));

			// ��ʼ�� jsp
			jsp = new JScrollPane(jt);

			// ��jsp���뵽jframe
			getContentPane().add(jsp);

			this.setSize(1500, 300);

			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			break;
		// byModel
		case 2:
			String index1 = s;
			String sql1 = "select * from device where model = ?";

			// ������������list
			List<Object> paramList1 = new ArrayList<Object>();
			// ������
			paramList1.add(index1);
			JdbcUtil jdbcUtil1 = null;
			try {
				jdbcUtil1 = new JdbcUtil();
				jdbcUtil1.getConnection(); // ��ȡ���ݿ�����
				List<Map<String, Object>> mapList = jdbcUtil1.findResult(sql1.toString(), paramList1);
				for (int i = 0; i < mapList.size(); i++) {

					Map<String, Object> map = mapList.get(i);

					try {
						// �豸����
						deviceId = (String) map.get("deviceid");
						type = (String) map.get("type");
						model = (String) map.get("model");
						ip = (String) map.get("ip");
						port = Integer.parseInt(map.get("port").toString());
						userName = (String) map.get("username");
						password = (String) map.get("password");
						language = (String) map.get("language");
						// boolean isOnline1 = ((String) map.get("isOnline")) ==
						// "true" ? true : false;
						isOnline = (String) map.get("isonline");
						errorCode = (int) map.get("errorcode");
						position = (String) map.get("position");
						comment = (String) map.get("comment");
					} catch (Exception e4) {

					}

					Vector hang = new Vector();
					hang.add(deviceId);
					hang.add(type);
					hang.add(model);
					hang.add(ip);
					hang.add(port);
					hang.add(userName);
					hang.add(password);
					hang.add(language);
					hang.add(isOnline);
					hang.add(errorCode);
					hang.add(position);
					hang.add(comment);

					rowData.add(hang);

				}
			} catch (SQLException e2) {
				System.out.println(this.getClass() + "ִ�в�ѯ�����׳��쳣��");
				e2.printStackTrace();
			} finally {
				if (jdbcUtil1 != null) {
					jdbcUtil1.releaseConn();
				}
			}
			// ��ʼ��JTable
			jt = new JTable(rowData, columnNames);

			// �޸�
			MyEvent e3 = new MyEvent() {
				@Override
				public void invoke(ActionEvent e) {
					MyButton button = (MyButton) e.getSource();
					int row = button.getRow();
					deviceId = (String) jt.getValueAt(row, 0);
					type = (String) jt.getValueAt(row, 1);
					model = (String) jt.getValueAt(row, 2);
					ip = (String) jt.getValueAt(row, 3);
					port = (int) jt.getValueAt(row, 4);
					userName = (String) jt.getValueAt(row, 5);
					password = (String) jt.getValueAt(row, 6);
					language = (String) jt.getValueAt(row, 7);
					// boolean isOnline1 = ((String) map.get("isOnline")) ==
					// "true" ? true : false;
					isOnline = (String) jt.getValueAt(row, 8);
					errorCode = (int) jt.getValueAt(row, 9);
					position = (String) jt.getValueAt(row, 10);
					comment = (String) jt.getValueAt(row, 11);

					String sql = "update device set deviceid=?,type=?,model=?,ip=?,port=?,username=?,password=?,language=?,isonline=?,errorcode=?,position=?,comment=? where deviceid=?";
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
					paramList.add(isOnline);
					paramList.add(errorCode);
					paramList.add(position);
					paramList.add(comment);
					paramList.add(deviceId);

					JdbcUtil jdbcUtil = null;
					boolean bool = false;
					try {
						jdbcUtil = new JdbcUtil();
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
			MyEvent e4 = new MyEvent() {
				@Override
				public void invoke(ActionEvent e) {
					MyButton button = (MyButton) e.getSource();
					int row = button.getRow();
					deviceId = (String) jt.getValueAt(row, 0);

					String sql = "delete from device where deviceid=?";
					// ������������list
					List<Object> paramList = new ArrayList<Object>();
					// ������
					paramList.add(deviceId);

					JdbcUtil jdbcUtil = null;
					boolean bool = false;
					try {
						jdbcUtil = new JdbcUtil();
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
			jt.getColumnModel().getColumn(12).setCellRenderer(new MyRender());
			// ���ñ��ı༭��
			jt.getColumnModel().getColumn(12).setCellEditor(new MyRender(e3));

			// ���ñ����Ⱦ��
			jt.getColumnModel().getColumn(13).setCellRenderer(new MyRenderDelete());
			// ���ñ��ı༭��
			jt.getColumnModel().getColumn(13).setCellEditor(new MyRenderDelete(e4));

			// ��ʼ�� jsp
			jsp = new JScrollPane(jt);

			// ��jsp���뵽jframe
			getContentPane().add(jsp);

			this.setSize(1500, 300);

			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			break;

		// // byIp
		case 3:
			String index2 = s;
			String sql2 = "select * from device where ip = ?";

			// ������������list
			List<Object> paramList2 = new ArrayList<Object>();
			// ������
			paramList2.add(index2);
			JdbcUtil jdbcUtil2 = null;
			try {
				jdbcUtil2 = new JdbcUtil();
				jdbcUtil2.getConnection(); // ��ȡ���ݿ�����
				List<Map<String, Object>> mapList = jdbcUtil2.findResult(sql2.toString(), paramList2);
				for (int i = 0; i < mapList.size(); i++) {

					Map<String, Object> map = mapList.get(i);
					try {
						// �豸����
						deviceId = (String) map.get("deviceid");
						type = (String) map.get("type");
						model = (String) map.get("model");
						ip = (String) map.get("ip");
						port = (int) map.get("port");
						userName = (String) map.get("username");
						password = (String) map.get("password");
						language = (String) map.get("language");
						// boolean isOnline1 = ((String) map.get("isOnline")) ==
						// "true" ? true : false;
						isOnline = (String) map.get("isonline");
						errorCode = (int) map.get("errorcode");
						position = (String) map.get("position");
						comment = (String) map.get("comment");
					} catch (Exception e2) {

					}

					Vector hang = new Vector();
					hang.add(deviceId);
					hang.add(type);
					hang.add(model);
					hang.add(ip);
					hang.add(port);
					hang.add(userName);
					hang.add(password);
					hang.add(language);
					hang.add(isOnline);
					hang.add(errorCode);
					hang.add(position);
					hang.add(comment);

					rowData.add(hang);

				}
			} catch (SQLException e2) {
				System.out.println(this.getClass() + "ִ�в�ѯ�����׳��쳣��");
				e2.printStackTrace();
			} finally {
				if (jdbcUtil2 != null) {
					jdbcUtil2.releaseConn();
				}
			}
			// ��ʼ��JTable
			jt = new JTable(rowData, columnNames);

			// �޸�
			MyEvent e5 = new MyEvent() {
				@Override
				public void invoke(ActionEvent e) {
					MyButton button = (MyButton) e.getSource();
					int row = button.getRow();
					deviceId = (String) jt.getValueAt(row, 0);
					type = (String) jt.getValueAt(row, 1);
					model = (String) jt.getValueAt(row, 2);
					ip = (String) jt.getValueAt(row, 3);
					port = (int) jt.getValueAt(row, 4);
					userName = (String) jt.getValueAt(row, 5);
					password = (String) jt.getValueAt(row, 6);
					language = (String) jt.getValueAt(row, 7);
					// boolean isOnline1 = ((String) map.get("isOnline")) ==
					// "true" ? true : false;
					isOnline = (String) jt.getValueAt(row, 8);
					errorCode = (int) jt.getValueAt(row, 9);
					position = (String) jt.getValueAt(row, 10);
					comment = (String) jt.getValueAt(row, 11);

					String sql = "update device set deviceid=?,type=?,model=?,ip=?,port=?,username=?,password=?,language=?,isonline=?,errorcode=?,position=?,comment=? where deviceid=?";
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
					paramList.add(isOnline);
					paramList.add(errorCode);
					paramList.add(position);
					paramList.add(comment);
					paramList.add(deviceId);

					JdbcUtil jdbcUtil = null;
					boolean bool = false;
					try {
						jdbcUtil = new JdbcUtil();
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
			MyEvent e6 = new MyEvent() {
				@Override
				public void invoke(ActionEvent e) {
					MyButton button = (MyButton) e.getSource();
					int row = button.getRow();
					deviceId = (String) jt.getValueAt(row, 0);

					String sql = "delete from device where deviceid=?";
					// ������������list
					List<Object> paramList = new ArrayList<Object>();
					// ������
					paramList.add(deviceId);

					JdbcUtil jdbcUtil = null;
					boolean bool = false;
					try {
						jdbcUtil = new JdbcUtil();
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
			jt.getColumnModel().getColumn(12).setCellRenderer(new MyRender());
			// ���ñ��ı༭��
			jt.getColumnModel().getColumn(12).setCellEditor(new MyRender(e5));

			// ���ñ����Ⱦ��
			jt.getColumnModel().getColumn(13).setCellRenderer(new MyRenderDelete());
			// ���ñ��ı༭��
			jt.getColumnModel().getColumn(13).setCellEditor(new MyRenderDelete(e6));

			// ��ʼ�� jsp
			jsp = new JScrollPane(jt);

			// ��jsp���뵽jframe
			getContentPane().add(jsp);

			this.setSize(1500, 300);

			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			break;
		}
	}

	public void selectAll() {
		String sql = "select * from device";

		// ������������list
		List<Object> paramList = new ArrayList<Object>();

		JdbcUtil jdbcUtil = null;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // ��ȡ���ݿ�����
			List<Map<String, Object>> mapList = jdbcUtil.findResult(sql.toString(), paramList);
			for (int i = 0; i < mapList.size(); i++) {

				Map<String, Object> map = mapList.get(i);
				try {
					// �豸����
					deviceId = (String) map.get("deviceid");
					type = (String) map.get("type");
					model = (String) map.get("model");
					ip = (String) map.get("ip");
					port = (int) map.get("port");
					userName = (String) map.get("username");
					password = (String) map.get("password");
					language = (String) map.get("language");
					// boolean isOnline1 = ((String) map.get("isOnline")) ==
					// "true" ? true : false;
					isOnline = (String) map.get("isonline");
					errorCode = (int) map.get("errorcode");
					position = (String) map.get("position");
					comment = (String) map.get("comment");
				} catch (Exception e) {

				}

				Vector hang = new Vector();
				hang.add(deviceId);
				hang.add(type);
				hang.add(model);
				hang.add(ip);
				hang.add(port);
				hang.add(userName);
				hang.add(password);
				hang.add(language);
				hang.add(isOnline);
				hang.add(errorCode);
				hang.add(position);
				hang.add(comment);

				rowData.add(hang);

			}
		} catch (SQLException e) {
			System.out.println(this.getClass() + "ִ�в�ѯ�����׳��쳣��");
			e.printStackTrace();
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn();
			}
		}
		// ��ʼ��JTable
		jt = new JTable(rowData, columnNames);

		// �޸�
		MyEvent e = new MyEvent() {
			@Override
			public void invoke(ActionEvent e) {
				MyButton button = (MyButton) e.getSource();
				int row = button.getRow();
				deviceId = (String) jt.getValueAt(row, 0);
				type = (String) jt.getValueAt(row, 1);
				model = (String) jt.getValueAt(row, 2);
				ip = (String) jt.getValueAt(row, 3);
				port = (int) jt.getValueAt(row, 4);
				userName = (String) jt.getValueAt(row, 5);
				password = (String) jt.getValueAt(row, 6);
				language = (String) jt.getValueAt(row, 7);
				// boolean isOnline1 = ((String) map.get("isOnline")) ==
				// "true" ? true : false;
				isOnline = (String) jt.getValueAt(row, 8);
				errorCode = (int) jt.getValueAt(row, 9);
				position = (String) jt.getValueAt(row, 10);
				comment = (String) jt.getValueAt(row, 11);

				String sql = "update device set deviceid=?,type=?,model=?,ip=?,port=?,username=?,password=?,language=?,isonline=?,errorcode=?,position=?,comment=? where deviceid=?";
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
				paramList.add(isOnline);
				paramList.add(errorCode);
				paramList.add(position);
				paramList.add(comment);
				paramList.add(deviceId);

				JdbcUtil jdbcUtil = null;
				boolean bool = false;
				try {
					jdbcUtil = new JdbcUtil();
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
			}

		};

		// ɾ��
		MyEvent e1 = new MyEvent() {
			@Override
			public void invoke(ActionEvent e) {
				MyButton button = (MyButton) e.getSource();
				int row = button.getRow();
				deviceId = (String) jt.getValueAt(row, 0);

				String sql = "delete from device where deviceid=?";
				// ������������list
				List<Object> paramList = new ArrayList<Object>();
				// ������
				paramList.add(deviceId);

				JdbcUtil jdbcUtil = null;
				boolean bool = false;
				try {
					jdbcUtil = new JdbcUtil();
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
			}

		};
		// ���ñ����Ⱦ��
		jt.getColumnModel().getColumn(12).setCellRenderer(new MyRender());
		// ���ñ��ı༭��
		jt.getColumnModel().getColumn(12).setCellEditor(new MyRender(e));

		// ���ñ����Ⱦ��
		jt.getColumnModel().getColumn(13).setCellRenderer(new MyRenderDelete());
		// ���ñ��ı༭��
		jt.getColumnModel().getColumn(13).setCellEditor(new MyRenderDelete(e1));

		// ��ʼ�� jsp
		jsp = new JScrollPane(jt);

		// ��jsp���뵽jframe
		getContentPane().add(jsp);

		this.setSize(1500, 300);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	// class MyButtonRender extends JButton implements TableCellRenderer{
	//
	// public JComponent getTableCellRendererComponent(JTable table, Object
	// value,
	// boolean isSelected, boolean hasFocus, int row, int column) {
	// //value Դ��editor
	//// String text = (value == null) ? "" : value.toString();
	// //��ť����
	// setText("�޸�");
	// //��Ԫ����ʾ
	//// setToolTipText("");
	// //����ɫ
	// setBackground(Color.BLACK);
	// //ǰ��ɫ
	// setForeground(Color.green);
	// return this;
	// }
	//
	// }

	class MyRender extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

		private MyButton button;

		private MyEvent event;

		public MyRender() {
			button = new MyButton("�޸�");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// ��������Զ�����¼�������
					event.invoke(e);
				}

			});

		}

		public MyRender(MyEvent e) {
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
					// ��������Զ�����¼�������
					event.invoke(e);
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

	abstract class MyEvent {
		public abstract void invoke(ActionEvent e);
	}
}

//// ��Ⱦ �� �༭��
// class MyRender extends AbstractCellEditor implements TableCellRenderer,
//// ActionListener, TableCellEditor {
// private MyButton button = null;
//
// public MyRender() {
// button = new MyButton("�޸�");
// button.addActionListener(this);
// }
//
// @Override
// public Object getCellEditorValue() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public Component getTableCellRendererComponent(JTable table, Object value,
//// boolean isSelected, boolean hasFocus,
// int row, int column) {
// // TODO Auto-generated method stub
// button.setRow(row);
// button.setColumn(column);
// return button;
// }
//
// @Override
// public void actionPerformed(ActionEvent e) {
// // TODO Auto-generated method stub
// MyButton botton = (MyButton)e.getSource();
// int row = button.getRow();
// String deviceId = (String) jt.getValueAt(row, 0);
// String type = (String) jt.getValueAt(row, 1);
// String model = (String) jt.getValueAt(row, 2);
// String ip = (String) jt.getValueAt(row, 3);
// String port = (int) jt.getValueAt(row, 4);
// String userName = (String) jt.getValueAt(row, 5);
// String password = (String) jt.getValueAt(row, 6);
// String language = (String) jt.getValueAt(row, 7);
// // boolean isOnline1 = ((String) map.get("isOnline")) ==
// // "true" ? true : false;
// String isOnline = (String) jt.getValueAt(row, 8);
// int errorCode = (int) jt.getValueAt(row, 9);
// String position = (String) jt.getValueAt(row, 10);
// String comment = (String) jt.getValueAt(row, 11);
//
// String sql = "update device set
//// deviceid=?,type=?,model=?,ip=?,port=?,username=?,password=?,language=?,isonline=?,errorcode=?position=?comment=?
//// where deviceid=?";
// // ������������list
// List<Object> paramList = new ArrayList<Object>();
// // ������
// paramList.add(deviceId);
// paramList.add(type);
// paramList.add(model);
// paramList.add(ip);
// paramList.add(port);
// paramList.add(userName);
// paramList.add(password);
// paramList.add(language);
// paramList.add(isOnline);
// paramList.add(errorCode);
// paramList.add(position);
// paramList.add(comment);
// paramList.add(deviceId);
//
// JdbcUtil jdbcUtil = null;
// boolean bool = false;
// try {
// jdbcUtil = new JdbcUtil();
// jdbcUtil.getConnection(); // ��ȡ���ݿ�����
// bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
// } catch (SQLException e1) {
// System.out.println(this.getClass() + "ִ�и��²����׳��쳣��");
// e1.printStackTrace();
// } finally {
// if (jdbcUtil != null) {
// jdbcUtil.releaseConn(); // һ��Ҫ�ͷ���Դ
// }
// }
// System.out.println("ִ�и��µĽ����" + bool);
// }
//
//
// @Override
// public Component getTableCellEditorComponent(JTable table, Object value,
//// boolean isSelected, int row, int column) {
// // TODO Auto-generated method stub
// button.setRow(row);
// button.setColumn(column);
// return button;
// }
//
// }
