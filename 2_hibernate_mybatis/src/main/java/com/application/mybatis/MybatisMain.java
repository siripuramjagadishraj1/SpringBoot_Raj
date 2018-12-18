package com.application.mybatis;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisMain {
	
	public static void main(String[] args) throws Exception{
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		//session related configuration
		//session.getConnection().setAutoCommit(autoCommit);
		//Connection conn = session.getConnection(); //This way we can get database connection and work on

		//Get All students
		List<Student> studentList = session.selectList("personMapper.getAllStudents");
		System.out.println(studentList);
		
		//Types of INPUTS, Date is pending..
		//Get 1 Student (1 int param as input)
		studentList = session.selectList("personMapper.getEachStudentById",  1);
		System.out.println(studentList);
		
		//Get 1 Student (1 String param as input)
		studentList = session.selectList("personMapper.getEachStudentByName", "Raj");
		System.out.println(studentList);
		
		//Get 1 Student (Sending 2 params from dto)
		Student student = new Student();
		student.setSid(1);
		student.setSname("Jagadish");
		studentList = session.selectList("personMapper.getEachStudentByNameDemo", student);
		System.out.println(studentList);
		
		//Get 1 Student (Sending 2 params from map)
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sidMap", 1);
		paramMap.put("snameMap", "Jagadish");
		studentList = session.selectList("personMapper.getEachStudentByNameDemoMap", paramMap);
		System.out.println(studentList);
		
		//Get 1 Student (where in)
		List<Integer> arraylist = Arrays.asList(1, 2);
		Map<String, Object> paramMap2 = new HashMap<String, Object>();
		paramMap2.put("arraylistDemo", arraylist);
		paramMap2.put("name", "Raj");
		System.out.println(session.selectList("personMapper.getEachStudentByNameDemoListParam", paramMap2));
		System.out.println(session.selectList("personMapper.getEachStudentByNameDemoListParamIn", arraylist));
		System.out.println(session.selectList("personMapper.getLikeStudent", "Jaga"));  //like
		
		
		//Insert
		Student insertStudent = new Student("Jayanth", "Jayanth@gmailc.om");
		System.out.println(session.insert("personMapper.insert",insertStudent));
		System.out.println(insertStudent);
		session.commit();
		
		//Update
		Map<String, Object> nameParam= new HashMap<String, Object>();
		nameParam.put("newName", "Jagadish");
		nameParam.put("oldName", "Jagadish");
		System.out.println(session.update("personMapper.update", nameParam));
		session.commit();
		
		//Delete
		System.out.println(session.delete("personMapper.delete", 1));
		session.commit();
		session.close();
		
	}
}
