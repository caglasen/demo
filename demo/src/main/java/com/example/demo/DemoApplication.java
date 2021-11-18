package com.example.demo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	/*
	public String getToDos(@RequestParam(value = "student", defaultValue = "1") int studentId){
		for(int i=0; i<)
		return
	}

	 */
	@GetMapping("students")
	public String getStudents(){
		//JSON parser object to parse read file
		JSONArray studentList = null;
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader("students.json"))
		{
			//Read JSON file
			Object obj = jsonParser.parse(reader);

			studentList = (JSONArray) obj;

			//Iterate over employee array
			studentList.forEach( stu -> parseStudentObject( (JSONObject) stu ) );

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return studentList.toString();
	}

	private static void parseStudentObject(JSONObject student)
	{
		//Get employee object within list
		JSONObject employeeObject = (JSONObject) student.get("student");

		//Get employee first name
		String firstName = (String) employeeObject.get("firstname");

	}
}
