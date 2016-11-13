<?php
function gerarNovaMatriz($n){
	$novaMatriz = array();
	for ($i=0; $i < $n; $i++) {
        for ($j=0; $j < $n; $j++) {
            $novaMatriz[$i][$j] = 0.0;
        }
	}
	return $novaMatriz;
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

function executar() {
    list($usec, $sec) = explode(' ', microtime());
    $tempoInicioOcupado = (float) $sec + (float) $usec;

    $cargas = array(100, 200, 300, 400, 500, 600, 700, 800, 900, 1000);
    $T = 900; // 15 minutos

    for ($index=0; $index < count($cargas); $index++) {
      list($usec1, $sec1) = explode(' ', microtime());
      $script_start = (float) $sec1 + (float) $usec1;

      $C = $cargas[$index];
      $matrizA = gerarMatrizPopulada($C);
      $matrizB = gerarMatrizPopulada($C);
      $resultado = multiplicacao($matrizA, $matrizB, $C);

      list($usec1, $sec1) = explode(' ', microtime());
      $script_end = (float) $sec1 + (float) $usec1;

      $B = round($script_end - $script_start, 3);

      echo "\n";
      echo "Processamento: " . ($index+1) . " | Carga (C): " . $C . " | Tempo (B): " . round($B,3) ."segs";

      if(($index+1) === count($cargas)){
        print "\n \n \t Carga Trabalhada";
        print "\n \n";
        echo "Processamento: " . ($index+1) . " | Carga (C): " . $C . " | Tempo (B): " . round($B,3) . "segs";
        echo "\n";

        $μ = $C / $B;
        echo "Taxa média de atendimento (μ = C/B): " . round($μ,3) . "carga/segs";
        echo "\n";

        $S = 1 / $μ;
        echo "Tempo médio de atendimento (S = 1/μ): " . round($S,3) . "segs/carga";
        echo "\n";

        $X = $C / $T;
        echo "Taxa média de processamento (X = C/T): " . round($X,3) . "carga/segs";
        echo "\n";

        $λ = $X;
        echo "Hipótese do equilíbrio de fluxo (λ = X): " . round($λ,3) . "carga/segs";
        echo "\n";

        $U = $S * $λ;
        echo "Teorema da taxa de processamento (U = S × λ): " . round($U,3) . " ou " . round($U,3)*100 . "%";
        echo "\n";

        $R = $S / (1 - $U);
        echo "Tempo médio de resposta [R = S/(1 - U)]: " . round($R,3) . "segs/carga";
        echo "\n";

        $W = $R - $S;
        echo "Tempo médio de espera (W = R – S): " . round($W,3) . "segs/carga";
        echo "\n";

        $λsat = 1 / $S;
        echo "Identificação da Saturação (λsat = 1/S): " . round($λsat,3) . "carga/segs";
        echo "\n";

        $cargaSaturada = $λsat * $T;
        echo "Tamanho da carga saturada (λsat x T): " . round($cargaSaturada,3) . "carga";
      }
    }

    list($usec, $sec) = explode(' ', microtime());
    $tempoFinalOcupado = (float) $sec + (float) $usec;

    $tempoCalculado =  round($tempoFinalOcupado - $tempoInicioOcupado, 3);
    echo "\n";
    echo "Tempo total ocupado (B): " . round($tempoCalculado/60,3) . " minutos ou " . $tempoCalculado . "segundos";
    echo "\n";
    echo "Tempo Observado (T) " . ($T/60) . " minutos ou " . $T . "segundos";
}
executar();