package br.com.senai.provaJava;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GravacaoTxt {
	
	public void gravarArq(String textoGravar) {
		Path p = Paths.get("C://temp//Banco.txt" );
		
		try {
			Files.write(p, textoGravar.getBytes(StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> lerArq(){
		Path p = Paths.get("C://temp//Banco.txt");
		try {
			return Files.readAllLines(p, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
