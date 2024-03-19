package com.example.springunittesting.service;

import com.example.springunittesting.data.SomeDataService;
import lombok.Data;

@Data
public class SomeServiceImpl {
  SomeDataService someDataService;
  public int calSum (int[] data) {
    int sum = 0;
    for (int value : data) {
      sum += value;
    }
    return sum;
  }

  public int calSumUsingDataService () {
    int sum = 0;
    int[] data = someDataService.retrieveAllData();
    for (int value : data) {
      sum += value;
    }
    return sum;
  }
}
