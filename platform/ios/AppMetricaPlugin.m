#import "AppMetricaPlugin.h"

@implementation AppMetricaPlugin


- (void)activate:(CDVInvokedUrlCommand*)command
{
    if ([command.arguments count] == 1) {
        NSString* devKey = [command.arguments objectAtIndex:0];
        [YMMYandexMetrica activateWithApiKey:devKey];
        
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    } else {
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Wrong arguments"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
};

- (void)reportEvent:(CDVInvokedUrlCommand *)command
{
    if ([command.arguments count] == 1) {
        NSString* eventName = [command.arguments objectAtIndex:0];
        [YMMYandexMetrica reportEvent:eventName
                            onFailure:^(NSError *error) {
                                NSLog(@"DID FAIL REPORT EVENT: %@", eventName);
                                NSLog(@"REPORT ERROR: %@", [error localizedDescription]);
                            }];
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    } else {
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Wrong arguments"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
};

- (void)reportEventJson:(CDVInvokedUrlCommand *)command
{
    if ([command.arguments count] == 2) {
        NSString* eventName = [command.arguments objectAtIndex:0];
        NSDictionary* params = [command.arguments objectAtIndex:1];
        [YMMYandexMetrica reportEvent:eventName
                           parameters:params
                            onFailure:^(NSError *error) {
                                NSLog(@"error: %@", [error localizedDescription]);
                            }];
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    } else {
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"Wrong arguments"];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
};

@end
