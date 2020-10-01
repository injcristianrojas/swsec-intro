var host = "127.0.0.1";
var port = 1337;
var express = require("express");

var app = express();
var dir = __dirname + '/dist/angular-app'
app.use((req, res, next) => {
    res.append('Cache-Control', "no-cache, no-store, must-revalidate");
    res.append('Pragma', "no-cache");
    res.append('Expires', "0");
    next();
});
app.use('/', express.static(dir));
app.listen(port, host);

console.log('Running server at http://localhost:' + port + '/ on ' + dir);