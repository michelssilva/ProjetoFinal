package br.gov.sp.fatec.cripto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Seguranca {

	public String criptografarSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest algoritimo = MessageDigest.getInstance("SHA-512");
		byte messageDigestSenhaAdmin[] = algoritimo.digest(senha.getBytes("UTF-8"));

		StringBuilder hexStringSenha = new StringBuilder();
		for (byte b : messageDigestSenhaAdmin) {
			hexStringSenha.append(String.format("%02X", 0xFF & b));
		}
		String senhaHex = hexStringSenha.toString();

		return senhaHex;
	}

}
