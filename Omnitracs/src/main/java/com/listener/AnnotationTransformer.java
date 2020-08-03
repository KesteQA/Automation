package com.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.utlis.TestUtils;

public class AnnotationTransformer implements IAnnotationTransformer{
	
	int count=0;
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		

		try {
			if(count==0) {
				TestUtils.getRunStatus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		for(int i=0;i<TestUtils.testCases.size();i++) {
			if(testMethod.getName().equalsIgnoreCase(TestUtils.testCases.get(i)))
			{	
				annotation.setRetryAnalyzer(RetryFailedTestCases.class); 	
				//annotation.setDescription(TestUtils.testDescription.get(i)); 							
				if(TestUtils.runStatus.get(i).equalsIgnoreCase("no")) {
					annotation.setEnabled(false);														
					break;
				}
			} 
		}

		count++;
	}
}
