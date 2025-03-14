import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Accenture extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEnterUsername;
	private JTextField txtEnterPassword;
	private JLabel lblMessage;
	
	private HashMap<String, Employee.Employees> employees = new HashMap<>();
	private ArrayList<String> logs = new ArrayList<>();
	private JButton btnExit;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	public void setEmployeeData(HashMap<String, Employee.Employees> data) { 
		this.employees = data;
	}
	public void setLogs(ArrayList<String> logData) {
		this.logs = logData;
	}
	
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Accenture frame = new Accenture();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	

		});
	}
	
	public ArrayList<String> getLogs() {
		return logs;
	}
	
	public HashMap<String, Employee.Employees> getEmployeeData() {
		return employees;
	}
	
	

	/**
	 * Create the frame.
	 */
	public Accenture() {
		setLocationByPlatform(true);
		setTitle("Accenture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 451);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(255, 255, 255));
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblUsername.setBounds(398, 152, 73, 13);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblPassword.setBounds(398, 219, 73, 13);
		contentPane.add(lblPassword);
		
		txtEnterUsername = new JTextField();
		txtEnterUsername.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		txtEnterUsername.setForeground(new Color(0, 0, 0));
		txtEnterUsername.setBackground(new Color(255, 255, 255));
		txtEnterUsername.setBounds(398, 173, 233, 19);
		contentPane.add(txtEnterUsername);
		txtEnterUsername.setColumns(10);
		
	
		
		
		txtEnterPassword = new JPasswordField();
		txtEnterPassword.setForeground(new Color(0, 0, 0));
		txtEnterPassword.setBackground(Color.WHITE);
		txtEnterPassword.setBounds(398, 242, 233, 19);

		((JPasswordField) txtEnterPassword).setEchoChar('*');

		contentPane.add(txtEnterPassword);
		
		
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtEnterUsername.getText().equals("admin") && txtEnterPassword.getText().equals("admin123")) {
					Employee emp = new Employee();
					String role = "Admin";
					emp.setEmployeeData(getEmployeeData());
					emp.setLogs(getLogs());
			        emp.getLblCurrent().setText(role);
			        emp.refreshUI();
			        emp.setVisible(true);
			        
			        emp.revalidate();
			        emp.repaint();

			        logs.add("Admin logged in" + " " + emp.getTimestamp());
					dispose();
				}
				
				else if (txtEnterUsername.getText().equals("manager") && txtEnterPassword.getText().equals("manager123")) {
					Employee emp = new Employee();
					
					String role = "Manager";

					emp.setEmployeeData(getEmployeeData());
					emp.setLogs(getLogs());
                    emp.getLblCurrent().setText(role);
                    emp.setVisible(true);
                    emp.refreshUI(); 
                    
                    emp.revalidate();
                    emp.repaint();
                    
                    logs.add("Manager logged in" + " " + emp.getTimestamp());
					dispose();
				}
				else if (txtEnterUsername.getText().equals("user") && txtEnterPassword.getText().equals("user123")) {
					Employee emp = new Employee();

					String role = "Employee";
					emp.setEmployeeData(getEmployeeData());
					emp.setLogs(getLogs());
					emp.getLblCurrent().setText(role); 
					emp.refreshUI(); 
					emp.setVisible(true);
					
					emp.revalidate();
					emp.repaint();
					
					logs.add("Employee logged in" + " " + emp.getTimestamp());
					dispose();
				}
				else {
					lblMessage.setText("Invalid username or password");
				}
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnNewButton.setBounds(480, 301, 85, 21);
		contentPane.add(btnNewButton);
		
		lblMessage = new JLabel("");
		lblMessage.setForeground(new Color(255, 255, 255));
		lblMessage.setFont(new Font("Segoe UI", Font.PLAIN, 10));
		lblMessage.setBounds(398, 271, 233, 13);
		contentPane.add(lblMessage);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(480, 332, 85, 21);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\carlos\\Pictures\\aaa.jpg"));
		lblNewLabel.setBounds(0, 0, 362, 414);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Accenture");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_1.setBounds(409, 64, 219, 60);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Welcome!");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_2.setBounds(409, 30, 103, 60);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Let There Be Change");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(462, 120, 103, 13);
		contentPane.add(lblNewLabel_3);
				
	}
}
