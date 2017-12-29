package DeviceManager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.CardLayout;

/**
 * <p>Title: temp<£¯p>
 * <p>Description: <£¯p>
 * @author quzhe
 * 
 * 2017Äê12ÔÂ22ÈÕ
 */
public class temp {

	private JFrame frame;
	private JTextField tfIp;
	private JTextField tfPort;
	private JTextField tfUser;
	private JTextField tfPassword;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					temp window = new temp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public temp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 30, 375, 222);
		frame.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		JLabel lblIp = new JLabel("IP\uFF1A");
		panel.add(lblIp, "name_30852853896060");
		tfIp = new JTextField();
		tfIp.setColumns(10);
		tfIp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel.add(tfIp, "name_30852879852811");
		
		JLabel lbPort = new JLabel("\u7AEF\u53E3\uFF1A");
		panel.add(lbPort, "name_30852888775520");
		
		tfPort = new JTextField();
		tfPort.setText("8000");
		tfPort.setColumns(10);
		tfPort.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel.add(tfPort, "name_30852893865155");
		
		JLabel lbUser = new JLabel("\u7528\u6237\u540D\uFF1A");
		panel.add(lbUser, "name_30852898102211");
		
		tfUser = new JTextField();
		tfUser.setColumns(10);
		tfUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel.add(tfUser, "name_30852903403468");
		
		JLabel lbPassword = new JLabel("\u5BC6\u7801\uFF1A");
		panel.add(lbPassword, "name_30852907657590");
		
		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		tfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		panel.add(tfPassword, "name_30852911686681");
		
		btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton.setBounds(30, 10, 93, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
