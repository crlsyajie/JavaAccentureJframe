import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class Employee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfDepartment;
	private JTabbedPane tabbedPane;
	private JTable table;
	private JTextField tfNameOp;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JLabel lblCurrent;
	private JTextField tfEmailOp;
	private JTextField tfDeptOp;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnAuditLogs;
	private JButton btnView;
	private JButton btnSignout;
	



    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String timestamp = now.format(formatter);
	
	

    private HashMap<String, Employees> employees = new HashMap<>();
	private ArrayList<String> logs = new ArrayList<>();

	class Employees {
		String name;
		String email;
		String department;

		Employees(String name, String email, String department) {
			this.name = name;
			this.email = email;
			this.department = department;
		}
	}
	
	public void setEmployeeData(HashMap<String, Employees> data) {
		this.employees = data;
	}

	public void setLogs(ArrayList<String> logData) {
		this.logs = logData;
	}
	
	public ArrayList<String> getLogs() {
		return logs;
	}
	
	public HashMap<String, Employees> getEmployeeData() {
		return employees;
	}
	

	
	
	
	
	
	
	public void refreshUI() {
	    lblCurrent.revalidate();
	    lblCurrent.repaint();
	    updateButtonPermissions(); 
	}
	
	
	
	
	public void updateButtonPermissions() {
		
	    String currentRole = lblCurrent.getText();

	    switch (currentRole) {
	        case "Admin":
	            btnAdd.setEnabled(true);
	            btnUpdate.setEnabled(true);
	            btnDelete.setEnabled(true);
	            btnAuditLogs.setEnabled(true);
	            btnView.setEnabled(true);
	            btnSignout.setEnabled(true);
	            break;

	        case "Manager":
	        	
	        	
	        	btnUpdate.doClick();
	            btnAdd.setEnabled(false);
	            btnUpdate.setEnabled(true);
	            btnDelete.setEnabled(false);
	            btnAuditLogs.setEnabled(true);
	            btnView.setEnabled(true);
	            btnSignout.setEnabled(true);
	            break;

	        case "Employee":
	        	btnView.doClick();
	            btnAdd.setEnabled(false);
	            btnUpdate.setEnabled(false);
	            btnDelete.setEnabled(false);
	            btnAuditLogs.setEnabled(true);
	            btnView.setEnabled(true);
	            btnSignout.setEnabled(true);
	            break;

	        default:
	            btnAdd.setEnabled(false);
	            btnUpdate.setEnabled(false);
	            btnDelete.setEnabled(false);
	            btnAuditLogs.setEnabled(false);
	            btnView.setEnabled(false);
	            btnSignout.setEnabled(true);
	            break;
	    }
	}


	public void refreshUI1() {
	    lblCurrent.revalidate();
	    lblCurrent.repaint();
	    updateButtonPermissions();
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);
	    DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
	    model1.setRowCount(0);
	    DefaultTableModel model2 = (DefaultTableModel) table_2.getModel();
	    model2.setRowCount(0);
	    DefaultTableModel model3 = (DefaultTableModel) table_3.getModel();
	    model3.setRowCount(0);
	    
	}

	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee frame = new Employee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void sortTable1(JTable table, boolean ascending) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    int rowCount = model.getRowCount();
	    int columnCount = model.getColumnCount();
	    
	    if (rowCount > 1) {
	        ArrayList<Object[]> rows = new ArrayList<>();

	        for (int i = 0; i < rowCount; i++) {
	            Object[] row = new Object[columnCount];
	            for (int j = 0; j < columnCount; j++) {
	                row[j] = model.getValueAt(i, j);
	            }
	            rows.add(row);
	        }

	        rows.sort(new Comparator<Object[]>() {
	            @Override
	            public int compare(Object[] row1, Object[] row2) {
	                String name1 = row1[0].toString();
	                String name2 = row2[0].toString();
	                return ascending ? name1.compareToIgnoreCase(name2) : name2.compareToIgnoreCase(name1);
	            }
	        });

	        model.setRowCount(0);
	        for (Object[] row : rows) {
	            model.addRow(row);
	        }
	    }
	}
	
	
	private void filterTableByRole(JTable table, String role) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    table.setRowSorter(sorter);

	    if (role.equals("All")) {
	        sorter.setRowFilter(null);
	    } else {
	        String regex = "\\[" + role + "\\]";
	        sorter.setRowFilter(RowFilter.regexFilter(regex, 0)); 
	    }
	}


	/**
	 * Create the frame.
	 */
	public Employee() {
		setTitle("Accenture Employee Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 455);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 0, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 138, 418);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblCurrent = new JLabel("Admin"); 
		lblCurrent.setForeground(new Color(255, 255, 255));
		lblCurrent.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblCurrent.setBounds(23, 25, 150, 24); 
		panel.add(lblCurrent); 

	
		
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnAdd.setBounds(23, 75, 85, 21);
		panel.add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		btnUpdate.setBounds(23, 108, 85, 21);
		panel.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(2);
			}
		});
		btnDelete.setBounds(23, 139, 85, 21);
		panel.add(btnDelete);
		
		btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnView.setBounds(23, 170, 85, 21);
		panel.add(btnView);
		
		btnAuditLogs = new JButton("Audit Logs");
		btnAuditLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnAuditLogs.setBounds(23, 201, 85, 21);
		panel.add(btnAuditLogs);
		
		btnSignout = new JButton("Sign out");
		btnSignout.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		
		        String currentRole = lblCurrent.getText();
		        String timestamp = java.time.LocalDateTime.now().toString();
		        
		        logs.add("[" + currentRole + "] Logged out at " + timestamp);

		        Accenture acc = new Accenture();
		        acc.setEmployeeData(employees);
		        acc.setLogs(logs);
		        acc.setVisible(true);
		        dispose();
		        

		        dispose();
		    }
		});



		
		btnSignout.setBounds(23, 375, 85, 21);
		panel.add(btnSignout);
		
		
		
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(137, -22, 521, 440);
		contentPane.add(tabbedPane);
		
		JPanel panelAdd = new JPanel();
		panelAdd.setBackground(new Color(64, 0, 64));
		tabbedPane.addTab("New tab", null, panelAdd, null);
		panelAdd.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(108, 101, 45, 13);
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblName.setForeground(new Color(255, 255, 255));
		panelAdd.add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(108, 122, 303, 19);
		panelAdd.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(108, 151, 45, 13);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 12));
		panelAdd.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(108, 172, 303, 19);
		tfEmail.setColumns(10);
		panelAdd.add(tfEmail);
		
		tfDepartment = new JTextField();
		tfDepartment.setBounds(108, 226, 303, 19);
		tfDepartment.setColumns(10);
		panelAdd.add(tfDepartment);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setBounds(108, 205, 76, 13);
		lblDepartment.setForeground(Color.WHITE);
		lblDepartment.setFont(new Font("Segoe UI", Font.BOLD, 12));
		panelAdd.add(lblDepartment);
		
		JButton btnAddEmployee = new JButton("Add");
		btnAddEmployee.setBounds(326, 267, 85, 21);
		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = tfName.getText();
				String email = tfEmail.getText();
				String department = tfDepartment.getText();
				
		        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		        
		        if (!email.matches(emailRegex)) {
		            JOptionPane.showMessageDialog(null, "Invalid email format. Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            employees.put(name, new Employees(name, email, department));
		            JOptionPane.showMessageDialog(null, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		            tfName.setText("");
		            tfEmail.setText("");
		            tfDepartment.setText("");
		            refreshUI();
		        }
				employees.put(name, new Employees(name, email, department));
	
	            refreshUI();
	            logs.add("[" + lblCurrent.getText() + "] Added employee: " + name + " at " + timestamp);
			}
		});
		panelAdd.add(btnAddEmployee);
		
		JButton btnClearEmployee = new JButton("Clear");
		btnClearEmployee.setBounds(231, 267, 85, 21);
		btnClearEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				logs.add("[Admin] Cleared textfields at " + timestamp);
				tfName.setText("");
				tfEmail.setText("");
				tfDepartment.setText("");
			}
		});
		panelAdd.add(btnClearEmployee);
		
		JLabel lblpicupdate_1_2 = new JLabel("");
		lblpicupdate_1_2.setBounds(0, 0, 516, 413);
		lblpicupdate_1_2.setIcon(new ImageIcon("C:\\Users\\carlos\\Pictures\\aaa.jpg"));
		panelAdd.add(lblpicupdate_1_2);
		
		JPanel panelUpdate = new JPanel();
		panelUpdate.setBackground(new Color(64, 0, 64));
		tabbedPane.addTab("New tab", null, panelUpdate, null);
		panelUpdate.setLayout(null);
		
		
		
		table = new JTable();
		table.setBounds(10, 51, 496, 195);

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addColumn("Name");
		model.addColumn("Email");
		model.addColumn("Department");


		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(37, 51, 452, 195);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 

		panelUpdate.add(scrollPane);
		
		table.setShowGrid(true);                          
		table.setGridColor(Color.GRAY);                 
		table.setShowHorizontalLines(true);              
		table.setShowVerticalLines(true);             

	
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Name: ");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1.setBounds(32, 274, 45, 13);
		panelUpdate.add(lblNewLabel_1);
		
		tfNameOp = new JTextField();
		tfNameOp.setBounds(133, 272, 175, 19);
		panelUpdate.add(tfNameOp);
		tfNameOp.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email:");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(32, 299, 45, 13);
		panelUpdate.add(lblNewLabel_1_1);
		
		tfEmailOp = new JTextField();
		tfEmailOp.setColumns(10);
		tfEmailOp.setBounds(133, 297, 175, 19);
		panelUpdate.add(tfEmailOp);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Department: ");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(32, 324, 74, 13);
		panelUpdate.add(lblNewLabel_1_1_1);
		
		tfDeptOp = new JTextField();
		tfDeptOp.setColumns(10);
		tfDeptOp.setBounds(133, 322, 175, 19);
		panelUpdate.add(tfDeptOp);
		
		JButton btnUpdateEmployee = new JButton("Update");
		btnUpdateEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tfNameOp.getText();
				String email = tfEmailOp.getText();
				String department = tfDeptOp.getText();
				employees.put(name, new Employees(name, email, department));
				refreshUI(); 
				JOptionPane.showMessageDialog(null, "Employee updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE	);
				int row = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.removeRow(row);
				
				logs.add("[" + lblCurrent.getText() + "] Added employee: " + name + " at " + timestamp);
			   
			}
		});
		btnUpdateEmployee.setBounds(404, 382, 85, 21);
		panelUpdate.add(btnUpdateEmployee);
		
		JButton btnClearUpdate = new JButton("Clear");
		btnClearUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logs.add("[" + lblCurrent.getText() + "] Cleared textfields at " + timestamp);
				tfNameOp.setText("");
				tfEmailOp.setText("");
				tfDeptOp.setText("");
			}
		});
		btnClearUpdate.setBounds(314, 382, 85, 21);
		panelUpdate.add(btnClearUpdate);
		
		JButton btnLoadUpdate = new JButton("Load");
		btnLoadUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logs.add("[" + lblCurrent.getText() + "] Loaded textfields at " + timestamp);
	
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				for (String key : employees.keySet()) {
                    Employees emp = employees.get(key);
                    model.addRow(new Object[] { emp.name, emp.email, emp.department});
                    }
			}
		});
		btnLoadUpdate.setBounds(223, 382, 85, 21);
		panelUpdate.add(btnLoadUpdate);
		
		
		

		String[] filter = {"A to Z", "Z to A"};
		JComboBox<String> comboBox = new JComboBox<>(filter);
		comboBox.setBounds(389, 20, 100, 21);
		panelUpdate.add(comboBox);

		JLabel lblpicupdate = new JLabel("");
		lblpicupdate.setIcon(new ImageIcon("C:\\Users\\carlos\\Pictures\\aaa.jpg"));
		lblpicupdate.setBounds(0, 0, 516, 413);
		panelUpdate.add(lblpicupdate);

		comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (comboBox.getSelectedItem().equals("A to Z")) {
		            sortTable1(table, true);  
		        } else if (comboBox.getSelectedItem().equals("Z to A")) {
		            sortTable1(table, false);
		        }
		    }
		});
		
	
		JPanel panelDelete = new JPanel();
		String[] filter1 = {"Newest", "Oldest"};
		JComboBox<String> comboBox1 = new JComboBox<String>(filter1);
		panelDelete.setBackground(new Color(64, 0, 64));
		tabbedPane.addTab("New tab", null, panelDelete, null);
		panelDelete.setLayout(null);
		
		table_1 = new JTable();
		table_1.setBounds(10, 33, 496, 235);
		panelDelete.add(table_1);

		DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
		model1.addColumn("Name");
		model1.addColumn("Email");
		model1.addColumn("Department");

		table_1.setShowGrid(true);               
		table_1.setGridColor(Color.BLACK);             
		table_1.setShowHorizontalLines(true);          
		table_1.setShowVerticalLines(true);           

		JScrollPane scrollPane1 = new JScrollPane(table_1);
		scrollPane1.setBounds(37, 51, 452, 195);
		scrollPane1.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		panelDelete.add(scrollPane1);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				String name = model.getValueAt(row, 0).toString();
				String email = model.getValueAt(row, 1).toString();
				String department = model.getValueAt(row, 2).toString();
				tfNameOp.setText(name);
				tfEmailOp.setText(email);
				tfDeptOp.setText(department);
			}
		});
		
		
		
		JButton btnDeleteEmployee = new JButton("Delete");
		btnDeleteEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this employee?", "Warning", dialogButton);
				if (dialogResult == JOptionPane.NO_OPTION) {
					return;
				}
				JOptionPane.showMessageDialog(null, "Employee deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				
				int row = table_1.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				String name = model.getValueAt(row, 0).toString();
				employees.remove(name);
				refreshUI(); 
		
				logs.add("[" + lblCurrent.getText() + "] Deleted employee: " + name + " at " + timestamp);
			}
		});
		btnDeleteEmployee.setBounds(404, 382, 85, 21);
		panelDelete.add(btnDeleteEmployee);
		
		JButton btnLoadDelete = new JButton("Load");
		btnLoadDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logs.add("[" + lblCurrent.getText() + "] Cleared textfields at " + timestamp);
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				model.setRowCount(0);
				for (String key : employees.keySet()) {
					Employees emp = employees.get(key);
					model.addRow(new Object[] { emp.name, emp.email, emp.department });
				}
			}
		});
		btnLoadDelete.setBounds(309, 382, 85, 21);
		panelDelete.add(btnLoadDelete);
		
		String[] filter11 = {"A to Z", "Z to A"};
		JComboBox<String> comboBox_1 = new JComboBox<>(filter11);
		comboBox_1.setBounds(421, 21, 68, 21);
		panelDelete.add(comboBox_1);

		JLabel lblpicupdate_1 = new JLabel("");
		lblpicupdate_1.setIcon(new ImageIcon("C:\\Users\\carlos\\Pictures\\aaa.jpg"));
		lblpicupdate_1.setBounds(0, 0, 516, 413);
		panelDelete.add(lblpicupdate_1);

		comboBox_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (comboBox_1.getSelectedItem().equals("A to Z")) {
		            sortTable1(table_1, true);
		        } else if (comboBox_1.getSelectedItem().equals("Z to A")) {
		            sortTable1(table_1, false); 
		        }
		    }
		});

		
		JPanel panelView = new JPanel();
		panelView.setBackground(new Color(64, 0, 64));
		tabbedPane.addTab("New tab", null, panelView, null);
		panelView.setLayout(null);
		
		table_2 = new JTable();
		table_2.setBounds(10, 38, 496, 313);
		panelView.add(table_2);

		DefaultTableModel model2 = (DefaultTableModel) table_2.getModel();
		model2.addColumn("Name");
		model2.addColumn("Email");
		model2.addColumn("Department");


		table_2.setShowGrid(true);                     
		table_2.setGridColor(Color.BLACK);            
		table_2.setShowHorizontalLines(true);       
		table_2.setShowVerticalLines(true);     

		JScrollPane scrollPane2 = new JScrollPane(table_2);
		scrollPane2.setBounds(40, 51, 450, 196);
		scrollPane2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panelView.add(scrollPane2);

		
		
		JButton btnLoadView = new JButton("Load");
		btnLoadView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logs.add("[" + lblCurrent.getText() + "] Cleared textfields at " + timestamp);
                DefaultTableModel model = (DefaultTableModel) table_2.getModel();
                model.setRowCount(0);
                for (String key : employees.keySet()) {
                    Employees emp = employees.get(key);
                    model.addRow(new Object[] { emp.name, emp.email, emp.department
                    		});
                    }
			}
		});
		btnLoadView.setBounds(405, 382, 85, 21);
		panelView.add(btnLoadView);
		
		String[] filter111 = {"A to Z", "Z to A"};
		JComboBox<String> comboBox_2 = new JComboBox<>(filter111);
		comboBox_2.setBounds(422, 20, 68, 21);
		panelView.add(comboBox_2);

		JLabel lblpicupdate_1_1 = new JLabel("");
		lblpicupdate_1_1.setIcon(new ImageIcon("C:\\Users\\carlos\\Pictures\\aaa.jpg"));
		lblpicupdate_1_1.setBounds(0, 0, 516, 413);
		panelView.add(lblpicupdate_1_1);

		comboBox_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (comboBox_2.getSelectedItem().equals("A to Z")) {
		            sortTable1(table_2, true); 
		        } else if (comboBox_2.getSelectedItem().equals("Z to A")) {
		            sortTable1(table_2, false); 
		        }
		    }
		});

		
		JPanel panelAuditLogs = new JPanel();
		panelAuditLogs.setBackground(new Color(64, 0, 64));
		tabbedPane.addTab("New tab", null, panelAuditLogs, null);
		panelAuditLogs.setLayout(null);

		table_3 = new JTable();
		DefaultTableModel model3 = new DefaultTableModel();
		model3.addColumn("Audit Logs");  

		table_3.setModel(model3);

		table_3.setShowGrid(true);
		table_3.setGridColor(Color.BLACK);
		table_3.setShowHorizontalLines(true);
		table_3.setShowVerticalLines(false); 

		JScrollPane scrollPane3 = new JScrollPane(table_3);
		scrollPane3.setBounds(39, 53, 451, 195);
		panelAuditLogs.add(scrollPane3);

		
		
		
		JButton btnLoadAuditLogs = new JButton("Load");
		btnLoadAuditLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table_3.getModel();
                model.setRowCount(0);
                for (String log : logs) {
                    model.addRow(new Object[] { log });
                    }
                 }
		});
		btnLoadAuditLogs.setBounds(405, 382, 85, 21);
		panelAuditLogs.add(btnLoadAuditLogs);
		
		String[] filter1111 = {"All", "Admin", "Manager", "Employee"};
		JComboBox<String> comboBox_3 = new JComboBox<>(filter1111);
		comboBox_3.setBounds(390, 22, 100, 21);
		panelAuditLogs.add(comboBox_3);

		JLabel lblpicupdate_1_1_1 = new JLabel("");
		lblpicupdate_1_1_1.setIcon(new ImageIcon("C:\\Users\\carlos\\Pictures\\aaa.jpg"));
		lblpicupdate_1_1_1.setBounds(0, 0, 516, 413);
		panelAuditLogs.add(lblpicupdate_1_1_1);

		comboBox_3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String selectedRole = (String) comboBox_3.getSelectedItem();
		        filterTableByRole(table_3, selectedRole);
		    }
		});
	}

	protected void sortTable(JScrollPane scrollPane, boolean b) {
		// TODO Auto-generated method stub
		
	}

	protected void sortTable(JTable table_32, boolean b) {
		// TODO Auto-generated method stub
		
	}

	public JLabel getLblCurrent() {
		return lblCurrent;
	}

	public void setLblCurrent(JLabel lblCurrent) {
		this.lblCurrent = lblCurrent;
	}

	public Window getPanelAdd() {
		// TODO Auto-generated method stub
		return null;
	}

	public Window getPanelDelete() {
		// TODO Auto-generated method stub
		return null;
	}

	public Window getPanelUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	public Component getBtnUpdate() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTimestamp() {
		// TODO Auto-generated method stub
		return null;
	}
}
