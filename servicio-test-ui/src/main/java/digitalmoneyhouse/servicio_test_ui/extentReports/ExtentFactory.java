package digitalmoneyhouse.servicio_test_ui.extentReports;

import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = new ExtentReports();
            extent.setSystemInfo("Selenium Version", "4.29.0");
            extent.setSystemInfo("SO", "Windows 11");
            extent.setSystemInfo("Navegador", "Chrome");
            extent.setSystemInfo("ENV", "QA");
        }
        return extent;
    }
}

