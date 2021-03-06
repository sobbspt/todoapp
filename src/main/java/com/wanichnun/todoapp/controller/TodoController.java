package com.wanichnun.todoapp.controller;

import com.wanichnun.todoapp.model.Response;
import com.wanichnun.todoapp.model.ResponseModel;
import com.wanichnun.todoapp.model.TodoUpdateRequest;
import com.wanichnun.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users/{userId}/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public HttpEntity<ResponseModel> getAll(
            @PathVariable(name = "userId") String userId) {
        return new ResponseModel(Response.SUCCESS.getContent(), todoService.listTodos(userId)).build(HttpStatus.OK);
    }

    @PutMapping(path = "{todoId}")
    public HttpEntity<ResponseModel> update(
            @PathVariable(name = "userId") String userId,
            @PathVariable(name = "todoId") String todoId,
            @RequestBody TodoUpdateRequest request
    ) {
        return new ResponseModel(Response.SUCCESS.getContent(), todoService.update(
                todoId,
                userId,
                request.getIsPinned(),
                request.getIsDone(),
                request.getOrder())).build(HttpStatus.OK);
    }
}
