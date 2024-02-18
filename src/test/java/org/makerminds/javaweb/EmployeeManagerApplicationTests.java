package org.makerminds.javaweb;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.makerminds.javaweb.controllers.DepartmentController;
import org.makerminds.javaweb.entity.Department;
import org.makerminds.javaweb.service.DepartmentService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class EmployeeManagerApplicationTests {

	private MockMvc mockMvc;
	@MockBean
	private DepartmentService departmentService;
	
	@Mock
	private BindingResult bindingResult;
	
	private ObjectMapper objectMapper;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc= MockMvcBuilders.standaloneSetup(new DepartmentController(departmentService)).build();
		objectMapper = new ObjectMapper();
	}
	@Test
	void testGetDepartments() throws Exception {
		//databasedata
		Department department1= new Department();
		department1.setId(1L);
		department1.setName("department1");
		Department department2= new Department();
		department2.setId(2L);
		department2.setName("department2");
		
		when(departmentService.getDepartments()).thenReturn(Arrays.asList(department1, department2));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/departments/all"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("department1"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("department2"));

	}
	
	@Test 
	void createDepartmentInvalidData() throws Exception {
		
		Department department = new Department();
		department.setName(" ");
		
		when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(new FieldError("department", "Name","The department name is required")));
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/departments")
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(department)))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
			
	}
	
	
	
	
	
	
	
}
