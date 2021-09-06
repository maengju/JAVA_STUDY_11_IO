package io;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ScoreImpl implements Score {
	private List<ScoreDTO> list;
	
	public ScoreImpl() {
		list = new ArrayList<ScoreDTO>();
	}
	
	@Override
	public void input(ScoreDTO dto) {
		list.add(dto);
		System.out.println(list);
		JOptionPane.showMessageDialog(null, "등록 완료");
	}
	
	@Override
	public void print(DefaultTableModel model) {
		model.setRowCount(0);
		
		for(ScoreDTO dto : list) {
			Vector<String> v = new Vector<String>();
			v.add(dto.getHak());
			v.add(dto.getName());
			v.add(dto.getKor() +  "");
			v.add(dto.getEng() + "");
			v.add(dto.getMath() + "");
			v.add(dto.getTot() + "");
			v.add(String.format("%.2f", dto.getAvg()));
			
			model.addRow(v);
		}//for
		
	}

	@Override
	public void search(DefaultTableModel model) {
		String hak = JOptionPane.showInputDialog(null, 
				"학번을 입력해주세요", 
				"검색", 
				JOptionPane.QUESTION_MESSAGE);
		if(hak==null || hak.length()==0) return;
		
		int sw=0;
		for(ScoreDTO dto : list) {
			if (hak.equals(dto.getHak())) {
				model.setRowCount(0);
				
				Vector<String> v = new Vector<String>();
				v.add(dto.getHak());
				v.add(dto.getName());
				v.add(dto.getKor() +  "");
				v.add(dto.getEng() + "");
				v.add(dto.getMath() + "");
				v.add(dto.getTot() + "");
				v.add(String.format("%.2f", dto.getAvg()));
				
				model.addRow(v);
				sw=1;
			}
		}//for
		
		if(sw==0)
			JOptionPane.showMessageDialog(null, "찾고자 하는 학번이 없습니다");
	}

	@Override
	public void tot_desc() { //List안의 ScoreDTO를 내림차순(Comparable, Comparator)
		Comparator<ScoreDTO> com = new Comparator<ScoreDTO>() {
			@Override
			public int compare(ScoreDTO s1, ScoreDTO s2) {
//				if(s1.getTot() < s2.getTot()) return 1;
//				else if(s1.getTot() > s2.getTot()) return -1;
//				else return 0;
				
				return s1.getTot() < s2.getTot() ? 1 : -1;
			}
		};
		
		Collections.sort(list, com);		
	}

	@Override
	public void save() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(null);
		
		File file = null;
		if(result == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}
		//--------------
		if(file == null) return;
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			
			//두번째 방법 - ArrayList 담겨있는 ScoreDTO의 개수를 저장
			oos.writeInt(list.size());
			
			for(ScoreDTO dto : list) {
				oos.writeObject(dto);
			}//for
			
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(null);
		
		File file = null;
		if(result == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
		}
		//--------------
		if(file == null) return;
		
		list.clear();
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			
			//첫번째 방법
//			while(true) {
//				try {
//					ScoreDTO dto = (ScoreDTO)ois.readObject();
//					list.add(dto);
//					
//				}catch(EOFException e) {
//					break;
//				}
//			}//while
			
			//두번째 방법 - 파일에서 ScoreDTO의 개수가 몇개인지 먼저 읽는다
			int size = ois.readInt();
			for(int i=0; i<size; i++) {
				ScoreDTO dto = (ScoreDTO)ois.readObject();
				list.add(dto);
			}			
			
			ois.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
}













