package com.desafio.todolist;

import static com.desafio.todolist.TestConstants.TODO;
import static com.desafio.todolist.TestConstants.TODOS;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.desafio.todolist.entity.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("/remove.sql")
class TodolistApplicationTests {

	@Autowired
	private WebTestClient WebTestClient;

	@Test
	void testCreateTodoSucess() {
		var todo = new Todo("todo 1", "desc todo 1",  false, 1);

		WebTestClient
			.post()
			.uri("/todos")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(1)
			.jsonPath("$[0].nome").isEqualTo(todo.getNome())
			.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
			.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
			.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}


	@Test
	void testCreateTodoSFailure() {
		WebTestClient
		.post()
		.uri("/todos")
		.bodyValue( new Todo ("", "", false, 0))
		.exchange()
		.expectStatus().isBadRequest();
	}
	
	@Sql("/import.sql")
	@Test
	public void testUpdateTodoSuccess() {
		
		var todo = new Todo(TODO.getId(), TODO.getNome() + " Up", TODO.getDescricao() + " Up", !TODO.isRealizado(), TODO.getPrioridade() + 1);

		WebTestClient
				.put()
				.uri("/todos/" + TODO.getId())
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(5)
				.jsonPath("$[0].nome").isEqualTo(todo.getNome())
				.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
				.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	public void testUpdateTodoFailure() {
		var unexinstingId = 1L;

		WebTestClient
				.put()
				.uri("/todos/" + unexinstingId)
				.bodyValue(new Todo(unexinstingId, "", "", false, 0))
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Sql("/import.sql")
	@Test
	public void testDeleteTodoSuccess() {
		WebTestClient
				.delete()
				.uri("/todos/" + TODOS.get(0).getId())
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(4)
				.jsonPath("$[0].nome").isEqualTo(TODOS.get(1).getNome())
				.jsonPath("$[0].descricao").isEqualTo(TODOS.get(1).getDescricao())
				.jsonPath("$[0].realizado").isEqualTo(TODOS.get(1).isRealizado())
				.jsonPath("$[0].prioridade").isEqualTo(TODOS.get(1).getPrioridade());
	}

	@Test
	public void testDeleteTodoFailure() {
		var unexinstingId = 1L;

		WebTestClient
				.delete()
				.uri("/todos/" + unexinstingId)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Sql("/import.sql")
	@Test
	public void testListTodos() throws Exception {
		WebTestClient
				.get()
				.uri("/todos")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(5)
				.jsonPath("$[0]").isEqualTo(TODOS.get(0))
				.jsonPath("$[1]").isEqualTo(TODOS.get(1))
				.jsonPath("$[2]").isEqualTo(TODOS.get(2))
				.jsonPath("$[3]").isEqualTo(TODOS.get(3))
				.jsonPath("$[4]").isEqualTo(TODOS.get(4));
	}
}
