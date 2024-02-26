package com.project.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.internal.build.AllowSysOut;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaTest {

	// @Test
	void 람다테스트1() {
		List<String> fruits = List.of("apple","banana","cherry");
		fruits // 반복문 돌리는거처럼
		.stream() //계속 줄줄이 이어지다.
		.filter(fruit -> fruit.startsWith("b")) // 비로 시작하는 거라면
		.forEach(fruit -> log.info("###{}", fruit));
	}
	
	@Test
	void 람다테스트2() {
		List<String> fruits = List.of("apple","banana","cherry");
		fruits = fruits
		.stream()
		.map(fruit -> fruit.toUpperCase())
		.collect(Collectors.toList()); // stream to list
		
		log.info(fruits.toString());
	}
	
	
	@Test
	void 람다_메소드레퍼런스() {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("최아름", 33));
		personList.add(new Person("신보람", 30));
		
		// 객체 안에 있는 메소드 호출
		personList.forEach(p -> p.printInfo()); // 람다식으로 출력한것
		personList.forEach(Person::printInfo); // 메소드 레퍼런스
		
		// 객체를 println으로 출력
		personList.forEach(p -> System.out.println(p)); // 람다
	}
	
	@ToString
	@AllArgsConstructor
	class Person {
		private String name;
		private int age;
		
		public void printInfo() {
			log.info("### " + this);
		}
	}
}
