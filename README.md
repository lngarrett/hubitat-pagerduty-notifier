# Hubitat PagerDuty Notifier

This Hubitat app allows you to send notifications from your Hubitat devices to PagerDuty.

## Installation

- Navigate to the Apps Code section of your Hubitat dashboard.
- Click on the **New App** button.
- Copy and paste the code from `app.groovy` into the code editor.
- Click the **Save** button.

## Configuration

- In the Hubitat dashboard, go to the **Apps** section.
- Click on the **Add User App** button.
- Select **PagerDuty Notifier** from the list of available apps.
- Enter your PagerDuty API key in the **PagerDuty API Key** field.
- Enter your PagerDuty service key in the **PagerDuty Service Key** field.
- Select the desired logging level from the **IDE logging level** dropdown.
- Click the **Done** button to save the configuration.

## Usage

The PagerDuty Notifier app can be used as a standard notifier across Hubitat, such as in the Rule Machine app. It allows you to send notifications to PagerDuty based on specific events or conditions in your Hubitat environment.

For example, you can set up a rule in Rule Machine to send a notification to PagerDuty whenever a door is opened unexpectedly. Simply configure the rule to trigger when the door sensor detects an open state and use the PagerDuty Notifier app as the action to send the notification.

Please note that this app only creates incidents in PagerDuty and does not automatically resolve them. It is recommended to configure auto-resolution on the corresponding PagerDuty service to ensure that incidents are resolved automatically when the triggering condition is no longer met.

## License

This app is licensed under the Apache License, Version 2.0. See the LICENSE file for more information.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request on the GitHub repository.