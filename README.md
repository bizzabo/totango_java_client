# Totango Java Client

Java Client for sending data to Totango

For more information visit Totango's [site] (http://support.totango.com/hc/en-us/articles/203639605-Server-Backend-Integration-HTTP-)

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

Another example of usage:
```
Totango.track(account,user)
      .activity(activity)
      .displayName(displayName)
      .module(module)
      .attribute(key, value)
      .send();
```
## License

Copyright 2016 Bizzabo, Inc.

Licensed under the Apache License, Version 2.0 (the “License”); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an “AS IS” BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
