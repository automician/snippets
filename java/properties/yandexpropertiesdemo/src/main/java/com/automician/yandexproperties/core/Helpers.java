package com.automician.yandexproperties.core;

import org.apache.commons.lang3.StringUtils;
import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource;

import java.io.IOException;
import java.util.Map;

public class Helpers {

    public static EnvProperties getEnvProperties(String profileName) {
        if (profileName.equals("dev")) {
            return new DevEnvProperties();
        } else if (profileName.equals("test")) {
            return new TestEnvProperties();
        } else if (profileName.equals("prod")) {
            return new ProdEnvProperties();
        } else {
            return new TestEnvProperties();
        }
    }

    public interface EnvProperties {
        String getAppUrl();

        String getBrowserName();
    }

    abstract static class AbstractEnvProperties implements EnvProperties {

        AbstractEnvProperties() {
            PropertyLoader.newInstance().populate(this);
        }

        @Property("app.url")
        String appUrl;

        @Property("browser")
        String browser;

        @Override
        public String getAppUrl() {
            return appUrl;
        }

        @Override
        public String getBrowserName() {
            return browser;
        }
    }

    @Resource.Classpath("dev.properties")
    static class DevEnvProperties extends AbstractEnvProperties {
        DevEnvProperties() {
            super();
        }
    }

    @Resource.Classpath("prod.properties")
    static class ProdEnvProperties extends AbstractEnvProperties {
        ProdEnvProperties() {
            super();
        }
    }

    @Resource.Classpath("test.properties")
    static class TestEnvProperties extends AbstractEnvProperties {
        TestEnvProperties() {
            super();
        }
    }

}
