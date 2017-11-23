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
 * Title: DeviceList<／p>
 * <p>
 * Description: <／p>
 * 
 * @author quzhe
 * 
 *         2017年11月20日
 */
public class DeviceList extends JFrame {
	// rowData用来存放行数据
	// columnNames存放列名
	Vector columnNames;
	Vector rowData = new Vector();
	JTable jt = null;
	JScrollPane jsp = null;

	// 设备属性
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
		// 设置列名
		columnNames.add("设备ID");
		columnNames.add("设备类型");
		columnNames.add("设备型号");
		columnNames.add("IP");
		columnNames.add("端口号");
		columnNames.add("用户名");
		columnNames.add("密码");
		columnNames.add("语言");
		columnNames.add("isOnline");// 后期修改成“状态”
		columnNames.add("errorCode");
		columnNames.add("位置");
		columnNames.add("备注");
		columnNames.add("");// 修改设备信息按钮(update)
		columnNames.add("");// 删除设备信息按钮(delete)

	}

	public void select(String s) {
		switch (DeviceSelect.index) {
		// byType
		case 1:
			String index = s;
			String sql = "select * from device where type = ?";

			// 创建填充参数的list
			List<Object> paramList = new ArrayList<Object>();
			// 填充参数
			paramList.add(index);
			JdbcUtil jdbcUtil = null;
			try {
				jdbcUtil = new JdbcUtil();
				jdbcUtil.getConnection(); // 获取数据库链接
				List<Map<String, Object>> mapList = jdbcUtil.findResult(sql.toString(), paramList);
				for (int i = 0; i < mapList.size(); i++) {

					Map<String, Object> map = mapList.get(i);
					try {
						// 设备属性
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
				System.out.println(this.getClass() + "执行查询操作抛出异常！");
				e.printStackTrace();
			} finally {
				if (jdbcUtil != null) {
					jdbcUtil.releaseConn();
				}
			}
			// 初始化JTable
			jt = new JTable(rowData, columnNames);

			// 修改
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
					// 创建填充参数的list
					List<Object> paramList = new ArrayList<Object>();
					// 填充参数
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
						jdbcUtil.getConnection(); // 获取数据库链接
						bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
					} catch (SQLException e1) {
						System.out.println(this.getClass() + "执行修改操作抛出异常！");
						e1.printStackTrace();
					} finally {
						if (jdbcUtil != null) {
							jdbcUtil.releaseConn();
						}
					}
					System.out.println("执行修改的结果：" + bool);
					if (bool) {
						JOptionPane.showMessageDialog(null, "修改成功");
					} else {
						JOptionPane.showMessageDialog(null, "修改失败", "错误", JOptionPane.ERROR_MESSAGE);
					}
				}

			};

			// 删除
			MyEvent e1 = new MyEvent() {
				@Override
				public void invoke(ActionEvent e) {
					MyButton button = (MyButton) e.getSource();
					int row = button.getRow();
					deviceId = (String) jt.getValueAt(row, 0);

					String sql = "delete from device where deviceid=?";
					// 创建填充参数的list
					List<Object> paramList = new ArrayList<Object>();
					// 填充参数
					paramList.add(deviceId);

					JdbcUtil jdbcUtil = null;
					boolean bool = false;
					try {
						jdbcUtil = new JdbcUtil();
						jdbcUtil.getConnection(); // 获取数据库链接
						bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
					} catch (SQLException exception) {
						System.out.println(this.getClass() + "执行删除操作抛出异常！");
						exception.printStackTrace();
					} finally {
						if (jdbcUtil != null) {
							jdbcUtil.releaseConn();
						}
					}
					System.out.println("执行删除的结果：" + bool);
					if (bool) {
						JOptionPane.showMessageDialog(null, "删除成功");
					} else {
						JOptionPane.showMessageDialog(null, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
					}
				}

			};
			// 设置表格渲染器
			jt.getColumnModel().getColumn(12).setCellRenderer(new MyRender());
			// 设置表格的编辑器
			jt.getColumnModel().getColumn(12).setCellEditor(new MyRender(e));

			// 设置表格渲染器
			jt.getColumnModel().getColumn(13).setCellRenderer(new MyRenderDelete());
			// 设置表格的编辑器
			jt.getColumnModel().getColumn(13).setCellEditor(new MyRenderDelete(e1));

			// 初始化 jsp
			jsp = new JScrollPane(jt);

			// 把jsp放入到jframe
			getContentPane().add(jsp);

			this.setSize(1500, 300);

			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			break;
		// byModel
		case 2:
			String index1 = s;
			String sql1 = "select * from device where model = ?";

			// 创建填充参数的list
			List<Object> paramList1 = new ArrayList<Object>();
			// 填充参数
			paramList1.add(index1);
			JdbcUtil jdbcUtil1 = null;
			try {
				jdbcUtil1 = new JdbcUtil();
				jdbcUtil1.getConnection(); // 获取数据库链接
				List<Map<String, Object>> mapList = jdbcUtil1.findResult(sql1.toString(), paramList1);
				for (int i = 0; i < mapList.size(); i++) {

					Map<String, Object> map = mapList.get(i);

					try {
						// 设备属性
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
				System.out.println(this.getClass() + "执行查询操作抛出异常！");
				e2.printStackTrace();
			} finally {
				if (jdbcUtil1 != null) {
					jdbcUtil1.releaseConn();
				}
			}
			// 初始化JTable
			jt = new JTable(rowData, columnNames);

			// 修改
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
					// 创建填充参数的list
					List<Object> paramList = new ArrayList<Object>();
					// 填充参数
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
						jdbcUtil.getConnection(); // 获取数据库链接
						bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
					} catch (SQLException e1) {
						System.out.println(this.getClass() + "执行修改操作抛出异常！");
						e1.printStackTrace();
					} finally {
						if (jdbcUtil != null) {
							jdbcUtil.releaseConn();
						}
					}
					System.out.println("执行修改的结果：" + bool);
					if (bool) {
						JOptionPane.showMessageDialog(null, "修改成功");
					} else {
						JOptionPane.showMessageDialog(null, "修改失败", "错误", JOptionPane.ERROR_MESSAGE);
					}
				}

			};

			// 删除
			MyEvent e4 = new MyEvent() {
				@Override
				public void invoke(ActionEvent e) {
					MyButton button = (MyButton) e.getSource();
					int row = button.getRow();
					deviceId = (String) jt.getValueAt(row, 0);

					String sql = "delete from device where deviceid=?";
					// 创建填充参数的list
					List<Object> paramList = new ArrayList<Object>();
					// 填充参数
					paramList.add(deviceId);

					JdbcUtil jdbcUtil = null;
					boolean bool = false;
					try {
						jdbcUtil = new JdbcUtil();
						jdbcUtil.getConnection(); // 获取数据库链接
						bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
					} catch (SQLException exception) {
						System.out.println(this.getClass() + "执行删除操作抛出异常！");
						exception.printStackTrace();
					} finally {
						if (jdbcUtil != null) {
							jdbcUtil.releaseConn();
						}
					}
					System.out.println("执行删除的结果：" + bool);
					if (bool) {
						JOptionPane.showMessageDialog(null, "删除成功");
					} else {
						JOptionPane.showMessageDialog(null, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
					}
				}

			};
			// 设置表格渲染器
			jt.getColumnModel().getColumn(12).setCellRenderer(new MyRender());
			// 设置表格的编辑器
			jt.getColumnModel().getColumn(12).setCellEditor(new MyRender(e3));

			// 设置表格渲染器
			jt.getColumnModel().getColumn(13).setCellRenderer(new MyRenderDelete());
			// 设置表格的编辑器
			jt.getColumnModel().getColumn(13).setCellEditor(new MyRenderDelete(e4));

			// 初始化 jsp
			jsp = new JScrollPane(jt);

			// 把jsp放入到jframe
			getContentPane().add(jsp);

			this.setSize(1500, 300);

			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			break;

		// // byIp
		case 3:
			String index2 = s;
			String sql2 = "select * from device where ip = ?";

			// 创建填充参数的list
			List<Object> paramList2 = new ArrayList<Object>();
			// 填充参数
			paramList2.add(index2);
			JdbcUtil jdbcUtil2 = null;
			try {
				jdbcUtil2 = new JdbcUtil();
				jdbcUtil2.getConnection(); // 获取数据库链接
				List<Map<String, Object>> mapList = jdbcUtil2.findResult(sql2.toString(), paramList2);
				for (int i = 0; i < mapList.size(); i++) {

					Map<String, Object> map = mapList.get(i);
					try {
						// 设备属性
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
				System.out.println(this.getClass() + "执行查询操作抛出异常！");
				e2.printStackTrace();
			} finally {
				if (jdbcUtil2 != null) {
					jdbcUtil2.releaseConn();
				}
			}
			// 初始化JTable
			jt = new JTable(rowData, columnNames);

			// 修改
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
					// 创建填充参数的list
					List<Object> paramList = new ArrayList<Object>();
					// 填充参数
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
						jdbcUtil.getConnection(); // 获取数据库链接
						bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
					} catch (SQLException e1) {
						System.out.println(this.getClass() + "执行修改操作抛出异常！");
						e1.printStackTrace();
					} finally {
						if (jdbcUtil != null) {
							jdbcUtil.releaseConn();
						}
					}
					System.out.println("执行修改的结果：" + bool);
					if (bool) {
						JOptionPane.showMessageDialog(null, "修改成功");
					} else {
						JOptionPane.showMessageDialog(null, "修改失败", "错误", JOptionPane.ERROR_MESSAGE);
					}
				}

			};

			// 删除
			MyEvent e6 = new MyEvent() {
				@Override
				public void invoke(ActionEvent e) {
					MyButton button = (MyButton) e.getSource();
					int row = button.getRow();
					deviceId = (String) jt.getValueAt(row, 0);

					String sql = "delete from device where deviceid=?";
					// 创建填充参数的list
					List<Object> paramList = new ArrayList<Object>();
					// 填充参数
					paramList.add(deviceId);

					JdbcUtil jdbcUtil = null;
					boolean bool = false;
					try {
						jdbcUtil = new JdbcUtil();
						jdbcUtil.getConnection(); // 获取数据库链接
						bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
					} catch (SQLException exception) {
						System.out.println(this.getClass() + "执行删除操作抛出异常！");
						exception.printStackTrace();
					} finally {
						if (jdbcUtil != null) {
							jdbcUtil.releaseConn();
						}
					}
					System.out.println("执行删除的结果：" + bool);
					if (bool) {
						JOptionPane.showMessageDialog(null, "删除成功");
					} else {
						JOptionPane.showMessageDialog(null, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
					}
				}

			};
			// 设置表格渲染器
			jt.getColumnModel().getColumn(12).setCellRenderer(new MyRender());
			// 设置表格的编辑器
			jt.getColumnModel().getColumn(12).setCellEditor(new MyRender(e5));

			// 设置表格渲染器
			jt.getColumnModel().getColumn(13).setCellRenderer(new MyRenderDelete());
			// 设置表格的编辑器
			jt.getColumnModel().getColumn(13).setCellEditor(new MyRenderDelete(e6));

			// 初始化 jsp
			jsp = new JScrollPane(jt);

			// 把jsp放入到jframe
			getContentPane().add(jsp);

			this.setSize(1500, 300);

			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			break;
		}
	}

	public void selectAll() {
		String sql = "select * from device";

		// 创建填充参数的list
		List<Object> paramList = new ArrayList<Object>();

		JdbcUtil jdbcUtil = null;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // 获取数据库链接
			List<Map<String, Object>> mapList = jdbcUtil.findResult(sql.toString(), paramList);
			for (int i = 0; i < mapList.size(); i++) {

				Map<String, Object> map = mapList.get(i);
				try {
					// 设备属性
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
			System.out.println(this.getClass() + "执行查询操作抛出异常！");
			e.printStackTrace();
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn();
			}
		}
		// 初始化JTable
		jt = new JTable(rowData, columnNames);

		// 修改
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
				// 创建填充参数的list
				List<Object> paramList = new ArrayList<Object>();
				// 填充参数
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
					jdbcUtil.getConnection(); // 获取数据库链接
					bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
				} catch (SQLException e1) {
					System.out.println(this.getClass() + "执行修改操作抛出异常！");
					e1.printStackTrace();
				} finally {
					if (jdbcUtil != null) {
						jdbcUtil.releaseConn();
					}
				}
				System.out.println("执行修改的结果：" + bool);
			}

		};

		// 删除
		MyEvent e1 = new MyEvent() {
			@Override
			public void invoke(ActionEvent e) {
				MyButton button = (MyButton) e.getSource();
				int row = button.getRow();
				deviceId = (String) jt.getValueAt(row, 0);

				String sql = "delete from device where deviceid=?";
				// 创建填充参数的list
				List<Object> paramList = new ArrayList<Object>();
				// 填充参数
				paramList.add(deviceId);

				JdbcUtil jdbcUtil = null;
				boolean bool = false;
				try {
					jdbcUtil = new JdbcUtil();
					jdbcUtil.getConnection(); // 获取数据库链接
					bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
				} catch (SQLException exception) {
					System.out.println(this.getClass() + "执行删除操作抛出异常！");
					exception.printStackTrace();
				} finally {
					if (jdbcUtil != null) {
						jdbcUtil.releaseConn();
					}
				}
				System.out.println("执行删除的结果：" + bool);
			}

		};
		// 设置表格渲染器
		jt.getColumnModel().getColumn(12).setCellRenderer(new MyRender());
		// 设置表格的编辑器
		jt.getColumnModel().getColumn(12).setCellEditor(new MyRender(e));

		// 设置表格渲染器
		jt.getColumnModel().getColumn(13).setCellRenderer(new MyRenderDelete());
		// 设置表格的编辑器
		jt.getColumnModel().getColumn(13).setCellEditor(new MyRenderDelete(e1));

		// 初始化 jsp
		jsp = new JScrollPane(jt);

		// 把jsp放入到jframe
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
	// //value 源于editor
	//// String text = (value == null) ? "" : value.toString();
	// //按钮文字
	// setText("修改");
	// //单元格提示
	//// setToolTipText("");
	// //背景色
	// setBackground(Color.BLACK);
	// //前景色
	// setForeground(Color.green);
	// return this;
	// }
	//
	// }

	class MyRender extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

		private MyButton button;

		private MyEvent event;

		public MyRender() {
			button = new MyButton("修改");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 这里调用自定义的事件处理方法
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
			// 单元格提示
			// button.setToolTipText("");
			// 背景色
			button.setBackground(Color.BLACK);
			// 前景色
			button.setForeground(Color.green);
			return button;
		}

		/*
		 * 重写编辑器方法，返回一个按钮给JTable
		 */
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// setClickCountToStart(1);
			// 将这个被点击的按钮所在的行和列放进button里面
			button.setRow(row);
			button.setColumn(column);
			// 背景色
			button.setBackground(Color.BLACK);
			// 前景色
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
			button = new MyButton("删除");
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 这里调用自定义的事件处理方法
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
			// 单元格提示
			// button.setToolTipText("");
			// 背景色
			button.setBackground(Color.BLACK);
			// 前景色
			button.setForeground(Color.red);
			return button;
		}

		/*
		 * 重写编辑器方法，返回一个按钮给JTable
		 */
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			// setClickCountToStart(1);
			// 将这个被点击的按钮所在的行和列放进button里面
			button.setRow(row);
			button.setColumn(column);
			// 背景色
			button.setBackground(Color.BLACK);
			// 前景色
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

//// 渲染 器 编辑器
// class MyRender extends AbstractCellEditor implements TableCellRenderer,
//// ActionListener, TableCellEditor {
// private MyButton button = null;
//
// public MyRender() {
// button = new MyButton("修改");
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
// // 创建填充参数的list
// List<Object> paramList = new ArrayList<Object>();
// // 填充参数
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
// jdbcUtil.getConnection(); // 获取数据库链接
// bool = jdbcUtil.updateByPreparedStatement(sql, paramList);
// } catch (SQLException e1) {
// System.out.println(this.getClass() + "执行更新操作抛出异常！");
// e1.printStackTrace();
// } finally {
// if (jdbcUtil != null) {
// jdbcUtil.releaseConn(); // 一定要释放资源
// }
// }
// System.out.println("执行更新的结果：" + bool);
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
