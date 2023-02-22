import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class guiclass implements ActionListener{

	private JFrame frmRedBlackTree;
	private JTextField addField;
	private JTextField deleteField;
	private JButton btnClear;
	private JButton btnDelete;
	private JButton btnAdd;
	private RedBlackTree tree=new RedBlackTree();
	private JPanel panel;

	
	  //Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiclass window = new guiclass();
					window.frmRedBlackTree.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 // Create the application.
	
	public guiclass() {
		initialize();
	}

	
	 // Initialize the contents of the frame.
	 
	
	private void initialize() {
		frmRedBlackTree = new JFrame();
		frmRedBlackTree.setTitle("Red Black Tree Gui");
		frmRedBlackTree.setBounds(150, 100, 1200, 800);
		frmRedBlackTree.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRedBlackTree.getContentPane().setLayout(null);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(0, 100, 0));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Added");
				int s=Integer.parseInt(addField.getText());
				System.out.println(s);
				tree.insert(s);
				addField.setText(null);
				panel.removeAll();
				printGUI(tree.getRoot(),700,50,300);
			}
		});
		btnAdd.setBounds(315, 729, 146, 23);
		frmRedBlackTree.getContentPane().add(btnAdd);
		
		addField = new JTextField();
		addField.setBounds(66, 730, 239, 20);
		frmRedBlackTree.getContentPane().add(addField);
		addField.setColumns(10);
		
		deleteField = new JTextField();
		deleteField.setBounds(490, 730, 207, 20);
		frmRedBlackTree.getContentPane().add(deleteField);
		deleteField.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(178, 34, 34));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int s=Integer.parseInt(deleteField.getText());
				tree.delete(s);
				deleteField.setText(null);
				panel.removeAll();
				printGUI(tree.getRoot(),700,50,300);
			}
		});
		btnDelete.setBounds(718, 729, 126, 23);
		frmRedBlackTree.getContentPane().add(btnDelete);
		
		btnClear = new JButton("Clear");
		btnClear.setBackground(new Color(255, 255, 0));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnClear) {
					tree.clear();
					panel.removeAll();
					
				}
				
				
				
			}
		});
		btnClear.setBounds(900, 729, 146, 23);
		frmRedBlackTree.getContentPane().add(btnClear);
		
		 panel = new JPanel();
		panel.setBounds(20, 11, 1439, 695);
		panel.setLayout(null);
		frmRedBlackTree.getContentPane().add(panel);
	}

public void printGUI(Node n,int x,int y,int m) {
	if(n.data != 0) {
		System.out.println("ok");
		String l=Integer.toString(n.data);
		JLabel label=new JLabel(l);
		label.setBounds(x,y,50,50);
		label.setOpaque(true);
		label.setForeground(Color.white);
		label.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));
		if(n.color==1) {
			label.setBackground(Color.RED);
			
		}
		else {
			label.setBackground(Color.BLACK);
		}
		System.out.println(l);
		panel.add(label);
		if(n.left!=null) {
		printGUI(n.left,x-m,y+80,m/2);}
		if(n.right!=null) {
		printGUI(n.right,x+m,y+80,m/2);}
	}
	
}
	@Override
	
	public void actionPerformed(ActionEvent e) {

		
		
		
	}
}
