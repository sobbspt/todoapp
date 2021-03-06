package com.wanichnun.todoapp.service;

import com.wanichnun.todoapp.document.Todo;
import com.wanichnun.todoapp.repository.TodoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodoServiceTest {
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        todoService = new TodoService(todoRepository);
    }

    @Test
    public void testCreateTodo() {
        String mockUserId = "U1";
        String mockTaskName = "mock-task";
        Boolean mockIsPinned = false;
        Boolean mockIsDone = false;
        Long mockOrder = 0L;
        Date mockDate = new Date();

        Todo mockTodo = new Todo();
        mockTodo.setUserId(mockUserId);
        mockTodo.setTaskName(mockTaskName);
        mockTodo.setOrder(mockOrder);
        mockTodo.setIsPinned(mockIsPinned);
        mockTodo.setIsDone(mockIsDone);
        mockTodo.setDate(mockDate);


        when(todoRepository.save(any())).thenReturn(mockTodo);

        Todo actual = todoService.create(mockUserId, mockTaskName, mockIsPinned, mockIsDone, mockOrder, mockDate);

        verify(todoRepository, times(1)).save(any());

        Assert.assertEquals(mockTaskName, actual.getTaskName());
        Assert.assertEquals(mockUserId, actual.getUserId());
        Assert.assertEquals(mockOrder, actual.getOrder());
        Assert.assertEquals(mockIsDone, actual.getIsDone());
        Assert.assertEquals(mockIsPinned, actual.getIsPinned());
        Assert.assertEquals(mockDate, actual.getDate());
    }

    @Test
    public void testUpdateSuccess() {
        String mockUserId = "U1";
        String mockTaskName = "mock-task";
        Boolean mockIsPinned = false;
        Boolean mockIsDone = false;
        Long mockOrder = 0L;
        Date mockDate = new Date();

        Todo mockTodo = new Todo();
        mockTodo.setUserId(mockUserId);
        mockTodo.setTaskName(mockTaskName);
        mockTodo.setOrder(mockOrder);
        mockTodo.setIsPinned(mockIsPinned);
        mockTodo.setIsDone(mockIsDone);
        mockTodo.setDate(mockDate);

        Optional<Todo> mockTodoOpt = Optional.of(mockTodo);

        when(todoRepository.findByIdAndUserId(any(), any())).thenReturn(mockTodoOpt);
        when(todoRepository.save(any())).thenReturn(mockTodoOpt.get());

        Todo actual = todoService.update("AAA", "USERID", false, false, 0L);

        verify(todoRepository, times(1)).findByIdAndUserId(any(), any());
        verify(todoRepository, times(1)).save(any());

        Assert.assertEquals(mockUserId, actual.getUserId());
        Assert.assertEquals(mockTaskName, actual.getTaskName());
        Assert.assertEquals(mockOrder, actual.getOrder());
        Assert.assertEquals(mockIsDone, actual.getIsDone());
        Assert.assertEquals(mockIsPinned, actual.getIsPinned());
        Assert.assertEquals(mockDate, actual.getDate());
    }

    @Test
    public void testListTodosSuccess() {
        String mockUserId = "U1";
        String mockTaskName = "mock-task";
        Boolean mockIsPinned = false;
        Boolean mockIsDone = false;
        Long mockOrder = 0L;
        Date mockDate = new Date();

        Todo mockTodo = new Todo();
        mockTodo.setUserId(mockUserId);
        mockTodo.setTaskName(mockTaskName);
        mockTodo.setOrder(mockOrder);
        mockTodo.setIsPinned(mockIsPinned);
        mockTodo.setIsDone(mockIsDone);
        mockTodo.setDate(mockDate);

        when(todoRepository.findByUserId(any())).thenReturn(Arrays.asList(mockTodo));;

        List<Todo> actual = todoService.listTodos("AAA");

        verify(todoRepository, times(1)).findByUserId(any());

        Assert.assertEquals(1, actual.size());

        Assert.assertEquals(mockUserId, actual.get(0).getUserId());
        Assert.assertEquals(mockTaskName, actual.get(0).getTaskName());
        Assert.assertEquals(mockOrder, actual.get(0).getOrder());
        Assert.assertEquals(mockIsDone, actual.get(0).getIsDone());
        Assert.assertEquals(mockIsPinned, actual.get(0).getIsPinned());
        Assert.assertEquals(mockDate, actual.get(0).getDate());
    }

    @Test
    public void test() throws ParseException {
        String test = "2/1/2018 12:00";
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
        Date date = format.parse(test);
        System.out.println(date);
    }
}
