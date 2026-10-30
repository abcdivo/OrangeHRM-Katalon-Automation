
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

WebUI.click(findTestObject('page_admin/dropdown_User Role'))

WebUI.click(findTestObject('page_admin/selectoptionUserrole_ESS'))

WebUI.click(findTestObject('page_admin/dropdown_Status'))

WebUI.click(findTestObject('page_admin/selectoptionStatus_Enabled'))

WebUI.click(findTestObject('page_admin/button_Search'))

WebUI.click(findTestObject('page_admin/button_Reset'))

// Verify Username Field Is Empty
WebUI.verifyElementAttributeValue(
    findTestObject('page_admin/field_Username'),
    'value',
    '',
    10
)

// Verify User Role Is Reset
WebUI.verifyElementText(
    findTestObject('page_admin/dropdown_User Role'),
    '-- Select --'
)

// Verify Status Is Reset
WebUI.verifyElementText(
    findTestObject('page_admin/dropdown_Status'),
    '-- Select --'
)

WebUI.closeBrowser()
