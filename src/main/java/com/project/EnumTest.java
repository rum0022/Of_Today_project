package com.project;

public class EnumTest {
	
	//@Getter
	public enum status {
		// 열거형 정의
		Y(1, true),
		N(0, false)
		;
		// enum에 정의된 항목의 필드
		private int value1;
		private boolean value2;
		
		// 생성자
		status(int value1, boolean value2) {
			this.value1 = value1;
			this.value2 = value2;
 		}
		
//		@Test
//		void status테스트() {
//			// given (준비)
//			status s = status.Y;
//			
//			// when (실행)
//		 	int value1 = s.getValue1();
//		 	boolean value2 = s.isValue2(); // 불린은 is로 꺼내진다.
//			
//			// then (검증)
//		 	assertEquals(status, status.Y);
//		 	assertEquals(1, value1);
//		 	assertEquals(true, value2);
		 	
		}
	}

