# AuthApp

Auth app is a simple login and registration app made in kotlin, to show the authentication out flow. I have also made a backend for it in ktor - https://github.com/arseniyGoryagin/AuthApp_backend

## Installation

clone the repository


```bash
git clone https://github.com/arseniyGoryagin/AuthApp.git
```

if you want to use it with my backend (https://github.com/arseniyGoryagin/AuthApp_backend) you should make a file called "network_security_config.xml" in /app/src/main/res/xml/, for andorid to be able to connect to your server ip.

example network_security_config.xml:

```xml
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">YOUR IP HERE</domain>
    </domain-config>
</network-security-config>
```
