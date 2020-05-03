# Trabalho Final da Disciplina de Programação Orientada a Objetos II

# Desenvolvido por:
- Anderson Maik Rodrigues
- Jean Matheus Backes Kistenmacher

# Especificação

A cada dia mais pessoas escolhem a praticidade de pedir comida utilizando celular. Por outro lado, alguns usuários de SmartPhones estão cansados de terem que instalar aplicativos diferentes o tempo todo, então alguns restaurantes optam por interagir com os seus clientes por meio dos aplicativos de trocas de mensagens instantâneas, o que demanda de um funcionário somente para isso. Sabendo disso, sua empresa enxerga uma oportunidade de negócio e resolve disponibilizar um sistema com um ChatBot para fazer pedidos pelo Telegram. 		

ChatBot é um programa de computador que tenta simular um ser humano na conversação com as pessoas. Seu grupo deve criar um Bot no aplicativo Telegram (forme o grupo de modo que ao menos um integrante tenha o aplicativo). É preciso abrir o Telegram, localizar o perfil “BotFather” e enviar o comando /newbot. Depois, precisa especificar um nome, como por exemplo “LancheriaMilliway” e um nome de usuário (que termine com a palavra bot, por exemplo “LancheriaMilliwaybot”). Por fim, você receberá um token para utilizar no seu programa Java o qual acessa o Bot via API e responde as requisições do usuário. A documentação de uso da API está disponível em https://core.telegram.org/api. Você pode utilizar como base o código disponibilizado no virtual para fazer a parte da comunicação devendo desenvolver o restante do sistema. Os dados serão armazenados em um banco de dados cujo modelo relacional é apresentado a seguir:


# Funcionalidades

- A tabela cliente vai armazenar os dados de todos os usuários que entrarem em contato pelo aplicativo (inserção automática). As tabelas categoria e produto terão seus dados mantidos pelo dono do restaurante de forma que você precisa fazer as telas de cadastro. A seguir são apresentados exemplos de registros nessas tabelas.
- As tabelas pedido e pedido_item serão preenchidas durante a interação do usuário com o Bot.  
- O programa deve ter no mínimo as seguintes telas:
	- Manutenção de Categoria: permitir incluir, alterar e excluir categorias de lanches (somente será possível excluir se ainda não houver produto vinculado); 
	- Manutenção de Produtos (lanches): permitir incluir, alterar e excluir produtos (somente será possível excluir se ainda não estiver em algum pedido). Deve ser possível escolher a categoria em uma JComboBox.
	- Consulta de pedidos: O sistema deve apresentar uma tela em formato mestre-detalhe. Na primeira tabela exibir todos os pedidos que foram finalizados pelo cliente, mas ainda não foram entregues. Na segunda tabela listar os itens do pedido selecionado. Ao clicar no botão “Atualizar a Tela” deve ser refeita a consulta no BD. O botão “Finalizar Entrega” deve atualizar o campo entregue=1 para o pedido selecionado e recarregar a tela (de forma que o pedido saia da tela). 
	- A interação do cliente com o Bot ocorre da seguinte forma: 
		- Cliente envia uma mensagem qualquer; 
		- Bot insere o registro na tabela cliente (se ainda não houver esse id) e responde com uma saudação padrão perguntando ao cliente qual categoria de lanches ele gostaria de olhar (listando as opções); 
		- Supondo que o cliente escolha ver a categoria 3 – pizzas; 
		- O Bot responde com a lista de produtos desta categoria (código, descrição e preço); 
		- Supondo que o cliente escolha a opção 2 – Pizza Média; 
		- O Bot pergunta a quantidade; 7. Supondo que o cliente responda 1; 
		- O Bot pergunta se tem alguma observação; 
		- Supondo que o cliente responda “Com borda de Cheddar”; 
		- O Bot insere o corpo do pedido e o item de pedido e em seguida pergunta se o cliente quer adicionar mais algum produto no pedido; 
		- Se o cliente responder sim, exibe novamente as categorias e repete os passos a partir do 3; 
		- Se o cliente responder não, então atualiza o pedido como finalizado=1 e retorna uma mensagem agradecendo o pedido e informando que poderá retirar em 40 minutos.

Lembre-se que a cada interação o usuário pode informar uma entrada que não corresponde a um comando esperado. Então se o cliente informar na quantidade que quer “uma” ao invés de “1”, o Bot deveria ser inteligente o suficiente para tratar isso. Caso seja impossível interpretar o comando, retornar uma mensagem avisando que não entendeu e pedir a resposta novamente.

Lembre-se também que enquanto o Bot está respondendo a requisição do cliente 9999999, um outro cliente pode iniciar uma interação. Cada sequência de comandos deve ser gerenciada em um thread separado.
