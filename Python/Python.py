import array
import sys
import datetime


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
def executar(n):
    inicio = ((datetime.datetime.now().timestamp()))

    matrizA = gerarMatrizPopulada(n)

    matrizB = gerarMatrizPopulada(n)

    resultado = multiplicacao(matrizA, matrizB, n)

    print ("n = ", n," | Tempo de execução é: ",round((((datetime.datetime.now().timestamp())) - inicio) * 1000, 2), "ms")

def main(argv):
    executar(10)
    executar(100)
    executar(200)
    executar(300)

if __name__ == "__main__":
    main(sys.argv)