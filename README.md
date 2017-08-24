PhoneGap\XDK Cordova plug-in for Yandex Metrica 2.0

Android and iOS are supported

Three methods supported:

window.AppMetrica.activate('key here' [, success, fail]);

window.AppMetrica.reportEvent('event' [, success, fail]);

window.AppMetrica.reportEventJson('event', json [, success, fail]);


Call reportEvent before u got success on activate can cause crash on android. 
