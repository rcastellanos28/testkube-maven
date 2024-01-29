package com.testkube.taskmanagement;

import com.testkube.taskmanagement.controller.TaskController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @MockBean
    private TaskController taskController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void shouldReturnAllTasks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                .content("New Task")
                .contentType("text/plain"))
                .andExpect(status().isOk())
                .andExpect(content().string("Task created successfully"));
    }

    @Test
    public void shouldReturnTaskById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/0"))
                .andExpect(status().isOk())
                .andExpect(content().string("Task not found"));

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                .content("New Task")
                .contentType("text/plain"));

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/0"))
                .andExpect(status().isOk())
                .andExpect(content().string("New Task"));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/tasks/0")
                .content("Updated Task")
                .contentType("text/plain"))
                .andExpect(status().isOk())
                .andExpect(content().string("Task not found"));

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                .content("New Task")
                .contentType("text/plain"));

        mockMvc.perform(MockMvcRequestBuilders.put("/tasks/0")
                .content("Updated Task")
                .contentType("text/plain"))
                .andExpect(status().isOk())
                .andExpect(content().string("Task updated successfully"));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/0"))
                .andExpect(status().isOk())
                .andExpect(content().string("Task not found"));

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                .content("New Task")
                .contentType("text/plain"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/0"))
                .andExpect(status().isOk())
                .andExpect(content().string("Task deleted successfully"));
    }
}
