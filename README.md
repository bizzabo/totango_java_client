# Totango Java Client

Java Client for sending data to Totango

For more information visit Totango site:

http://support.totango.com/hc/en-us/articles/203639605-Server-Backend-Integration-HTTP-

## Usage 
Initialize Totango client with your service id (One time)
```
Totango.serviceId(serviceId);
```

Create records using the builder methods
```
Track sdr = Totango.track(account)
```
Or
```
Track sdr = Totango.track(account,user)
```
Collect your data 
```
sdr.activity(activity);
sdr.displayName(displayName);
sdr.module(module);
```
Add custom attributes
```
sdr.attribute(key, value);
```
And lastly send your record 
```
sdr.send()
```

Another usage example:
```
Totango.track(account,user)
      .activity(activity)
      .displayName(displayName)
      .module(module)
      .attribute(key, value)
      .send();
```
