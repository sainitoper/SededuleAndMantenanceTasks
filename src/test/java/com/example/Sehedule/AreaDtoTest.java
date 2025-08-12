package com.example.Sehedule;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import com.example.Sehedule.Dtos.AreaDto;
import com.example.Sehedule.Dtos.TaskDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    
//   @Test
//   @Tag("fast")
//   void fast()
//   {
//	   System.out.println("fast run");
//   }
//    
//   @Test
//   @Tag("slow")
//   void slow()
//   {
//	   System.out.println("slow run");
//   }
    

}
