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
		//JLabel 생성
		hakL = new JLabel("학번");
		nameL = new JLabel("이름");
		korL = new JLabel("국어");
		engL = new JLabel("영어");
		mathL = new JLabel("수학");
		
		//JTextField 생성
		hakT = new JTextField("", 22);
		nameT = new JTextField("", 22);
		korT = new JTextField("", 22);
		engT = new JTextField("", 22);
		mathT = new JTextField("", 22);
		
		//JButton 생성
		inputBtn = new JButton("입력");
		printBtn = new JButton("출력");
		searchBtn = new JButton("학번검색");
		rankBtn = new JButton("순위");
		saveBtn = new JButton("파일저장");
		loadBtn = new JButton("파일읽기");
		
		//JTable의 타이틀
		Vector<String> vector = new Vector<String>();
		vector.add("학번");
		vector.add("이름");
		vector.add("국어");
		vector.add("영어");
		vector.add("수학");
		vector.add("총점");
		vector.add("평균");

		model = new DefaultTableModel(vector, 0);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		
		score = new ScoreImpl();
		
		//West - 왼쪽
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
		
		//이벤트
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
			input();//호출
			//score.print(model);
			
		}else if(e.getSource() == printBtn) {
			score.print(model);
			
		}else if(e.getSource() == searchBtn) {
			score.search(model);
			
		}else if(e.getSource() == rankBtn) {
			score.tot_desc(); //총점으로 내림차순
			score.print(model);
			
		}else if(e.getSource() == saveBtn) {
			score.save();
			
		}else if(e.getSource() == loadBtn) {
			score.load();
			score.print(model);
		}
	}//actionPerformed(ActionEvent e)

	public void input() {
		//JTextField에 입력된 데이터 얻기
		String hak = hakT.getText();
		if(hak.length() == 0) { //if(hak.equals(""))
			JOptionPane.showMessageDialog(this, "학번을 입력하세요");
			return;
		}
		
		String name = nameT.getText();
		int kor = Integer.parseInt(korT.getText());
		int eng = Integer.parseInt(engT.getText());
		int math = Integer.parseInt(mathT.getText());
		
		//계산
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
		
		//첫번째 -> ScoreDTO 를 List에 담아서 JTable에 뿌리고...파일에 보관
		score.input(dto);
		//두번째 -> ScoreDTO를 가지고 ScoreDAO.java 에서 DB에 insert
		
		//초기화
		hakT.setText("");
		nameT.setText("");
		korT.setText("");
		engT.setText("");
		mathT.setText("");
	}
}














