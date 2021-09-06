package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectReaderMain {

	public static void main(String[] args) throws  IOException, ClassNotFoundException {

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("result2.txt"));
		
		PersonDTO dto = (PersonDTO) ois.readObject(); // �ڽ� = (�ڽ�)�θ�
		
		System.out.println("�̸� "+dto.getName());
		System.out.println("���� "+dto.getAge());
		System.out.println("Ű "+dto.getHeight());
		
		

	}

}
