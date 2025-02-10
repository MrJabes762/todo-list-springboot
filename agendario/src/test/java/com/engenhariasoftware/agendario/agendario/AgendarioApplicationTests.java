package com.engenhariasoftware.agendario.agendario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.engenhariasoftware.agendario.agendario.model.Agendario;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AgendarioApplicationTests {

	@Autowired// chamar o endpoints
	private WebTestClient webTestClient;
	private Agendario todo;

	

	@Test
	void testCreateTodoSucess() {// Primeiro teste é para verificar a criação de uma tarefa com sucesso 
		setTodo(new Agendario("Tarefa 1", "descricao da tarefa", false, "Alta","10/10/2014"));
		getWebTestClient()
		.post()
		.uri("/agendas")
		.bodyValue(getTodo())
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.jsonPath("$").isArray()
		.jsonPath("$.length()").isEqualTo(1)
		.jsonPath("$[0].nome").isEqualTo(getTodo().getNome())
		.jsonPath("$[0].descricao").isEqualTo(getTodo().getDescricao())
		.jsonPath("$[0].realizado").isEqualTo(getTodo().getRealizado())
		.jsonPath("$[0].prioridade").isEqualTo(getTodo().getPrioridade())
		.jsonPath("$[0].data").isEqualTo(getTodo().getData());
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
		setTodo(new Agendario("", "", false, "Alta",""));
		getWebTestClient()
			.post()
			.uri("/agendas")
			.bodyValue(getTodo())
			.exchange()
			.expectStatus()
			.isBadRequest();
	}
	@Test
	void testUpdateTodo() {
		Agendario todo = new Agendario("Tarefa Atualizada", "descricao atualizada", true, "Baixa", "12/12/2022");
		webTestClient.put()
			.uri("/agendas")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$[0].nome").isEqualTo("Tarefa Atualizada");
	}
	@Test
	void testGetTodos() {
		webTestClient.get()
			.uri("/agendas")
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray();
	}
	@Test
	void testDeleteTodo() {
		webTestClient.delete()
			.uri("/agendas/{id}", 1)
			.exchange()
			.expectStatus().isOk();
	}
	public WebTestClient getWebTestClient() {
		return webTestClient;
	}

	public void setWebTestClient(WebTestClient webTestClient) {
		this.webTestClient = webTestClient;
	}

	public Agendario getTodo() {
		return this.todo;
	}

	public void setTodo(Agendario todo) {
		this.todo = todo;
	}

}
