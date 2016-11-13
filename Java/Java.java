import java.text.DecimalFormat;

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

	private static void executar() {
		long tempoInicioOcupado = System.nanoTime();

	    int cargas[] = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
	    int t = 300; // 5 minutos
	    DecimalFormat df = new DecimalFormat("0.###");

	    for (int index=0; index < cargas.length; index++) {
	      long script_start = System.nanoTime();

	      int c = cargas[index];
	      double[][] matrizA = gerarMatrizPopulada(c);
	      double[][] matrizB = gerarMatrizPopulada(c);
	      double[][] resultado = multiplicacao(matrizA, matrizB, c);

	      long script_end = (System.nanoTime() - script_start);
	      double b = (script_end * 1E-6)/1000;

	      System.out.println("Processamento: " + (index+1) + " | Carga (C): " + c + " | Tempo (B): " + df.format(b) + "segs");

	      if((index+1) == cargas.length){
	    	System.out.println("\n \n \t Carga Trabalhada");
	    	System.out.println("\n \n");
	    	System.out.println("Processamento: " + (index+1) + " | Carga (C): " + c + " | Tempo (B): " + df.format(b) + "segs");

	        double μ = c / b;
	        System.out.println("Taxa média de atendimento (μ = C/B): " + df.format(μ) + "carga/segs");

	        double s = 1 / μ;
	        System.out.println("Tempo médio de atendimento (S = 1/μ): " + df.format(s) + "segs/carga");

	        double x = c / t;
	        System.out.println("Taxa média de processamento (X = C/T): " + df.format(x) + "carga/segs");

	        double λ = x;
	        System.out.println("Hipótese do equilíbrio de fluxo (λ = X): " + df.format(λ) + "carga/segs");

	        double u = s * λ;
	        System.out.println("Teorema da taxa de processamento (U = S × λ): " + df.format(u) + " ou " + df.format((u*100)) + "%");

	        double r = s / (1 - u);
	        System.out.println("Tempo médio de resposta [R = S/(1 - U)]: " + df.format(r) + "segs/carga");

	        double w = r - s;
	        System.out.println("Tempo médio de espera (W = R – S): " + df.format(w) + "segs/carga");

	        double λsat = 1 / s;
	        System.out.println("Identificação da Saturação (λsat = 1/S): " + df.format(λsat) + "carga/segs");

	        double cargaSaturada = λsat * t;
	        System.out.println("Tamanho da carga saturada (λsat x T): " + df.format(cargaSaturada) + "carga");
	      }
	    }

	    long tempoFinalOcupado = (System.nanoTime() - tempoInicioOcupado);
	    double tempoFinalCalculado = (tempoFinalOcupado * 1E-6)/1000;

	    System.out.println("\nTempo total ocupado (B): " + df.format((tempoFinalCalculado/60)) + " minutos ou " + df.format(tempoFinalCalculado) + "segundos");
	    System.out.println("Tempo Observado (T) " + (t/60) + " minutos ou " + t + "segundos");
	}

	public static void main(String[] args) {
		executar();
	}
}