package com.project.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Props {

	// Nombre original del archivo

	public static String saveFile(MultipartFile multiPart, String route) {

		String originalName = multiPart.getOriginalFilename();
		originalName.replace(" ", "-");
		String finalName = randomAlphaNumberic(8) + originalName;
		try {
			// Formar el nombre del archivo para guardarlo

			File imageFile = new File(route, finalName);
			// Guardamos fisicamente

			multiPart.transferTo(imageFile);
			return finalName;

		} catch (IOException e) {
			System.out.println("Error :" + e.getMessage());
			return null;
		}

	}

	// Creamos caracteres aelatorios al archivo img subida para que no haya
	// repetidos
	public static String randomAlphaNumberic(int count) {
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(character));
		}
		return builder.toString();
	}

}
