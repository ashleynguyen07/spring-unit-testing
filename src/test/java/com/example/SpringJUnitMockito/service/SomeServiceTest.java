package com.example.SpringJUnitMockito.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.springunittesting.service.SomeServiceImpl;
import org.junit.jupiter.api.Test;

class SomeServiceTest {

  @Test
  void calSumBasic() {
    SomeServiceImpl service = new SomeServiceImpl();
    int actualResult = service.calSum(new int[] {1,2,3});
    int expectTedResult = 6;
    assertEquals(expectTedResult, actualResult);
  }

  @Test
  void calSum_empty() {
    SomeServiceImpl service = new SomeServiceImpl();
    int actualResult = service.calSum(new int[] {});
    int expectTedResult = 0;
    assertEquals(expectTedResult, actualResult);
  }

}
