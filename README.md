# Sistema de Gestão de Livraria

Sistema desktop desenvolvido em Java com integração ao MySQL, com o objetivo de gerenciar uma livraria de forma simples e eficiente.

---

## Funcionalidades

### Clientes

- Cadastro de clientes
- Edição de dados
- Exclusão de registros

### Funcionários

- Cadastro de funcionários
- Controle de acesso (nível de permissão)
- Edição e exclusão

### Livros

- Cadastro de livros
- Controle de estoque
- Atualização e exclusão

### Vendas

- Registro de vendas
- Associação de cliente e funcionário
- Adição de múltiplos livros por venda
- Cálculo automático do valor total

### Histórico

- Registro automático de ações:

  - Cadastro
  - Edição
  - Exclusão
  - Vendas
- Armazenamento de:

  - Tipo de ação
  - Entidade afetada
  - Funcionário responsável
  - Data e hora

### Relatórios

- Vendas por período
- Livros mais vendidos
- Desempenho de vendas por funcionário

---

## Tecnologias Utilizadas

- Java (Swing)
- MySQL
- NetBeans

---

## Estrutura do Projeto

```
src/
 ├── data/
 │    ├── ClienteDao
 │    ├── Conexao
 │    ├── FuncionarioDao
 │    ├── HistoricoDao
 │    ├── ItemVendaDao
 │    ├── LivroDao
 │    ├── RelatorioDao
 │    └── VendaDao
 │
 ├── main/
 │    ├── Main
 │
 ├── model/
 │    ├── Cliente
 │    ├── Funcionario
 │    ├── Historico
 │    ├── ItemVenda
 │    ├── Livro
 │    └── Venda
 │
 └── view/
      ├── PainelClientes
      ├── PainelHistorico
      ├── PainelLivros
      ├── PainelRelatorio
      ├── PainelUsuario
      ├── PainelVendas
      ├── TelaCadastroCliente
      ├── TelaCadastroLivro
      ├── TelaCadastroUsuario
      ├── TelaLogin
      ├── TelaPrincipal
      └── TelaRegistroVenda

```

---

## Banco de Dados

Nome do banco: livraria_gestor

### Tabelas principais:

- cliente
- historico
- item_venda
- livro
- usuario
- venda

---

## Autor

Desenvolvido por William Orfanó

---

## Licença

Este projeto é de uso acadêmico.
