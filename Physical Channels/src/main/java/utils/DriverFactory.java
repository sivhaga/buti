package utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static Logger log = Logger.getLogger(DriverFactory.class);
    public static String propertyFile = "./Framework.properties";
    Utils util = new Utils();

	}
