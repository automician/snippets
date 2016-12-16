package com.automician.core.basewidgets;

import com.automician.core.angular.entities.AngularCollection;
import com.automician.core.angular.entities.AngularElement;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

import static com.automician.core.locators.Locators.dt;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;

public class TextFieldGroup {

    private AngularElement self;
    private String[] attrDataTestValues;

    private List<SelenideElement> elements;

    public TextFieldGroup(AngularElement container) {
        this.self = container;
    }

    public TextFieldGroup(AngularElement container, String... attrDataTestValues) {
        this.self = container;
        this.attrDataTestValues = attrDataTestValues;
    }

    private List<AngularElement> getElements(int minimumOfCount) {
        List<AngularElement> elements = new ArrayList<AngularElement>();

        if (attrDataTestValues == null) {
            AngularCollection collection = self.findAll("input").shouldHave(sizeGreaterThanOrEqual(minimumOfCount));
            for (AngularElement element : collection) {
                elements.add(element);
            }
        } else {
            for (String attrDataTestValue : attrDataTestValues) {
                elements.add(self.find(dt(attrDataTestValue)));
            }
        }
        return elements;
    }

    public void fill(String... texts) {
        List<AngularElement> elements = getElements(texts.length);

        for (int i = 0; i < Math.min(texts.length, elements.size()); i++) {
            elements.get(i).setValue(texts[i]);
        }
    }
}
