package projeto;

import java.util.Scanner;

public class JogoDaVelha {
	private static final int TAMANHO = 3;
	private static char[][] tabuleiro = new char[TAMANHO][TAMANHO];
	private static char jogadorAtual = 'X';

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;

		System.out.println("Bem Vindo ao Jogo da Velha!\nCriado por Thiago Stefano Garcia.\nRA: 22318463-5.");
		System.out.println("O jogador deve escolher a linha e a coluna desejada:");
		while (continuar) {
			inicializarTabuleiro();
			boolean jogoEmAndamento = true;

			while (jogoEmAndamento) {
				mostrarTabuleiro();
				System.out.println("Jogador " + jogadorAtual + ", escolha a linha e coluna (1, 2 ou 3):");

				int linha = -1, coluna = -1;
				boolean entradaValida = false;

				while (!entradaValida) {
					try {
						System.out.println("Escolha a linha:");
						linha = scanner.nextInt() - 1;
						System.out.println("Escolha a coluna:");
						coluna = scanner.nextInt() - 1;

						if (linha < 0 || linha >= TAMANHO || coluna < 0 || coluna >= TAMANHO) {
							System.out.println("Coordenadas fora do limite! Tente novamente. Digite 1, 2 ou 3.");
						} else if (tabuleiro[linha][coluna] != '-') {
							System.out.println("Posição já ocupada! Tente novamente.");
						} else {
							entradaValida = true;
						}
					} catch (Exception e) {
						System.out.println("Entrada inválida! Digite 1, 2 ou 3.");
						scanner.nextLine();
					}
				}

				tabuleiro[linha][coluna] = jogadorAtual;
				if (verificarVitoria()) {
					mostrarTabuleiro();
					System.out.println("Jogador " + jogadorAtual + " venceu!");
					jogoEmAndamento = false;
				} else if (verificarEmpate()) {
					mostrarTabuleiro();
					System.out.println("Empate!");
					jogoEmAndamento = false;
				} else {
					trocarJogador();
				}
			}

			System.out.println("Deseja jogar novamente? (s/n)");
			continuar = scanner.next().toLowerCase().charAt(0) == 's';			
			if (continuar) {
				System.out.println("Iniciando novo jogo...");
			}
		}
		scanner.close();
		System.out.println("Obrigado por jogar! Se possível me avalie com a nota máxima.");
	}

	private static void inicializarTabuleiro() {
		for (int i = 0; i < TAMANHO; i++) {
			for (int j = 0; j < TAMANHO; j++) {
				tabuleiro[i][j] = '-';
			}
		}
	}

	private static void mostrarTabuleiro() {
		for (int i = 0; i < TAMANHO; i++) {
			for (int j = 0; j < TAMANHO; j++) {
				System.out.print(tabuleiro[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void trocarJogador() {
		jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
	}

	private static boolean verificarVitoria() {
		for (int i = 0; i < TAMANHO; i++) {
			if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) {
				return true;
			}
			if (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual) {
				return true;
			}
		}
		if (tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) {
			return true;
		}
		if (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual) {
			return true;
		}
		return false;
	}

	private static boolean verificarEmpate() {
		for (int i = 0; i < TAMANHO; i++) {
			for (int j = 0; j < TAMANHO; j++) {
				if (tabuleiro[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}
}
