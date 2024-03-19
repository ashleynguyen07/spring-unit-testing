package com.example.SpringJUnitMockito.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.springunittesting.data.SomeDataService;
import com.example.springunittesting.service.SomeServiceImpl;
import org.junit.jupiter.api.Test;

class SomeDataServiceSubBasic implements SomeDataService {
  @Override
  public int[] retrieveAllData() {
    return new int[] {1,2,3,3};
  }
}
class SomeDataServiceSubEmpty implements SomeDataService {
  @Override
  public int[] retrieveAllData() {
    return new int[] {};
  }
}
class SomeServiceSubTest {

  @Test
  void calSumUsingDataServiceBasic() {
    SomeServiceImpl service = new SomeServiceImpl();
    service.setSomeDataService(new SomeDataServiceSubBasic());
    int actualResult = service.calSumUsingDataService();
    int expectTedResult = 9;
    assertEquals(expectTedResult, actualResult);
  }

  @Test
  void calSumUsingDataService_empty() {
    SomeServiceImpl service = new SomeServiceImpl();
    service.setSomeDataService(new SomeDataServiceSubEmpty());
    int actualResult = service.calSumUsingDataService();
    int expectTedResult = 0;
    assertEquals(expectTedResult, actualResult);
  }

}
