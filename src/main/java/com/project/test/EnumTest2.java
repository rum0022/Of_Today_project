package com.project.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class EnumTest2 {

	enum PayType {
		// 열거형 정의
		CASH("현금", List.of("REMITTANCE", "ACCOUNT_TRANSFER"))
		, CARD("카드", List.of("CREDIT", "NAVER"))
		, EMPTY("없음", Collections.emptyList()); // 실무에서는 new ArrayList 보다 더 잘씀
		
		// 필드
		private String title;
		private List<String> payList;
		
		// 생성자
		PayType(String title, List<String> payList) {
			this.title = title;
			this.payList = payList;
		}
		
		// 결제수단(리스트 안의 값) String이 enum에 존재하는지 확인
		public boolean hasPayMethod(String payMethod) {
			return payList.stream() // payList 순회
					.anyMatch(pay -> pay.equals(payMethod)); // 하나라도 같은게 있으면 true
		}
		
		// 결제 수단(String)으로 enum 타입(부모) 찾기
		public static PayType findByPayMethod(String payMethod) {
			return Arrays.stream(PayType.values()) // PayType의 열거형 변수들을 stream으로 변환
					.filter(payType -> payType.hasPayMethod(payMethod)) // payMethod와 같은 type을 뽑아냄
					.findAny()  // 찾은 요소 반환
					.orElse(EMPTY); // 없으면 PayType.EMPTY로 리턴됨
		}
	}
	
	@Test
	void pay테스트() {
		// given 준비
		String payMethod = "NAVER";
		
		// when 실행
		// 결제 수단에 해당하는 결제 종류는?
		PayType payType = PayType.findByPayMethod(payMethod);
	
		// then 확인
		assertEquals(payType, payType.CARD);
		
	}
}
