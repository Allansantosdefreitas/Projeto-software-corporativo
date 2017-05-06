# Projeto-software-corporativo
Projeto da disciplina de Desenvolvimento de Software Corporativo



### Testes JUnit (entrega 1: 29/04 e entrega 2: 06/05)
- BD criado pelo JPA
- Deve ter um estado conhecido antes dos testes (DBUnit)

+ Classes (falta create e update inválido)):
  - CartaoTest (ok) 
  - ClienteTest (ok, falta delete via query e native query)
  - ConsultaGeralTest ()
  - ConsultaMedicaTest
  - EnderecoTest
  - FuncionarioTest
  - PetTest
  - ServicoTest
  - VeterinarioTest



+ Testar:
	- 1- Insert, Update, delete (via em)
	- 2- Select (JPQL)
		- query string
		- named query 
		- ex.: "checar qtde valores etc."
	- 3- Select (SQL native query)
		- query string
		- named query 
		- recuperar objetos
		- Recuperar objetos e valor
		- ex.: "checar qtde valores etc."
	- 4- Update, delete (via query)
	- 5- Validação (Bean validation)
	  - "Com Hibernate validator"
	  - ex.: "Pela qtde testes"
	  - "Usar validador próprio; um pra cada equipe"

  ##### "Usar Asserts para evitar ter que ler o log para saber o resultado do teste"
