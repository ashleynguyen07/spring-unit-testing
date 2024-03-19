package com.example.SpringJUnitMockito.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.springunittesting.data.SomeDataService;
import com.example.springunittesting.service.SomeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SomeServiceMockTest {

  SomeServiceImpl service = new SomeServiceImpl();
  SomeDataService dataService = mock(SomeDataService.class);

  @BeforeEach
  public void before() {
    service.setSomeDataService(dataService);
  }

  @Test
  void calSumUsingDataService_empty() {
    when(dataService.retrieveAllData()).thenReturn(new int[]{});
    int actualResult = service.calSumUsingDataService();
    int expectTedResult = 0;
    assertEquals(expectTedResult, actualResult);
  }

  @Test
  void calSumUsingDataServiceBasic() {
    when(dataService.retrieveAllData()).thenReturn(new int[]{1, 2, 3, 3});
    int actualResult = service.calSumUsingDataService();
    int expectTedResult = 9;
    assertEquals(expectTedResult, actualResult);
  }
}
