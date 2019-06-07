# ControleDeGastos

Este commit possui as seguintes funcionalidade:
Funcionalidade - Listagem de gastos onde é ordenado de forma automática os últimos gastos realizados.
Funcionalidade - Filtro de gastos, filtro criado por data.
Funcionalidade - Categorização de gastos, dado que ao buscar seus últimos gastos o usuário identifique que algum não esta categorizado, é possível atribuir uma categoria ao mesmo. 
Funcionalidade - Sugestão de categoria, onde é possível o usuário visualizar alguma categoria existente para que possa atribuir a algum de seus gastos. 
Funcionalidade - Categorização automatica de gasto, é atribuído automaticamente de acordo com a descrição de algum gasto anterior. Caso seja uma nova descrição, o gasto é salvo automaticamente com a categoria Outros.

A data do gasto é atribuida automaticamente, caso não seja informada uma data. Assumindo então a data atual.
É possível também criar novas categorias acessando o serviço de categorias.
DB utilizado foi o mysql junto com o flyway para migração de dados.

