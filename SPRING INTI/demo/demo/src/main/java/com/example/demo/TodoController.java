package com.example.todoapi.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {

  private final TodoRepository todoRepository;

  public TodoController(final TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @GetMapping
  public ResponseEntity<List<TodoModel>> getAllTodos() {
    List<TodoModel> todos = this.todoRepository.findAll();
    if (todos.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(todos);
  }

  @GetMapping("/{todoId}")
  public ResponseEntity<TodoModel> getTodoById(@PathVariable String todoId) {
    return this.todoRepository.findById(todoId)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<TodoModel> createTodo(@RequestBody TodoModel todo) {
    TodoModel createdTodo = this.todoRepository.save(todo);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
  }

  @PutMapping("/{todoId}")
  public ResponseEntity<TodoModel> updateTodo(@PathVariable String todoId, @RequestBody TodoModel todo) {
    Optional<TodoModel> todoModelOpt = this.todoRepository.findById(todoId);
    if (todoModelOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    TodoModel todoModel = todoModelOpt.get();
    if (todo.getTitle() != null) {
      todoModel.setTitle(todo.getTitle());
    }
    if (todo.getIsCompleted() != null) {
      todoModel.setIsCompleted(todo.getIsCompleted());
    }
    TodoModel updatedTodo = this.todoRepository.save(todoModel);
    return ResponseEntity.ok(updatedTodo);
  }

  @DeleteMapping("/{todoId}")
  public ResponseEntity<String> deleteTodo(@PathVariable String todoId) {
    if (!this.todoRepository.existsById(todoId)) {
      return ResponseEntity.notFound().build();
    }
    this.todoRepository.deleteById(todoId);
    return ResponseEntity.ok("Todo Deleted Successfully");
  }
}
