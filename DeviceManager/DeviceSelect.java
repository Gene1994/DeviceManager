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
		comboBox_type.setModel(new DefaultComboBoxModel(new String[] {"PC", "\u670D\u52A1\u5668", "DVR/NVR", "DVS", "IPC", "IP\u7403", "NVR", "UTC", "\u534A\u7403PTZ", "\u62A5\u8B66\u4E3B\u673A", "\u8F66\u7AD9-DVR", "\u52A8\u73AF\u76D1\u63A7\u62A5\u8B66\u4E3B\u673A", "\u591A\u5C4F\u63A7\u5236\u5668", "\u5408\u7801\u5668", "\u4F1A\u8BAE\u89C6\u9891\u7EC8\u7AEF", "\u4EA4\u901AIPC", "\u89E3\u7801\u5668", "\u77E9\u9635", "\u95E8\u7981\u4E00\u4F53\u673A", "\u95E8\u7981\u4E3B\u673A", "\u67AA\u673AIPC", "\u70ED\u6210\u50CF", "\u53CC\u76EEIPC", "\u7F51\u7EDC\u89C6\u97F3\u9891\u89E3\u7801\u5668", "\u65E0\u7EBF\u95E8\u7981\u4E3B\u673A", "\u4FE1\u606F\u53D1\u5E03\u4E3B\u673A", "\u9E70\u773C", "\u8424\u77F3IPC", "\u9C7C\u773C", "\u81EA\u52A9\u94F6\u884C\u62A5\u8B66\u4E3B\u673A", "\u7EFC\u5408\u5E73\u53F0", "\u5750\u5F0FIP\u7403"}));
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
