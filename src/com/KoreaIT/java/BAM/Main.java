package com.KoreaIT.java.BAM;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==���α׷� ����==");

		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;

		while (true) {

			System.out.printf("��ɾ� ) ");
			String cmd = sc.nextLine();
			if (cmd.length() == 0) {
				System.out.println("��ɾ �Է����ּ���");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();

				System.out.printf("%d�� ���� �����Ǿ����ϴ�\n", id);

			} else if (cmd.equals("article list")) {
				System.out.println("�Խù��� �����ϴ�");
			} else {
				System.out.println("�������� �ʴ� ��ɾ��Դϴ�");
			}
		}

		System.out.println("==���α׷� ��==");
		sc.close();
	}
}