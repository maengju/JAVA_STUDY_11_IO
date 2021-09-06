package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectWriteMain {

	public static void main(String[] args) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("result2.txt"));
		
		PersonDTO dto = new PersonDTO();
		dto.setName("ȫ�浿");
		dto.setAge(25);
		dto.setHeight(185.3);
		
		
		oos.writeObject(dto);
		oos.close();
	}

}
