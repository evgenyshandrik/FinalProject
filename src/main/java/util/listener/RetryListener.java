package util.listener;

import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {

    public void transform(ITestAnnotation testAnnotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        IRetryAnalyzer retry = testAnnotation.getRetryAnalyzer();

        if (retry == null) {
            testAnnotation.setRetryAnalyzer(Retry.class);
        }

    }
}