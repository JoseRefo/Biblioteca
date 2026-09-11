Sistema de Gestão da Biblioteca Municipal
1. Descrição do Projeto
O Sistema de Gestão da Biblioteca Municipal é uma aplicação desenvolvida em Java, executada através da consola, com o objetivo de auxiliar na gestão básica de uma biblioteca.
O sistema permite registar livros e utilizadores, consultar o catálogo, pesquisar livros, efetuar empréstimos e devoluções e apresentar estatísticas de utilização.
O projeto foi desenvolvido utilizando estruturas de dados fundamentais da linguagem Java, nomeadamente vetores (arrays) e matrizes, sendo adequado para demonstrar conceitos de programação estruturada, estruturas de dados e manipulação de informação.
2. Funcionalidades
O sistema disponibiliza as seguintes funcionalidades:
2.1 Registo de livros
Permite cadastrar novos livros no catálogo, informando:
•	ID do livro;
•	Título;
•	Autor;
•	Ano de publicação;
•	Quantidade disponível.
Cada livro deve possuir um ID único.
2.2 Registo de utilizadores
Permite cadastrar utilizadores da biblioteca através de:
•	ID do utilizador;
•	Nome do utilizador.
O sistema verifica se o ID informado já está registado.

2.3 Listagem do catálogo
Apresenta todos os livros registados, incluindo:
•	ID;
•	Título;
•	Autor;
•	Ano de publicação;
•	Quantidade disponível;
•	Quantidade total.
2.4 Pesquisa de livros
É possível pesquisar livros utilizando:
•	Título;
•	Autor.
A pesquisa não diferencia letras maiúsculas de minúsculas e permite encontrar um termo mesmo que este faça parte do título ou nome do autor.
2.5 Empréstimo de livros
Para realizar um empréstimo, o utilizador deve informar:
1.	ID do livro;
2.	ID do utilizador.
O sistema verifica:
•	Se o livro existe;
•	Se o utilizador está registado;
•	Se existem exemplares disponíveis.
Quando o empréstimo é efetuado com sucesso, a quantidade disponível do livro é reduzida em uma unidade.

2.6 Devolução de livros
Permite registar a devolução de um livro através do ID do livro e do ID do utilizador.
O sistema procura o empréstimo ativo correspondente e, quando encontrado, marca-o como devolvido e aumenta novamente a quantidade disponível do livro.
2.7 Estatísticas
O sistema apresenta informações como:
•	Total de livros no catálogo;
•	Total de utilizadores registados;
•	Total de empréstimos realizados;
•	Número de empréstimos atualmente ativos;
•	Livro mais requisitado.
3. Tecnologias utilizadas
O projeto utiliza:
•	Java;
•	Java Standard Edition (Java SE);
•	Scanner para entrada de dados;
•	Arrays/Vetores para armazenamento dos livros e utilizadores;
•	Matriz bidimensional para armazenamento dos empréstimos;
•	Terminal/Consola para interação com o utilizador.
Não são utilizadas bases de dados ou bibliotecas externas.





4. Requisitos
Para executar o projeto é necessário ter instalado:
Java Development Kit (JDK)
Recomenda-se utilizar uma versão moderna do JDK, como:
•	JDK 17;
•	JDK 21;
•	ou superior.
Para verificar se o Java está instalado, abra o terminal ou Prompt de Comando e execute:
java -version
Também é possível verificar o compilador Java com:
javac -version
Se os dois comandos apresentarem a versão instalada, o ambiente está preparado para executar o projeto.
5. Estrutura do projeto
A estrutura mínima do projeto é:
BibliotecaMunicipal/
│
├── src/
│   └── Biblioteca.java
└── README.md
O ficheiro principal da aplicação é:
Biblioteca.java
A classe principal contém o método:
public static void main(String[] args)
que inicia a execução do sistema.
6. Configuração do projeto
6.1 Clonar ou obter o projeto
Caso o projeto esteja disponível no GitHub, primeiro clone o repositório:
git clone URL_DO_REPOSITORIO
Depois entre na pasta:
cd BibliotecaMunicipal
Caso o projeto tenha sido obtido através de um ficheiro ZIP, extraia-o para uma pasta no computador e abra essa pasta no editor ou IDE.
7. Execução através do terminal
Entre na pasta onde se encontra o ficheiro Biblioteca.java.
Compilar
Execute:
javac Biblioteca.java
Se não houver erros, será criado o ficheiro:
Biblioteca.class
Executar
Depois da compilação, execute:
java Biblioteca
O menu principal será apresentado no terminal.
8. Armazenamento dos dados
O sistema utiliza estruturas de dados em memória.
Os livros são armazenados em vetores paralelos, incluindo vetores para:
•	ID;
•	título;
•	autor;
•	ano;
•	quantidade disponível;
•	quantidade total.
Os utilizadores também são armazenados através de vetores.
Os empréstimos são armazenados numa matriz bidimensional. Cada linha representa um empréstimo e possui três informações:
[0] = ID do livro
[1] = ID do utilizador
[2] = estado do empréstimo
O estado é representado por:
1 = empréstimo ativo
0 = livro devolvido
9. Limitações
Como o projeto utiliza armazenamento em memória, existem algumas limitações:
•	Os dados não são gravados numa base de dados;
•	Os dados são perdidos quando o programa é encerrado;
•	Existe um limite máximo de livros;
•	Existe um limite máximo de utilizadores;
•	Existe um limite máximo de empréstimos;
•	A aplicação funciona através da consola.
Os limites definidos atualmente são:
Máximo de livros:       200
Máximo de utilizadores: 200
Máximo de empréstimos:  2000

10. Dependências
O projeto não possui dependências externas.
A única classe importada no programa é:
import java.util.Scanner;
A classe Scanner faz parte da biblioteca padrão do Java, portanto não é necessário instalar nenhuma biblioteca adicional.
11. Solução de problemas
Erro: javac is not recognized
Este erro normalmente indica que o JDK não está instalado ou que o caminho do Java não está configurado nas variáveis de ambiente do sistema.
Verifique:
java -version
e:
javac -version
Erro: Could not find or load main class Biblioteca
Verifique se:
1.	Está dentro da pasta correta;
2.	O ficheiro foi compilado;
3.	O nome da classe é Biblioteca;
4.	O comando de execução foi escrito corretamente:
java Biblioteca
Erro durante a introdução de dados
O programa possui uma função para validar valores inteiros. Quando o utilizador introduz um valor que não é um número válido, o sistema solicita uma nova introdução.

Link do repositório GitHub:

https://github.com/JoseRefo/Biblioteca


