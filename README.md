# Spring multi-context config server

Setup: the spring boot config server is running as standalone application 
with embedded web server inside. By design a config server client can pass 
the only three parameters: application, profile and label. So we can 
configure one project set (project pool) with one config server. The 
usual logical structure of configuration folder looks like below:

<pre>
config/
   service-xxxxx/
      service-xxxxx.yaml  
      service-xxxxx-dev.yaml  
      service-xxxxx-prod.yaml  
   application.yaml
   application-dev.yaml  
   application-prod.yaml  
</pre>

Using the layout above we can manage the configuration for one project 
pool with common application.yaml file. What if we need more project sets 
managed within the same config server instance?

<pre>
project-pool-x/
   service-xxxxx/
      service-xxxxx.yaml  
      service-xxxxx-dev.yaml  
      service-xxxxx-prod.yaml  
   application.yaml
   application-dev.yaml  
   application-prod.yaml  

project-pool-y/
   service-yyyyy/
      service-yyyyy.yaml  
      service-yyyyy-dev.yaml  
      service-yyyyy-prod.yaml  
   application.yaml
   application-dev.yaml  
   application-prod.yaml  
</pre>

The project is a POC for this use case. There are several config-server 
enabled contexts (tenants) under single boot application.
