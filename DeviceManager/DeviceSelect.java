package DeviceManager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * <p>
 * Title: DeviceSelect<／p>
 * <p>
 * Description: <／p>
 * 
 * @author quzhe
 * 
 *         2017年11月17日
 */
public class DeviceSelect {

	private JFrame frame;
	private JTextField textField_model;
	private JTextField textField_ip;

	// 设备属性
	public String type;
	public String model;
	public String ip;

	public static int index;// 使用switch-case创建devicelist构造函数，case1:type,
							// case2:model, case3:ip。
	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// DeviceSelect window = new DeviceSelect();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public DeviceSelect() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setTitle("\u8BBE\u5907\u67E5\u627E");
		frame.setBounds(100, 100, 458, 197);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		// tpye
		JLabel lbType = new JLabel("\u8BBE\u5907\u7C7B\u578B\uFF1A");
		lbType.setBounds(28, 26, 71, 15);
		frame.getContentPane().add(lbType);

		JComboBox comboBox_type = new JComboBox();
		comboBox_type.setModel(new DefaultComboBoxModel(new String[] { "PC", "\u670D\u52A1\u5668" }));
		comboBox_type.setBounds(109, 23, 157, 21);
		frame.getContentPane().add(comboBox_type);
		type = comboBox_type.getSelectedItem().toString();
		comboBox_type.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				type = comboBox_type.getSelectedItem().toString();
			}
		});

		JButton btntype = new JButton("\u6839\u636E\u7C7B\u578B\u67E5\u627E");
		btntype.setBounds(293, 22, 123, 23);
		frame.getContentPane().add(btntype);
		btntype.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = 1;
				DeviceList dl = new DeviceList();
				dl.select(type);
			}
		});

		// model
		JLabel lbModel = new JLabel("\u8BBE\u5907\u578B\u53F7\uFF1A");
		lbModel.setBounds(28, 67, 71, 15);
		frame.getContentPane().add(lbModel);

		textField_model = new JTextField();
		textField_model.setColumns(10);
		textField_model.setBounds(109, 64, 157, 21);
		frame.getContentPane().add(textField_model);

		textField_model.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				model = textField_model.getText();
			}
		});

		JButton btnmodel = new JButton("\u6839\u636E\u578B\u53F7\u67E5\u627E");
		btnmodel.setBounds(293, 63, 123, 23);
		frame.getContentPane().add(btnmodel);
		btnmodel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = 2;
				DeviceList dl = new DeviceList();
				dl.select(model);
			}
		});

		// ip
		JLabel lblIp = new JLabel("ip\uFF1A");
		lblIp.setBounds(28, 111, 71, 15);
		frame.getContentPane().add(lblIp);

		textField_ip = new JTextField();

		textField_ip.setColumns(10);
		textField_ip.setBounds(109, 108, 157, 21);
		frame.getContentPane().add(textField_ip);
		textField_ip.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ip = textField_ip.getText();
			}
		});

		JButton btnip = new JButton("\u6839\u636EIP\u67E5\u627E");
		btnip.setBounds(293, 107, 123, 23);
		frame.getContentPane().add(btnip);
		btnip.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				index = 3;
				DeviceList dl = new DeviceList();
				dl.select(ip);
			}
		});
	}
}
