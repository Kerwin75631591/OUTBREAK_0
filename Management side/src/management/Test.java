package management;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Test {
	public static void main(String[] args) {
		JFrame frame=new JFrame("JSplitPanel example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container=frame.getContentPane();
		
		JScrollPane sp=new JScrollPane(new JTextArea());
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		container.add(sp);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}
}
