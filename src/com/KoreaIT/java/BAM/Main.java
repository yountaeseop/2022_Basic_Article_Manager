package com.KoreaIT.java.BAM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==���α׷� ����==");

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("��ɾ� ) ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;
			}
		}

		System.out.println("==���α׷� ��==");
		sc.close();
	}
}