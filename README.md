# Sistema de Gerenciamento de Biblioteca

###### Aqui, você terá acesso a:
- Arquivos com o código-fonte em Java;
- Print do sistema na tela de empréstimo;
- Diagrama relacional (EER) do Banco de Dados criado

## Introdução
Como parte da segunda unidade da disciplina de Programação Desktop no curso de Análise e Desenvolvimento de Sistemas (ADS), foi solicitado um sistema em Java capaz de realizar CRUD em um banco de dados MySQL, além de exibir informações da melhor forma possível.

## Conclusão
Na programação, diferentes ferramentas são versáteis o suficiente para serem usadas em qualquer cenário, contudo, é inegável o fato de que existem aquelas mais eficazes para determinadas tarefas. Chego a essa conclusão após perceber o quão exaustivo foi programar a interface e o front-end em Java.

## Pontos a melhorar
- Acredito que haviam formas mais performáticas de trabalhar com o MySQL, através de Procedures e Views.
- Como observado na conclusão, há ferramentas mais eficazes para criação da interface em termo de produtividade.
- A forma como o sistema foi criado não abre precedentes para cenários adversos como, por exemplo, o comportamento de determinado livro no sistema após seu autor ter sido deletado.
- Na prática, um sistema como este precisa ser implementado levando em consideração uma API da faculdade que traga automaticamente todos os alunos matriculados.

# Passo a passo
- OBJETIVO: Criar um sistema capaz de realizar CRUD de autores, livros destes autores e usuários (ou estudantes) que realizariam os empréstimos, além de oferecer a interface para registrar empréstimos ou devoluções dos livros emprestados.

-  BANCO DE DADOS: Foi criado pautado nas necessidades estabelecidas, com as devidas relações (PK-FK).
-  CRUDs: Baseado no banco de dados, foram criadas e programas as telas capazes de cadastrar, alterar ou deletar os autores, livros (que sempre possuem um autor que já tenha sido cadastrado antes) e usuários.
-  EMPRÉSTIMOS/DEVOLUÇÕES: Na etapa final do projeto, foram criadas as telas de empréstimo e devolução dos livros, para os testes destes, foi necessário cadastrar informações antes.

A seguir, dois prints: um do Diagrama EER do banco de dados, outro da tela de empréstimo do sistema enquanto é usado:

![Aqui há um print do Diagrama EER](https://github.com/BitencourtVitor/Biblioteca/blob/master/biblioteca/screenshot_diagramaeer.png)
![Aqui há um print do Sistema](https://github.com/BitencourtVitor/Biblioteca/blob/master/biblioteca/screenshot_emprestimo.png)
