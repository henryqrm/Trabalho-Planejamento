<?php
function gerarNovaMatriz($n){
	$novaMatriz = array();
	for ($i=0; $i < $n; $i++) {
        for ($j=0; $j < $n; $j++) {
            $novaMatriz[$n] = 0.0;
        }
	}
}

function multiplicacao($matrizA, $matrizB, $n) {
    $matrizResultado = gerarNovaMatriz($n);
    for ($i = 0; $i < $n; $i++) {
        for ($j = 0; $j < $n; $j++) {
            $mult = 0;
            for ($k = 0; $k < $n; $k++) {
                $mult += $matrizA[$i][$k] * $matrizB[$j][$k];
            }
            $matrizResultado[$i][$j] = $mult;
        }
    }
    return $matrizResultado;
}

function gerarMatrizPopulada($n) {
    $matriz = gerarNovaMatriz($n);
    for ($i = 0; $i < $n; $i++) {
        for ($j = 0; $j < $n; $j++) {
            $numeroSinistro = (($i / $n) / $n) + (($j / $n) / $n) + 1;
            $matriz[$i][$j] = $numeroSinistro;
        }
    }
    return $matriz;
}

function executar($n) {
    $inicio = microtime(true);

    $matrizA = gerarMatrizPopulada($n);

    $matrizB = gerarMatrizPopulada($n);

    $resultado = multiplicacao($matrizA, $matrizB, $n);

    $tempo = microtime(true) - $inicio;
    echo "Carga: " . $n . " | Tempo de execução é : " . $tempo . "ms";
    echo "\n";
}
executar(10);
executar(100);
executar(200);
executar(300);