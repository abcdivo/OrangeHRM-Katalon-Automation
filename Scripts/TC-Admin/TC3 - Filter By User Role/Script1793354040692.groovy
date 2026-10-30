
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
    findTestObject('page_admin/dropdown_User Role'),
    FailureHandling.STOP_ON_FAILURE
)

WebUI.click(
    findTestObject('page_admin/selectoptionUserrole_ESS'),
    FailureHandling.STOP_ON_FAILURE
)


WebUI.click(findTestObject('page_admin/button_Search'))

// Verify Search Results
String expectedUserRole = 'ESS'

List<WebElement> rows = WebUI.findWebElements(
    findTestObject('page_admin/table_user_rows'),
    10
)

assert rows.size() > 0 : 'Search result is empty'

rows.eachWithIndex { row, index ->

    String actualUserRole = row.findElement(
        By.xpath("./div[@role='cell'][3]")
    ).getText().trim()

    assert actualUserRole == expectedUserRole :
        "Row ${index + 1}: Expected ${expectedUserRole}, but found ${actualUserRole}"

    WebUI.comment("Row ${index + 1}: User Role = ${actualUserRole}")
}

// Close Browser
WebUI.closeBrowser()
