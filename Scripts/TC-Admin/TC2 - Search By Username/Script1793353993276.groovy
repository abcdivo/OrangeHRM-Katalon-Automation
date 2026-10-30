
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.By


WebUI.callTestCase(
    findTestCase('TC-Dashboard/TC4 - Navigate To Admin Page'),
    [:],
    FailureHandling.STOP_ON_FAILURE
)

WebUI.click(findTestObject('page_admin/field_Username'))

WebUI.setText(
    findTestObject('page_admin/field_Username'),
    GlobalVariable.validUsername
)

WebUI.click(findTestObject('page_admin/button_Search'))

// Verify Search Results
String expectedUsername = GlobalVariable.validUsername

List<WebElement> rows = WebUI.findWebElements(
    findTestObject('page_admin/table_user_rows'),
    10
)

assert rows.size() > 0 : 'Search result is empty'

rows.eachWithIndex { row, index ->

    String actualUsername = row.findElement(
        By.xpath("./div[@role='cell'][2]")
    ).getText().trim()

    assert actualUsername == expectedUsername :
        "Row ${index + 1}: Expected ${expectedUsername}, but found ${actualUsername}"

    WebUI.comment("Row ${index + 1}: Username = ${actualUsername}")
}

WebUI.closeBrowser()
