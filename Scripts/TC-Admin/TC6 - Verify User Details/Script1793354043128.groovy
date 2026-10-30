
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

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

WebUI.click(
    findTestObject('page_admin/button_Edit_User'),
    FailureHandling.STOP_ON_FAILURE
)


WebUI.verifyElementAttributeValue(
    findTestObject('page_admin/field_Edit_Username'),
    'value',
    GlobalVariable.validUsername,
    10
)


WebUI.verifyElementVisible(
    findTestObject('page_admin/dropdown_Edit_User_Role')
)


WebUI.verifyElementVisible(
    findTestObject('page_admin/dropdown_Edit_Employee_Name')
)


WebUI.verifyElementVisible(
    findTestObject('page_admin/dropdown_Edit_Status')
)


WebUI.closeBrowser()
