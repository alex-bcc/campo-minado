<div align="center">
<h1>Campo Minado</h1>
<h4>Projeto de Grafos com aplicação no jogo Campo Minado</h4>

</div>

<h3>Equipe:
   <a href = "https://github.com/iasmin-raquel"> Iasmin Raquel </a> |
   <a href = "https://github.com/alex-bcc"> Inês Alessandra</a> |
   <a href = "https://github.com/magnosillas"> Mágno Sillas </a>
</h3>

<h1>Sobre o Projeto:</h1>

<h4>Projeto é uma implementação de grafos utilizando as regras do jogo Campo Minado a partir do uso da linguagem Java para a disciplina de Algoritmos e Estrutura de Dados 2 ministrado pelo Professor Dr. <a href = "https://github.com/igorvanderlei">Igor Vanderlei </a>, da UFAPE, referente ao período de 2022.2 com intuito de avaliação para a 2ª Verificação de Aprendizagem. O projeto tem como intuito recriar as regras do jogo do Campo Minado e resolvê-lo automaticamente através de algoritmos de busca em profundidade.</h4>

<h1>Objetivo:</h1>

<h4>O principal propósito do projeto é recriar as regras do jogo Campo Minado e criar algoritmos de busca em profundidade que possam resolver automaticamente o jogo. Portanto, o objetivo é não apenas implementar o jogo, mas também desenvolver algoritmos capazes de solucioná-lo de forma autônoma.</h4>

<h1>Como foi desenvolvido:</h1>

<h4>O jogo tem em sua implementação, primeiramente um tamanho de tabuleiro já definido, sendo o número de linhas e colunas igual 10x10, bem como a porcentagem de células que conterão minas entre 8 a 16. A matriz do tabuleiro é, então, preenchida com células vazias, e as minas são aleatoriamente distribuídas com base na porcentagem fornecida. Além disso, o código calcula o número de minas adjacentes a cada célula do tabuleiro.

Quanto a lógica de resolução do jogo, esta foi implementada principalmente no método SolveMinesweeper(). Este método é responsável por determinar se o jogo foi concluído e, caso contrário, encontrar a próxima célula não visitada. Em seguida, ele tenta marcar a célula como uma mina. Se a marcação for segura, o algoritmo continua revelando as células adjacentes usando busca em profundidade.

A busca em profundidade é realizada para revelar células vazias, evitando minas, e avançando para as próximas células vazias adjacentes até que não haja mais células vazias para revelar ou até que uma solução seja encontrada. Se o algoritmo se deparar com uma situação em que a marcação de uma mina não é segura, ele desmarca a célula como uma mina e tenta outras abordagens. Esta lógica foi projetada para garantir que todas as células vazias sejam reveladas de forma segura, seguindo as regras do jogo. A medida de conclusão do jogo é baseada na verificação de todas as células vazias sendo reveladas.

O código também oferece métodos para imprimir o estado atual do tabuleiro do jogo, com minas, números de minas adjacentes visíveis e marcações de minas. Além disso, é possível imprimir o tabuleiro do solucionador, mostrando as células reveladas e as minas marcadas.

Em resumo, a lógica aplicada neste projeto permite criar uma versão simplificada do Campo Minado que pode ser automaticamente resolvida usando busca em profundidade.</h4>

<h1>Tecnologia Usada:</h1>

<h3><a href = "https://jdk.java.net/20/">JAVA</a></h3>
<ul>
   <li>Versão: 20</li>
</ul>

<h3><a href = "https://react.dev/">REACT</a></h3>

<h1>Status do projeto:</h1>
<h4>Em andamento</h4>