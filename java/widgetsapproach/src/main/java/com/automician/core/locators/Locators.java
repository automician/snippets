package com.automician.core.locators;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class Locators {

    public static By attrValue(String attrName, String attrValue) {
        return By.cssSelector(getAttrValueSelector(attrName, attrValue));
    }

    public static By attrValueStarts(String attrName, String attrValue) {
        return By.cssSelector(getAttrValueSelector(attrName, attrValue, "^="));
    }

    public static By oneOfAttrValues(String attrName, String... attrValues) {
        List<String> cssSelectors = new ArrayList<>();

        for (String attrValue : attrValues) {
            cssSelectors.add(getAttrValueSelector(attrName, attrValue));
        }
        return By.cssSelector(String.join(",", cssSelectors));
    }

    //xpath = //*[@attrName='some  value 1' or @attrName='some value 2']/..
    //get elements with attr attrName and next get their parent
    public static By childHasOneOfAttrValues(String attrName, String... attrValues) {
        List<String> attrConditions = new ArrayList<>();

        for (String attrValue : attrValues) {
            attrConditions.add("@" + attrName + "='" + attrValue + "'");
        }
        return By.xpath("//*[" + String.join(" or ", attrConditions) + "]/..");
    }

    private static String getAttrValueSelector(String attrName, String attrValue) {
        return getAttrValueSelector(attrName, attrValue, "=");
    }

    private static String getAttrValueSelector(String attrName, String attrValue, String operator) {
        return "[" + attrName + operator + "'" + attrValue + "']";
    }

}
