import array
import sys
import datetime
import time

def gerarNovaMatriz(n):
    return [array.array("d", [0.0]) * n for _ in range(0,n)]

def multiplicacao(matrizA, matrizB, n):
    matrizResultado = gerarNovaMatriz(n)
    for i in range(0,n):
        for j in range(0,n):
            mult = 0.0
            for k in range(0,n):
                mult += matrizA[i][k] * matrizB[j][k]
            matrizResultado[i][j] = mult
    return matrizResultado

def gerarMatrizPopulada(n):
    matriz = gerarNovaMatriz(n)
    for i in range(0,n):
        for j in range(0,n):
            numeroSinistro = ((i / n) / n) + ((j / n) / n) + 1
            matriz[i][j] = numeroSinistro
    return matriz

def executar():
    tempoInicioOcupado = time.time();
    cargas = [100, 200, 300, 400, 500, 600, 700, 800, 900, 1000];
    T = 900;

    for i in range(0, len(cargas)):
        inicio = time.time()
        
        C = cargas[i];

        matrizA = gerarMatrizPopulada(C);

        matrizB = gerarMatrizPopulada(C);

        resultado = multiplicacao(matrizA, matrizB, C);

        B = time.time() - inicio;

        print ("Processamento:", (i+1)," | Carga (C):", C," | Tempo (B): ", round(B,3),"seg");

        if((i+1) == len(cargas)):
            print ("\n\n\tCarga Trabalhada")
            print ("Processamento:", (i+1)," | Carga (C):", C," | Tempo (B): ", round(B,3),"seg");
            μ = C / B;
            print ("\nTaxa média de atendimento (μ = C/B): ",round(μ,3),"carga/seg");
            S = 1 / μ;
            print ("Tempo médio de atendimento (S = 1/μ): ",round(S,3),"seg/carga");

            X = C / T;
            print ("Taxa média de processamento (X = C/T): ",round(X,3),"carga/seg");

            λ = X;
            print ("Hipótese do equilíbrio de fluxo (λ = X): ",round(λ,3),"carga/seg");

            U = S * λ;
            print ("Teorema da taxa de processamento (U = S × λ): ",round(U*100,3)," ou ",round(U,3),"%");

            R = S / (1 - U);
            print ("Tempo médio de resposta [R = S/(1 - U)]: ",round(R,3),"seg/carga");

            W = R - S;
            print ("Tempo médio de espera (W = R – S): ",round(W,3),"seg/carga");


            λsat = 1 / S;
            print ("Identificação da Saturação (λsat = 1/S): ",round(λsat,3),"carga/seg");

            cargaSaturada = λsat * T;
            print ("Tamanho da carga saturada (λsat x T): ",round(cargaSaturada,3),"carga");

    print ("Tempo total ocupado (B) ", round((time.time() - tempoInicioOcupado)/60, 3),"minutos ou ",round(time.time() - tempoInicioOcupado, 3),"seg");
    print ("Tempo Observado (T) 5 minutos ou ",T,"segs");


def main(argv):
    executar()

if __name__ == "__main__":
    main(sys.argv)