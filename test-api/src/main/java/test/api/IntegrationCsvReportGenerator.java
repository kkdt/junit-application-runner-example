package test.api;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class IntegrationCsvReportGenerator extends RunListener {
    private final String directory;
    private final String outputReport;
    private final CSVWriter csvWriter;

    public IntegrationCsvReportGenerator(String directory) throws IOException {
        this.directory = directory;
        this.outputReport = String.format("%s%s%s",
            directory,
            File.separator,
            "results.csv");
        this.csvWriter = new CSVWriter(new FileWriter(new File(outputReport)));
    }

    @Override
    public void testRunStarted(Description description) throws Exception {
        String[] header = {"Result", "Test", "Class", "Method", "Message", "Stacktrace"};
        csvWriter.writeNext(header, true);
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        csvWriter.close();
    }

    @Override
    public void testStarted(Description description) throws Exception {
        super.testStarted(description);
    }

    @Override
    public void testFinished(Description description) throws Exception {
        Integration annotation = description.getTestClass().getAnnotation(Integration.class);
        csvWriter.writeNext(new String[]
            {
                "SUCCESS",
                annotation.bean(),
                description.getClassName(),
                description.getMethodName(),
                "NA",
                "NA"
            }, true);
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        handleFailure(failure);
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        handleFailure(failure);
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        Integration annotation = description.getTestClass().getAnnotation(Integration.class);
        csvWriter.writeNext(new String[]
            {
                "IGNORED",
                annotation.bean(),
                description.getClassName(),
                description.getMethodName(),
                "NA",
                "NA"
            }, true);
    }

    private void handleFailure(Failure failure) {
        Integration annotation = failure.getDescription().getTestClass().getAnnotation(Integration.class);
        csvWriter.writeNext(new String[]
            {
                "FAILED",
                annotation.bean(),
                failure.getDescription().getClassName(),
                failure.getDescription().getMethodName(),
                failure.getMessage(),
                failure.getTrace()
            }, true);
    }
}
