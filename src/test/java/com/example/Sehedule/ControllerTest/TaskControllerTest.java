package com.example.Sehedule.ControllerTest;

import com.example.Sehedule.Dtos.TaskDto;

import com.example.Sehedule.Controller.TaskController;
import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.Services.CsvServices;
import com.example.Sehedule.Services.TaskServices;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskServices taskServices;

    @MockBean
    private CsvServices csvServices;

    @Autowired
    private ObjectMapper objectMapper;

    private TaskDto createValidTaskDto() {
        TaskDto dto = new TaskDto();
        dto.setId(1L);
        dto.setTasktype("Cleaning");
        dto.setSehduleDate(LocalDate.now().plusDays(1));
        dto.setStatus("Pending");

        AreaDto areaDto = new AreaDto();
        areaDto.setId(101L);
        areaDto.setFloor("13th");
        dto.setAreaDto(areaDto);

        return dto;
    }

    @Test
    void testGetAll() throws Exception {
        when(taskServices.GetAllTaskDetails()).thenReturn(List.of(createValidTaskDto()));

        mockMvc.perform(get("/task/getAlltask"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetByTask() throws Exception {
        long id = 1L;
        when(taskServices.GetTaskDetaisById(id)).thenReturn(createValidTaskDto());

        mockMvc.perform(get("/task/id/{myid}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testInsert() throws Exception {
        long userId = 1L;
        TaskDto taskDto = createValidTaskDto();
        when(taskServices.sehedualeTask(any(TaskDto.class), eq(userId))).thenReturn(taskDto);
        mockMvc.perform(post("/task/Insert/{myid}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Done insert task"));


    }

    @Test
    void testUpdate() throws Exception {
        long taskId = 1L;
        String status = "Completed";

        when(taskServices.updateStatus(taskId, status)).thenReturn("update");
        mockMvc.perform(put("/task/{myid}/status", taskId)
                .param("status", status))
                .andExpect(status().isOk())
                .andExpect(content().string("update"));


    }

    @Test
    void testGetAllWeeklytask() throws Exception {
        String start = "2025-08-01";
        String end = "2025-08-07";
        when(taskServices.findWeeklyTasks(LocalDate.parse(start), LocalDate.parse(end)))
                .thenReturn(List.of(createValidTaskDto()));

        mockMvc.perform(get("/task/weekly-task")
                .param("start", start)
                .param("end", end))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetWeeklySummary() throws Exception {
        String start = "2025-08-01";
        String end = "2025-08-07";
        Map<String, Long> summary = Map.of("Completed", 3L, "Pending", 2L);

        when(taskServices.getWeekklySummary(LocalDate.parse(start), LocalDate.parse(end)))
                .thenReturn(summary);

        mockMvc.perform(get("/task/weekly-summary")
                .param("start", start)
                .param("end", end))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Completed").value(3))
                .andExpect(jsonPath("$.Pending").value(2));
    }
}
