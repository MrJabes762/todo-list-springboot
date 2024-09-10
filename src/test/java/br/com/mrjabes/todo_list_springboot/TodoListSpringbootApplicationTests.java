package br.com.mrjabes.todo_list_springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.mrjabes.todo_list_springboot.model.todo.Prioridade;
import br.com.mrjabes.todo_list_springboot.model.todo.Todo;

@SpringBootTest
class TodoListSpringbootApplicationTests {// testes automatizados 

	@Autowired// chamar o endpoints
	private WebTestClient webTestClient;
	private Todo todo;

	

	@Test
	void testCreateTodoSucess() {// Primeiro teste é para verificar a criação de uma tarefa com sucesso 
		setTodo(new Todo("Tarefa 1", "descricao da tarefa", false, Prioridade.Alta));
		getWebTestClient()
		.post()
		.uri("/todos")
		.bodyValue(getTodo())
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.jsonPath("$").isArray()
		.jsonPath("$.length()").isEqualTo(1)
		.jsonPath("$[0].nome").isEqualTo(getTodo().getNome())
		.jsonPath("$[0].descricao").isEqualTo(getTodo().getDescricao())
		.jsonPath("$[0].realizado").isEqualTo(getTodo().getRealizado())
		.jsonPath("$[0].prioridade").isEqualTo(getTodo().getPrioridade());
		/*  
			1- chamar endpoint getWebTestClient().post()
			2- mapeado para todos  - .uri("/todos")
			3 - passando a todo criada para o corpo da requisição bodyValue - .bodyValue(todo)
			4 -realizado a requizição - .exchange()
			5 -pegando o status da atividade com a expectativa de dar certo - expectStatus().isOk()
			6 - è esperado que a resposta  - expectBody()
			7 - Seja um Json com varias propriedades .jsonPath("$").isArray() 
			// o cifrão indica um nó raiz ou seja a lista de todos como um Array
			8 - Verificar se o tamanho desse array é = 1 (.jsonPath("$.length()").isEqualTo(1);)
			// chamando o nó Raiz $ e observando o seu comprimento 
			9 - Verificaçao de se todos os datos do Json são iguais ao do Objeto local
		*/
	}

	@Test
	void testCreateTodoFailure() {// Segundo teste é para verificar a criação de uma tarefa com falha
	}
	public WebTestClient getWebTestClient() {
		return webTestClient;
	}

	public void setWebTestClient(WebTestClient webTestClient) {
		this.webTestClient = webTestClient;
	}

	public Todo getTodo() {
		return this.todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

}
