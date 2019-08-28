package management;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicBorders;

public class Panel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton RefreshButton, SubmitButton,RefuseButton;
	JTextArea textArea;
	JTextField textField;
	DBConnect db;
	JLabel jl;

	public Panel() {
		db=new DBConnect();
		try {
			db.connect();
		} catch (SQLException e) {
			System.out.println("���ݿ�����ʧ��");
			e.printStackTrace();
		}
		jl = new JLabel("OUTBREAK����������");
		Font fnt = new Font("Serief", Font.ITALIC + Font.BOLD, 60);
        jl.setFont(fnt);                   //���ñ�ǩ�е�����
		this.setLayout(new FlowLayout(1,10,10));
		this.setName("inner panel");
		textArea = new JTextArea(40, 110);
		textArea.setEditable(false);
		textArea.setBorder(BasicBorders.getTextFieldBorder());
		textField = new JTextField(24);
		textField.setText("����Ҫ�޸��������Ļ����id");
		RefreshButton = new JButton("ˢ��");
		RefreshButton.addActionListener(new RefreshActionListener());
		SubmitButton = new JButton("����");
		SubmitButton.addActionListener(new SubmitActionListener());
		RefuseButton = new JButton("�ܾ�");
		RefuseButton.addActionListener(new RefuseActionListener());
		JScrollPane sp=new JScrollPane(textArea);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.add(jl);
		this.add(textField);
		this.add(SubmitButton);
		this.add(RefuseButton);
		this.add(sp);
		this.add(RefreshButton);
	}

	private class RefreshActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			ResultSet rs = null;
			String str="  id\t begintime\t\t endtime\t\t place\t name\t topic\t content\t host\t\t  PeopleNum\t ArrivalNum\t FileUrl\t \n";
			try {
				rs = db.searchMeeting();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next()) {
					str=str+"  "+rs.getString("id")+"\t"+rs.getString("begintime")+"\t"+rs.getString("endtime")+"\t"+
				rs.getString("place")+"\t"+rs.getString("name")+"\t"+rs.getString("topic")+"\t"+
				rs.getString("content")+"\t"+rs.getString("host")+"\t"+rs.getInt("PeopleNum")+"\t"+
				rs.getInt("ArrivalNum")+"\t"+rs.getString("FileUrl")+"\t"+"\n";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			textArea.setText(str);
		}
	}
	//���ͨ��
	private class SubmitActionListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			int int1=Integer.parseInt(textField.getText());
			try {
				db.updateMeeting(int1, 2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ResultSet rs = null;
			String str="  id\t time\t place\t name\t content\t host\t  PeopleNum\t Arrival\t \n";
			try {
				rs = db.searchMeeting();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next()) {
					str=str+"  "+rs.getString("id")+"\t"+rs.getString("time")+"\t"+rs.getString("place")+"\t"+
				rs.getString("name")+"\t"+rs.getString("content")+"\t"+rs.getString("host")+"\t"+
				+rs.getInt("PeopleNum")+"\t"+rs.getInt("ArrivalNum")+"\n";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			textArea.setText(str);
		}
		
		
	}
	//���δͨ��
	private class RefuseActionListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			int int1=Integer.parseInt(textField.getText());
			try {
				db.updateMeeting(int1, 3);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ResultSet rs = null;
			String str="  id\t time\t place\t name\t content\t host\t  PeopleNum\t Arrival\t \n";
			try {
				rs = db.searchMeeting();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				while(rs.next()) {
					str=str+"  "+rs.getString("id")+"\t"+rs.getString("time")+"\t"+rs.getString("place")+"\t"+
				rs.getString("name")+"\t"+rs.getString("content")+"\t"+rs.getString("host")+"\t"+
				+rs.getInt("PeopleNum")+"\t"+rs.getInt("ArrivalNum")+"\n";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			textArea.setText(str);
		}
	}
}
