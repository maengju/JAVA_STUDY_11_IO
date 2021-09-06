package io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteStream {
	
	

	public static void main(String[] args) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("data.txt"));
		int data;
		
		
		while ((data = bis.read()) != -1) { //1개의 문자식 읽어온다 ( 더이상 읽을 문자가 ㅇ벗으면 -1
			System.out.print((char)data);
		}//while
		
		bis.close();
	}

}
