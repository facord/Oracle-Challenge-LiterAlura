<H1>📚 Challenge LiterAlura – Oracle ONE & Alura</H1>

Projeto desenvolvido como parte do Challenge de Java da formação Oracle ONE + Alura, com o objetivo de consumir dados da API Gutendex, persistir informações em um banco de dados e permitir consultas via menu interativo no terminal.

A aplicação utiliza Java com Spring Boot, Spring Data JPA e PostgreSQL para gerenciamento e persistência dos dados.

<p>🚀 <b>Tecnologias Utilizadas</b></p>

<li>Java</li>

<li>Spring Boot</li>

<li>Spring Data JPA</li>

<li>PostgreSQL</li>

<li>API Gutendex (https://gutendex.com/
)</li>

<li>Maven</li>

<li>Hibernate</li>


<p>🧠 <b>Funcionalidades</b></p>

A aplicação apresenta um menu interativo no terminal, onde o usuário pode buscar livros diretamente na API ou consultar os dados já persistidos no banco de dados.

<p>🔎 <b>Funcionalidades que buscam dados na API e salvam no banco</b></p>

<b>As opções 1 a 7 realizam buscas na API Gutendex e armazenam os resultados no banco de dados:</b>

<li>Buscar livro por título (faz busca por trecho)</li>

<li>Buscar livro por autor (faz busca por trecho)</li>

<li>Buscar livro por assunto (faz busca por trecho)</li>

<li>Buscar livro por determinado período (informar ano inicial e ano final para busca)</li>

<li>Buscar livro por linguagem (com opções especificas da API)</li>

<li>Buscar livros por popularidade</li>

<li>Buscar livro por ID</li>


<p>🗄️ <b>Funcionalidades que consultam o banco de dados</b></p>

<b>As opções 8 a 14 realizam consultas diretamente no banco PostgreSQL, sem acessar a API:</b>

<li>Listar todos os livros</li>

<li>Listar livros por título</li>

<li>Listar livros por idioma</li>

<li>Listar autores</li>

<li>Listar autores vivos em determinado ano</li>

<li>Exibir a quantidade de livros em um determinado idioma</li>

<li>Exibir livro por ID</li>

<li>Sair da aplicação</li>


<p>📋<b> Exemplo do Menu</b></p>
*** Challenge LiterAlura ***
<ol>
<li>Buscar livro por título
<li>Buscar livro por autor
<li>Buscar livro por assunto
<li>Buscar livro por determinado período
<li>Buscar livro por linguagem
<li>Buscar livros por popularidade
<li>Buscar livro por id
<li>Lista de todos os livros
<li>Lista de livros por título
<li>Lista de livros por idioma
<li>Lista de autores
<li>Lista de autores vivos em determinado ano
<li>Exibir a quantidade de livros em um determinado idioma
<li>Exibir livro por ID
<li>Sair

<p>⚙️<b> Configuração do Banco de Dados</b></p>
<p>A aplicação utiliza PostgreSQL.</p>
<p>Configure o arquivo application.properties e crie variaveis de ambiente com os dados:</p>
<p><img width="792" height="355" alt="Captura de tela 2025-12-26 190344" src="https://github.com/user-attachments/assets/efb0c460-7028-4660-b40e-99f4a301119b" /></p>

<p>Configure o banco de dados PostgreSQL</p>

<p>Execute a aplicação como Spring Boot App</p>

<p>Interaja com o menu pelo terminal</p>

<p>🎯<b> Objetivo do Projeto</b></p>

Praticar consumo de APIs REST

Trabalhar com persistência de dados usando JPA/Hibernate

Aplicar conceitos de Spring Boot

Separação de responsabilidades (Service, Repository)

Consolidar conhecimentos adquiridos na formação Oracle ONE + Alura

<p>✨ <b>Autor</p>
<p>👩‍💻 Fabiana Cordeiro Rocha</b></p>
<p>Projeto desenvolvido para fins educacionais no programa Oracle Next Education (ONE) em parceria com a Alura.</p>
