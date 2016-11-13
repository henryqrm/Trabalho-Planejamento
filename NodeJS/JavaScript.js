'use strict';

// Cria uma matriz de Float64 com n posições valoradas em zero
function gerarNovaMatriz(n) {
    const novaMatriz = [];
    for (let i = 0; i < n; i++) {
        novaMatriz[i] = new Float64Array(n);
    }
    return novaMatriz;
}

function multiplicação(matrizA, matrizB, n) {
    const matrizResultado = gerarNovaMatriz(n);
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            let mult = 0;
            for (let k = 0; k < n; k++) {
                mult += matrizA[i][k] * matrizB[j][k];
            }
            matrizResultado[i][j] = mult;
        }
    }
    return matrizResultado;
}

function gerarMatrizPopulada(n) {
    const matriz = gerarNovaMatriz(n);
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            const numeroSinistro = ((i / n) / n) + ((j / n) / n) + 1;
            matriz[i][j] = numeroSinistro;
        }
    }
    return matriz;
}

function executar() {
    const tempoInicioOcupado = new Date();
    const cargas = [100, 200, 300, 400, 500, 600, 700, 800, 900, 1000];
    const T = 300; // 5 minutos

    for (var index = 0; index < cargas.length; index++) {

        const inicio = new Date();

        const C = cargas[index];

        const matrizA = gerarMatrizPopulada(C);

        const matrizB = gerarMatrizPopulada(C);

        const resultado = multiplicação(matrizA, matrizB, C);

        const B = (new Date() - inicio)/1000;

        console.log(`Processamento: ${index+1} | Carga (C): ${C} | Tempo (B): ${B.toFixed(3)}seg`);

        if ((index + 1) === cargas.length) { // Mostra a última carga, que é a que vamos trabalhar.
            console.log('\n\n\tCarga Trabalhada');

            console.log(`\n\nProcessamento: ${index+1} | Carga (C): ${C} | Tempo (B): ${B.toFixed(3)}seg`);


            const μ = C / B
            console.log(`\nTaxa média de atendimento (μ = C/B): ${μ.toFixed(3)}carga/seg`);

            const S = 1 / μ;
            console.log(`Tempo médio de atendimento (S = 1/μ): ${S.toFixed(3)}seg/carga`);

            const X = C / T;
            console.log(`Taxa média de processamento (X = C/T): ${X.toFixed(3)}carga/seg`);

            const λ = X;
            console.log(`Hipótese do equilíbrio de fluxo (λ = X): ${λ.toFixed(3)}carga/seg`);

            const U = S * λ;
            console.log(`Teorema da taxa de processamento (U = S × λ): ${U.toFixed(3)} ou ${U.toFixed(3)*100}%`);

            const R = S / (1 - U);
            console.log(`Tempo médio de resposta [R = S/(1 - U)]: ${R.toFixed(3)}seg/carga`);

            const W = R - S;
            console.log(`Tempo médio de espera (W = R – S): ${W.toFixed(3)}seg/carga`);


            const λsat = 1 / S;
            console.log(`Identificação da Saturação (λsat = 1/S): ${λsat.toFixed(3)}carga/seg`);

            const cargaSaturada = λsat * T;
            console.log(`Tamanho da carga saturada (λsat x T): ${cargaSaturada.toFixed(3)}carga`);
        }
    }

    console.log(`Tempo total ocupado (B) ${((new Date() - tempoInicioOcupado)/60000).toFixed(3)} minutos ou ${((new Date() - tempoInicioOcupado)/1000).toFixed(3)}seg`);
    console.log(`Tempo Observado (T) 5 minutos ou ${T}segs`);
}

executar();