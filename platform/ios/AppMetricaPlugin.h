#import <Foundation/Foundation.h>
#import <YandexMobileMetrica/YandexMobileMetrica.h>
#import <Cordova/CDV.h>

@interface AppMetricaPlugin : CDVPlugin
- (void)activate:(CDVInvokedUrlCommand*)command;
- (void)reportEvent:(CDVInvokedUrlCommand*)command;
- (void)reportEventJson:(CDVInvokedUrlCommand*)command;
@end
