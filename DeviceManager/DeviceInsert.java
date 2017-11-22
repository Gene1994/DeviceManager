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
	public boolean isOnline;
	public int errorCode = 0;
	public String comment;
	public String position;
	public String deviceId;

	private JFrame frame;
	private JTextField textField_model;
	private JTextField textField_ip;
	private JTextField textField_port;
	private JTextField textField_errorCode;
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
					Device d = new Device(deviceId, type, model, ip, port, userName, password, language, isOnline,
							errorCode, position, comment);
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
		comboBox_type.setModel(new DefaultComboBoxModel(new String[] { "PC", "\u670D\u52A1\u5668" }));
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

		// isOnline
		JLabel lblIsonline = new JLabel("isOnline\uFF1A");
		lblIsonline.setBounds(232, 160, 88, 15);
		frame.getContentPane().add(lblIsonline);

		JComboBox comboBox_isOnline = new JComboBox();
		comboBox_isOnline.setModel(new DefaultComboBoxModel(new String[] { "Y", "N" }));
		comboBox_isOnline.setBounds(300, 154, 97, 21);
		frame.getContentPane().add(comboBox_isOnline);
		isOnline = (comboBox_isOnline.getSelectedIndex() == 0) ? true : false;
		comboBox_isOnline.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				isOnline = (comboBox_isOnline.getSelectedIndex() == 0) ? true : false;

			}
		});

		// errorCode
		JLabel lblErrorCode = new JLabel("errorCode\uFF1A");
		lblErrorCode.setBounds(38, 204, 66, 15);
		frame.getContentPane().add(lblErrorCode);

		textField_errorCode = new JTextField();
		textField_errorCode.setColumns(10);
		textField_errorCode.setBounds(100, 200, 97, 23);
		frame.getContentPane().add(textField_errorCode);

		try {
			textField_errorCode.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					errorCode = Integer.parseInt(textField_errorCode.getText());
				}
			});
		} catch (NumberFormatException e) {
			// JOptionPane.showMessageDialog(null, "Error", "请输入正确的错误码",
			// JOptionPane.ERROR_MESSAGE);
		}

		// comment
		JLabel lbComment = new JLabel("\u5907\u6CE8\uFF1A");
		lbComment.setBounds(38, 248, 66, 15);
		frame.getContentPane().add(lbComment);

		textField_comment = new JTextField();
		textField_comment.setColumns(10);
		textField_comment.setBounds(100, 243, 297, 23);
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
		lbPosition.setBounds(232, 204, 88, 15);
		frame.getContentPane().add(lbPosition);

		JComboBox comboBox_position = new JComboBox();
		comboBox_position.setModel(new DefaultComboBoxModel(new String[] { "\u7B2C\u4E00\u6392", "\u7B2C\u4E8C\u6392",
				"\u7B2C\u4E09\u6392", "\u7B2C\u56DB\u6392", "\u7B2C\u4E94\u6392" }));
		comboBox_position.setBounds(300, 198, 97, 21);
		frame.getContentPane().add(comboBox_position);
		position = comboBox_position.getSelectedItem().toString();
		comboBox_position.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				position = comboBox_position.getSelectedItem().toString();
			}
		});
	}
}
