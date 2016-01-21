# totango_java_client
Client to send Totango sdr records 


# Initialize Totango client with your service id (One time)
Totango.serviceId(serviceId);


# Create records using the builder methods
Track sdr = Totango.track(account)
# Or 
Track sdr = Totango.track(account,user)

# Collect your data 
sdr.activity(activity);
sdr.displayName(displayName);
sdr.module(module);

# Add custom attributes
sdr.attribute(key, value);

# And lastly send the record 
sdr.send()
