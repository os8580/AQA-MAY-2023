package org.prog.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AllureDemo {


    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void criticalTest() {
        allureStep("critical");
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    public void blockerTestTest() {
        allureStep("blocker");
    }

    @Test
    @Severity(SeverityLevel.TRIVIAL)
    public void trivialTest() {
        allureStep("trivial");
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    public void minorTest() {
        allureStep("minor");
    }

    @Step("This is allure step!")
    private void allureStep(String s) {
        System.out.println(s);
        if ("minor".equals(s)) {
            Assert.fail("This is a minor failure!");
        }
    }
}
