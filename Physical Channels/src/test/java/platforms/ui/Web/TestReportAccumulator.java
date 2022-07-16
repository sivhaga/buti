package platforms.ui.Web;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import platforms.Report.TestExecutionReportWriter;
import utils.DriverFactory;


public class TestReportAccumulator {

    @Before
    public void beforeScenario(Scenario pScenario)
    {
        String test = "";
    }

    @After
    public void afterScenario(Scenario pScenario) throws Exception
    {
        String[] args = new String[6];
        args[0] = pScenario.getName();
        args[1] = pScenario.getStatus();
        args[2] = "WEB";
        args[3] = pScenario.getSourceTagNames().toString();
        try {
            args[4] = pScenario.getId().split(";")[0].toString();
        }
        catch(Exception e)
        {}

        try {
            TestExecutionReportWriter.ElasticSend(args);
        }
        catch(Exception e)
        {}
    }

   /* @After(order=0)
    public void clearDriver() {
        DriverFactory.driver = null;
    }*/

}
