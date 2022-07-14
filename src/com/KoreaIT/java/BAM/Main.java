package com.KoreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==���α׷� ����==");

		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;

		List<Article> articles = new ArrayList<>();

		while (true) {

			System.out.printf("��ɾ� ) ");
			String cmd = sc.nextLine().trim();
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

				Article article = new Article(id, title, body);

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

class Article {
	int id;
	String title;
	String body;

	Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
}