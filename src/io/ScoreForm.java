package io;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ScoreForm extends JFrame implements ActionListener {
	private JLabel hakL, nameL, korL, engL, mathL;
	private JTextField hakT, nameT, korT, engT, mathT;
	private JButton inputBtn, printBtn, searchBtn, rankBtn, saveBtn, loadBtn;
	private DefaultTableModel model;
	private JTable table;
	private Score score;

	public ScoreForm() {
		//JLabel ����
		hakL = new JLabel("�й�");
		nameL = new JLabel("�̸�");
		korL = new JLabel("����");
		engL = new JLabel("����");
		mathL = new JLabel("����");
		
		//JTextField ����
		hakT = new JTextField("", 22);
		nameT = new JTextField("", 22);
		korT = new JTextField("", 22);
		engT = new JTextField("", 22);
		mathT = new JTextField("", 22);
		
		//JButton ����
		inputBtn = new JButton("�Է�");
		printBtn = new JButton("���");
		searchBtn = new JButton("�й��˻�");
		rankBtn = new JButton("����");
		saveBtn = new JButton("��������");
		loadBtn = new JButton("�����б�");
		
		//JTable�� Ÿ��Ʋ
		Vector<String> vector = new Vector<String>();
		vector.add("�й�");
		vector.add("�̸�");
		vector.add("����");
		vector.add("����");
		vector.add("����");
		vector.add("����");
		vector.add("���");

		model = new DefaultTableModel(vector, 0);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		
		score = new ScoreImpl();
		
		//West - ����
		JPanel hakP = new JPanel();
		hakP.add(hakL);
		hakP.add(hakT);
		JPanel nameP = new JPanel();
		nameP.add(nameL);
		nameP.add(nameT);
		JPanel korP = new JPanel();
		korP.add(korL);
		korP.add(korT);
		JPanel engP = new JPanel();
		engP.add(engL);
		engP.add(engT);
		JPanel mathP = new JPanel();
		mathP.add(mathL);
		mathP.add(mathT);
		
		JPanel p = new JPanel(new GridLayout(5, 1));
		p.add(hakP);
		p.add(nameP);
		p.add(korP);
		p.add(engP);
		p.add(mathP);

		JPanel p2 = new JPanel(new GridLayout(1, 6, 5, 5));
		p2.add(inputBtn);
		p2.add(printBtn);
		p2.add(searchBtn);
		p2.add(rankBtn);
		p2.add(saveBtn);
		p2.add(loadBtn);

		Container c = getContentPane();
		c.add("West", p);
		c.add("South", p2);
		c.add("Center", scroll);
	
		setBounds(450, 100, 700, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//�̺�Ʈ
		event();
	}//ScoreForm()
	
	public void event() {
		inputBtn.addActionListener(this);
		printBtn.addActionListener(this);
		searchBtn.addActionListener(this);
		rankBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		loadBtn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == inputBtn) {
			input();//ȣ��
			//score.print(model);
			
		}else if(e.getSource() == printBtn) {
			score.print(model);
			
		}else if(e.getSource() == searchBtn) {
			score.search(model);
			
		}else if(e.getSource() == rankBtn) {
			score.tot_desc(); //�������� ��������
			score.print(model);
			
		}else if(e.getSource() == saveBtn) {
			score.save();
			
		}else if(e.getSource() == loadBtn) {
			score.load();
			score.print(model);
		}
	}//actionPerformed(ActionEvent e)

	public void input() {
		//JTextField�� �Էµ� ������ ���
		String hak = hakT.getText();
		if(hak.length() == 0) { //if(hak.equals(""))
			JOptionPane.showMessageDialog(this, "�й��� �Է��ϼ���");
			return;
		}
		
		String name = nameT.getText();
		int kor = Integer.parseInt(korT.getText());
		int eng = Integer.parseInt(engT.getText());
		int math = Integer.parseInt(mathT.getText());
		
		//���
		int tot = kor + eng + math;
		double avg = (double)tot / 3;
		
		ScoreDTO dto = new ScoreDTO();
		dto.setHak(hak);
		dto.setName(name);
		dto.setKor(kor);
		dto.setEng(eng);
		dto.setMath(math);
		dto.setTot(tot);
		dto.setAvg(avg);
		
		//ù��° -> ScoreDTO �� List�� ��Ƽ� JTable�� �Ѹ���...���Ͽ� ����
		score.input(dto);
		//�ι�° -> ScoreDTO�� ������ ScoreDAO.java ���� DB�� insert
		
		//�ʱ�ȭ
		hakT.setText("");
		nameT.setText("");
		korT.setText("");
		engT.setText("");
		mathT.setText("");
	}
}














