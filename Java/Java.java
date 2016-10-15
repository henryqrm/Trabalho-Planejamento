public class Java {
	private static double[][] gerarNovaMatriz(int n){
		double[][] novaMatriz = new double[n][n];
		for (int i=0; i < n; i++) {
	        for (int j=0; j < n; j++) {
	            novaMatriz[i][j] = 0.0;
	        }
		}
		return novaMatriz;
	}

	private static double[][] multiplicacao(double[][] matrizA, double[][] matrizB, int n) {
	    double[][] matrizResultado = gerarNovaMatriz(n);
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            double mult = 0;
	            for (int k = 0; k < n; k++) {
	                mult += matrizA[i][k] * matrizB[j][k];
	            }
	            matrizResultado[i][j] = mult;
	        }
	    }
	    return matrizResultado;
	}

	private static double[][] gerarMatrizPopulada(int n) {
	    double[][] matriz = gerarNovaMatriz(n);
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            double numeroSinistro = ((i / n) / n) + ((j / n) / n) + 1;
	            matriz[i][j] = numeroSinistro;
	        }
	    }
	    return matriz;
	}

	private static void executar(int n) {
		long inicio = System.nanoTime();

	    double[][] matrizA = gerarMatrizPopulada(n);

	    double[][] matrizB = gerarMatrizPopulada(n);

	    double[][] resultado = multiplicacao(matrizA, matrizB, n);

	    long fim = System.nanoTime() - inicio;
	    double tempo = fim * 1E-6;
	    System.out.println("Carga: " + n + " | Tempo de execução é : " + tempo + "ms");
	}

	public static void main(String[] args) {
		executar(10);
		executar(100);
		executar(200);
		executar(300);
	}
}
