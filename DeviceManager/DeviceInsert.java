package DeviceManager;

import DeviceManager.DeviceUI;
import DeviceManager.Device;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

/**
 * <p>
 * Title: DeviceInsert<／p>
 * <p>
 * Description: <／p>
 * 
 * @author quzhe
 * 
 *         2017年11月16日
 */
public class DeviceInsert {

	// 设备属性
	public String type;
	public String model;
	public String ip;
	public int port = 8000;
	public String userName = "admin";
	public String password;
	public String language;
	public boolean status = true;//添加时默认true，查询时修改
	public int errorCode = 0;
	public String comment;
	public String position;
	public String deviceId;

	private JFrame frame;
	private JTextField textField_model;
	private JTextField textField_ip;
	private JTextField textField_port;
	private JTextField textField_comment;
	private JTextField textField_userName;
	private JTextField textField_password;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// DeviceInsert window = new DeviceInsert();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 * 
	 */
	public DeviceInsert() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("\u6DFB\u52A0\u8BBE\u5907");
		frame.setBounds(100, 100, 500, 403);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblType = new JLabel("\u8BBE\u5907\u7C7B\u578B\uFF1A");
		lblType.setBounds(38, 28, 88, 15);
		frame.getContentPane().add(lblType);

		JButton btn_confirm = new JButton("\u786E\u8BA4");
		btn_confirm.setBounds(100, 308, 88, 23);
		frame.getContentPane().add(btn_confirm);
		try {
			btn_confirm.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					deviceId = String.format("%05d", (int) (Math.random() * 100000));
					// if(port >= -99999 && port <= 99999 && errorCode >= -99999
					// && errorCode <=99999){
					Device d = new Device(deviceId, type, model, ip, port, userName, password, language, position, comment, status);
					d.insert();
					// }else{
					// JOptionPane.showMessageDialog(null, "Error",
					// "请输入正确的端口号或错误码", JOptionPane.ERROR_MESSAGE);
					// }
				}
			});
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error", "请输入正确的端口号或错误码", JOptionPane.ERROR_MESSAGE);
		}

		JButton btn_cancel = new JButton("\u53D6\u6D88");
		btn_cancel.setBounds(274, 308, 88, 23);
		frame.getContentPane().add(btn_cancel);
		btn_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
			}
		});

		// type
		JComboBox comboBox_type = new JComboBox();
		comboBox_type.setModel(new DefaultComboBoxModel(new String[] {"PC", "\u670D\u52A1\u5668", "DVR/NVR", "DVS", "IPC", "IP\u7403", "NVR", "UTC", "\u534A\u7403PTZ", "\u62A5\u8B66\u4E3B\u673A", "\u8F66\u7AD9-DVR", "\u52A8\u73AF\u76D1\u63A7\u62A5\u8B66\u4E3B\u673A", "\u591A\u5C4F\u63A7\u5236\u5668", "\u5408\u7801\u5668", "\u4F1A\u8BAE\u89C6\u9891\u7EC8\u7AEF", "\u4EA4\u901AIPC", "\u89E3\u7801\u5668", "\u77E9\u9635", "\u95E8\u7981\u4E00\u4F53\u673A", "\u95E8\u7981\u4E3B\u673A", "\u67AA\u673AIPC", "\u70ED\u6210\u50CF", "\u53CC\u76EEIPC", "\u7F51\u7EDC\u89C6\u97F3\u9891\u89E3\u7801\u5668", "\u65E0\u7EBF\u95E8\u7981\u4E3B\u673A", "\u4FE1\u606F\u53D1\u5E03\u4E3B\u673A", "\u9E70\u773C", "\u8424\u77F3IPC", "\u9C7C\u773C", "\u81EA\u52A9\u94F6\u884C\u62A5\u8B66\u4E3B\u673A", "\u7EFC\u5408\u5E73\u53F0", "\u5750\u5F0FIP\u7403", "\u5176\u4ED6"}));
		comboBox_type.setBounds(100, 25, 97, 21);
		frame.getContentPane().add(comboBox_type);
		type = comboBox_type.getSelectedItem().toString();
		comboBox_type.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				type = comboBox_type.getSelectedItem().toString();
			}
		});

		// model
		JLabel lbModel = new JLabel("\u8BBE\u5907\u578B\u53F7\uFF1A");
		lbModel.setBounds(232, 28, 73, 15);
		frame.getContentPane().add(lbModel);

		textField_model = new JTextField();
		textField_model.setBounds(300, 25, 97, 23);
		frame.getContentPane().add(textField_model);
		textField_model.setColumns(10);
		model = textField_model.getText();
		textField_model.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				model = textField_model.getText();
			}
		});

		// ip
		JLabel lbIp = new JLabel("IP\uFF1A");
		lbIp.setBounds(38, 72, 54, 15);
		frame.getContentPane().add(lbIp);

		textField_ip = new JTextField();
		textField_ip.setColumns(10);
		textField_ip.setBounds(100, 68, 97, 23);
		frame.getContentPane().add(textField_ip);
		ip = textField_ip.getText();
		textField_ip.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ip = textField_ip.getText();
			}
		});

		// port
		JLabel lbPort = new JLabel("\u7AEF\u53E3\uFF1A");
		lbPort.setBounds(232, 72, 54, 15);
		frame.getContentPane().add(lbPort);

		textField_port = new JTextField();
		textField_port.setText("8000");
		textField_port.setColumns(10);
		textField_port.setBounds(300, 68, 97, 23);
		frame.getContentPane().add(textField_port);
		try {
			textField_port.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					port = Integer.parseInt(textField_port.getText());
				}
			});
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		// language
		JLabel lbLanguage = new JLabel("\u8BED\u8A00\uFF1A");
		lbLanguage.setBounds(38, 160, 88, 15);
		frame.getContentPane().add(lbLanguage);

		JComboBox comboBox_language = new JComboBox();
		comboBox_language.setModel(new DefaultComboBoxModel(new String[] { "CN", "EN" }));
		comboBox_language.setBounds(100, 154, 97, 21);
		frame.getContentPane().add(comboBox_language);
		language = comboBox_language.getSelectedItem().toString();
		comboBox_language.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				language = comboBox_language.getSelectedItem().toString();
			}
		});

		try {
		} catch (NumberFormatException e) {
			// JOptionPane.showMessageDialog(null, "Error", "请输入正确的错误码",
			// JOptionPane.ERROR_MESSAGE);
		}

		// comment
		JLabel lbComment = new JLabel("\u5907\u6CE8\uFF1A");
		lbComment.setBounds(38, 199, 66, 15);
		frame.getContentPane().add(lbComment);

		textField_comment = new JTextField();
		textField_comment.setColumns(10);
		textField_comment.setBounds(100, 194, 297, 90);
		frame.getContentPane().add(textField_comment);
		comment = textField_comment.getText();
		textField_comment.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				comment = textField_comment.getText();
			}
		});

		// userName
		JLabel lbUserName = new JLabel("\u7528\u6237\u540D\uFF1A");
		lbUserName.setBounds(38, 116, 54, 15);
		frame.getContentPane().add(lbUserName);

		textField_userName = new JTextField();
		textField_userName.setColumns(10);
		textField_userName.setBounds(100, 111, 97, 23);
		frame.getContentPane().add(textField_userName);
		textField_userName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				userName = textField_userName.getText();
			}
		});

		// password
		JLabel lbPassword = new JLabel("\u5BC6\u7801\uFF1A");
		lbPassword.setBounds(232, 116, 54, 15);
		frame.getContentPane().add(lbPassword);

		textField_password = new JTextField();
		textField_password.setColumns(10);
		textField_password.setBounds(300, 111, 97, 23);
		frame.getContentPane().add(textField_password);
		textField_password.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				password = textField_password.getText();
			}
		});

		// position
		JLabel lbPosition = new JLabel("\u4F4D\u7F6E\uFF1A");
		lbPosition.setBounds(232, 160, 88, 15);
		frame.getContentPane().add(lbPosition);

		JComboBox comboBox_position = new JComboBox();
		comboBox_position.setModel(new DefaultComboBoxModel(new String[] { "\u7B2C\u4E00\u6392", "\u7B2C\u4E8C\u6392",
				"\u7B2C\u4E09\u6392", "\u7B2C\u56DB\u6392", "\u7B2C\u4E94\u6392" }));
		comboBox_position.setBounds(300, 154, 97, 21);
		frame.getContentPane().add(comboBox_position);
		position = comboBox_position.getSelectedItem().toString();
		comboBox_position.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				position = comboBox_position.getSelectedItem().toString();
			}
		});
	}
}
