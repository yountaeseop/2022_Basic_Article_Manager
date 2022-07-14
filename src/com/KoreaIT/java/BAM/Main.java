package com.KoreaIT.java.BAM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		String command = sc.nextLine();

		System.out.printf("입력된 명령어 : %s\n", command);

		System.out.println("==프로그램 끝==");
		sc.close();
	}
}