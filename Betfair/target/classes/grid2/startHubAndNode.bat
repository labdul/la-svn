cd %~dp0
start "HUB" java -jar selenium-server-standalone-2.30.0.jar -role hub -hubConfig server.json
cd %~dp0
start "NODE" java -jar selenium-server-standalone-2.30.0.jar -role node -nodeConfig node.json  -Dwebdriver.chrome.driver=chromedriver