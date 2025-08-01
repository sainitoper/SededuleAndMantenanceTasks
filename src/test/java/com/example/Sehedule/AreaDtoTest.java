package com.example.Sehedule;


import org.junit.jupiter.api.Test;

import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.Dtos.TaskDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

public class AreaDtoTest {

    @Test
    void testGettersAndSetters() {
        AreaDto area = new AreaDto();

        area.setId(5L);
        area.setFloor("FirstFloor");
        area.setWing("EastWing");

        List<TaskDto> tasks = new ArrayList<>();
        area.setTasks(tasks);

        assertEquals(5L, area.getId());
        assertEquals("FirstFloor", area.getFloor());
        assertEquals("EastWing", area.getWing());
        assertSame(tasks, area.getTasks());
    }
}
