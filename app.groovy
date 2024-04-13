/*****************************************************************************************************************
 *  Source: https://github.com/lngarrett/hubitat-pagerduty-notifier
 *
 *  Raw Source: https://raw.githubusercontent.com/lngarrett/hubitat-pagerduty-notifier/main/app.groovy
 *
 *
 *  Description: Sends notifications to pagerduty
 *
 *  License:
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *   on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *   for the specific language governing permissions and limitations under the License.
 *
 *   Modifcation History
 *   Date       Name            Change
 *   2024-04-12 Logan Garrett   Initital release
 *****************************************************************************************************************/

metadata {
    definition (
        name: "PagerDuty Notifier",
        namespace: "pagerdutynotifier",
        author: "Logan Garrett",
        importUrl: "https://raw.githubusercontent.com/lngarrett/hubitat-pagerduty-notifier/main/app.groovy") { capability "Notification" }

    preferences {
        input "apiKey", "text", title: "PagerDuty API Key", defaultValue: "", required: true, displayDuringSetup: true
        input "serviceKey", "text", title: "PagerDuty Service Key", defaultValue: "", required: true, displayDuringSetup: true
        input(name: "logLevel", title: "IDE logging level", multiple: false, required: true, type: "enum", options: getLogLevels(), submitOnChange : false, defaultValue : "1")
    }
}

def installed() {
    unsubscribe()
    unschedule()
    initialize()
}

def updated() {
}

private def initialize() {
}

def deviceNotification(text){
    def params = [
        uri: "https://events.pagerduty.com/v2/enqueue",
        headers: [
            "Content-Type": "application/json",
            "Authorization": "Token token=${settings.apiKey}"
        ],
        body: [
            "payload": [
                "summary": "${text}",
                "severity": "critical",
                "source": "Hubitat Notifier",
                "component": "Hubitat Device"
            ],
            "routing_key": "${settings.serviceKey}",
            "event_action": "trigger"
        ]
    ]

    try {
        httpPostJson(params) { resp ->
            resp.headers.each {
                debuglog("${it.name} : ${it.value}")
            }
            debuglog("response contentType: ${resp.contentType}")
        }
    } catch (e) {
        debuglog("something went wrong: $e")
    }
}

def debuglog(statement)
{
    def logL = 0
    if (logLevel) logL = logLevel.toInteger()
    if (logL == 0) {return}//bail
    else if (logL >= 2)
    {
        log.debug(statement)
    }
}

def infolog(statement)
{
    def logL = 0
    if (logLevel) logL = logLevel.toInteger()
    if (logL == 0) {return}//bail
    else if (logL >= 1)
    {
        log.info(statement)
    }
}

def getLogLevels(){
    return [["0":"None"],["1":"Running"],["2":"NeedHelp"]]
}