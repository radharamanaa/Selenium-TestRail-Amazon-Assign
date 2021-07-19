package config;

import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRailConfig {
    private static List<ResultField> customResultFields;
    private static Run run;
    private static TestRail testRail;
    private static Map<String, Case> testCases = new HashMap<>(10);
    private static Project project;
    public static void initialize(){
        testRail = TestRail.builder(InitialConfig.getDomainTestRail(),
                InitialConfig.getUserNameTR(),InitialConfig.getPasswordTR()).applicationName("Amazon Tests").build();
        project = testRail.projects().add(new Project().setName("Second-Project-2")).execute();
        Suite suite = testRail.suites().add(project.getId(), new Suite()
//                .setId(Integer.parseInt(InitialConfig.getSuiteIdTestRail()))
                .setName("Amazon Functional tests")).execute();
        Section section = testRail.sections().add(project.getId(),
                new Section().setSuiteId(suite.getId()).setName("All cases")).execute();

        List<CaseField> customCaseFields = testRail.caseFields().list().execute();
        Case addItemToCartCase = testRail.cases().add(section.getId(), new Case().setTitle(InitialConfig.getAddItemToCart()),
                customCaseFields).execute();
        Case addMobToCartCase = testRail.cases().add(section.getId(), new Case().setTitle(InitialConfig.getAddLastMobToCart()),
                customCaseFields).execute();
        Case addNewPayMethCase = testRail.cases().add(section.getId(), new Case().setTitle(InitialConfig.getAddNewPayMethod()),
                customCaseFields).execute();
        Case addNewAddressCase = testRail.cases().add(section.getId(), new Case().setTitle(InitialConfig.getAddNewAddress()),
                customCaseFields).execute();
        Case seeLastYearOrdersCase = testRail.cases().add(section.getId(), new Case().setTitle(InitialConfig.getSeeLastYearOrders()),
                customCaseFields).execute();
        run = testRail.runs().add(project.getId(), new Run().setSuiteId(
                suite.getId()).setName("Weekly Regression")).execute();
        testCases.put(addItemToCartCase.getTitle(), addItemToCartCase);
        testCases.put(addMobToCartCase.getTitle(), addMobToCartCase);
        testCases.put(addNewPayMethCase.getTitle(), addNewPayMethCase);
        testCases.put(addNewAddressCase.getTitle(), addNewAddressCase);
        testCases.put(seeLastYearOrdersCase.getTitle(), seeLastYearOrdersCase);
        customResultFields = testRail.resultFields().list().execute();
    }

    public static void addTestResult(String testName, boolean result){
        int statusId = result ? 1 : 5;
        testRail.results().addForCase(run.getId(), testCases.get(testName).getId(),
                new Result().setStatusId(statusId),customResultFields);
    }
    public static void finishUp(){
        testRail.runs().close(run.getId()).execute();
        testRail.projects().update(project.setCompleted(true)).execute();
    }
}
