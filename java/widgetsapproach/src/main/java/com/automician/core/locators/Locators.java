package com.automician.core.locators;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class Locators {

    public static By dt(String attrDataTestValue) {
        return attrValue("data-test", attrDataTestValue);
    }

    public static By dtStarts(String attrDataTestValue) {
        return attrValueStarts("data-test", attrDataTestValue);
    }

    public static By oneOfDt(String... attrDataTestValues) {
        return oneOfAttrValues("data-test", attrDataTestValues);
    }

    //xpath = //*[@data-test='agb' or @data-test='security']/..
    //get elements with attr data-test and next get their parent
    public static By childHasOneOfDt(String... attrDataTestValues) {
        return childHasOneOfAttrValues("data-test", attrDataTestValues);
    }

    public static By attrValue(String attrName, String attrDataTestValue) {
        return By.cssSelector(getAttrValueSelector(attrName, attrDataTestValue));
    }

    public static By attrValueStarts(String attrName, String attrDataTestValue) {
        return By.cssSelector(getAttrValueSelector(attrName, attrDataTestValue, "^="));
    }

    public static By oneOfAttrValues(String attrName, String... attrDataTestValues) {
        List<String> cssSelectors = new ArrayList<>();

        for (String attrDataTestValue : attrDataTestValues) {
            cssSelectors.add(getAttrValueSelector(attrName, attrDataTestValue));
        }
        return By.cssSelector(String.join(",", cssSelectors));
    }

    //xpath = //*[@attrName='agb' or @attrName='security']/..
    //get elements with attr data-test and next get their parent
    public static By childHasOneOfAttrValues(String attrName, String... attrValues) {
        List<String> attrConditions = new ArrayList<>();

        for (String attrValue : attrValues) {
            attrConditions.add("@" + attrName + "='" + attrValue + "'");
        }
        return By.xpath("//*[" + String.join(" or ", attrConditions) + "]/..");
    }

    private static String getAttrValueSelector(String attrName, String attrDataTestValue) {
        return getAttrValueSelector(attrName, attrDataTestValue, "=");
    }

    private static String getAttrValueSelector(String attrName, String attrDataTestValue, String operator) {
        return "[" + attrName + operator + "'" + attrDataTestValue + "']";
    }

}
