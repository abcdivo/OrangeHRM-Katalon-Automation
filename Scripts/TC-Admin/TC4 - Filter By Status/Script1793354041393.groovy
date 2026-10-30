
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.By

WebUI.callTestCase(
    findTestCase('TC-Dashboard/TC4 - Navigate To Admin Page'),
    [:],
    FailureHandling.STOP_ON_FAILURE
)

WebUI.click(
    findTestObject('page_admin/dropdown_Status'),
    FailureHandling.STOP_ON_FAILURE
)

WebUI.click(
    findTestObject('page_admin/selectoptionStatus_Enabled'),
    FailureHandling.STOP_ON_FAILURE
)

WebUI.click(findTestObject('page_admin/button_Search'))

// Verify Search Results
String expectedStatus = 'Enabled'

List<WebElement> rows = WebUI.findWebElements(
    findTestObject('page_admin/table_user_rows'),
    10
)

assert rows.size() > 0 : 'Search result is empty'

rows.eachWithIndex { row, index ->

    String actualStatus = row.findElement(
        By.xpath("./div[@role='cell'][5]")
    ).getText().trim()

    assert actualStatus == expectedStatus :
        "Row ${index + 1}: Expected ${expectedStatus}, but found ${actualStatus}"

    WebUI.comment("Row ${index + 1}: Status = ${actualStatus}")
}

WebUI.closeBrowser()
