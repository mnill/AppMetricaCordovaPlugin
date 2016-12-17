var cordova = require('cordova'),
	argscheck = require('cordova/argscheck'),
	utils = require('cordova/utils'),
	exec = require('cordova/exec');

var AppMetrica = {
	pluginName: 'AppMetricaPlugin',
	activate: function (api_key, successCallback, failureCallback) {
		api_key = (api_key instanceof Array) ? api_key[0] : api_key;
		cordova.exec(
			successCallback,
			failureCallback,
			this.pluginName,
			'activate',
			[api_key]
		);
	},
	reportEvent: function (eventName, successCallback, failureCallback) {
		cordova.exec(
			successCallback,
			failureCallback,
			this.pluginName,
			'reportEvent',
			[eventName]
		);
	},
	reportEventJson: function (eventName, json, successCallback, failureCallback) {
		cordova.exec(
			successCallback,
			failureCallback,
			this.pluginName,
			'reportEventJson',
			[eventName, json]
		);
	},
};
module.exports = AppMetrica;