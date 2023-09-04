package org.qcmg.hairpin;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;


//@SpringBootTest
class HairpinApplicationTests {


	@Test
	void contextLoads() {
	}
	
	@Test
	void xuTest() {
		int i = 9;
		int j = i ++;
		System.out.println("i = " + i + "; j = " + j);
	}
	
	@Test
	void sortTest() {
		List<Student> students = new ArrayList<>();		

		students.add(new Student(1, "Bairu"));
		students.add(new Student(0, "Olivia"));
		students.add(new Student(3, "third"));
		students.add(new Student(7, "seventh"));

		List<Integer> orders = new ArrayList<>();
		for(int i = 0; i < 4; i ++) orders.add(i);
		Comparator<Integer> myCompare = (a, b) -> students.get(a).id - students.get(b).id; 
		Collections.sort(orders,myCompare);
		orders.forEach(System.out::println); 
		
	}

	
	class Student {
		public int id;
		public String name;
		
		Student(int id, String name) {
			this.id = id;
			this.name = name; 
		}
		
		int getId() {return id;}
		
		int compareTo(Student s2){
			return this.id  - s2.id; 
		}
 
	}

}
