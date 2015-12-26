package com.ravi.etl;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(JunitTestSuite.class);
	      for (Failure failure : result.getFailures()) {
	         System.out.println("\nFailed test case - " + failure.toString());
	      }
	      System.out.println("\nHave all Test cases passed? " + (result.wasSuccessful() ? "Yes" : "No"));
	}

}
