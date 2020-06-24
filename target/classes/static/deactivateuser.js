function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function getUrlParam(parameter, defaultvalue){
    var urlparameter = defaultvalue;
    if(window.location.href.indexOf(parameter) > -1){
        urlparameter = getUrlVars()[parameter];
    }
    return urlparameter;
}

var userId = decodeURI(getUrlParam('userid','Empty'));

if(userId!="Empty") {
    fetch(url + '/Users/' + userId, {credentials: 'include'})
        .then(function (response) {
            return response.json();
        })
        .then(function (data) {
            appendData(data);
        })
        .catch(function (err) {
            console.log(err);
        });
}

function appendData(data) {
    document.getElementById("userid").value = data.id;
    var mainContainer = document.getElementById("deactivateuserData");
    var div = document.createElement("div");
    div.innerHTML =  "<br> Username: " + data.username + "<br>email: " + data.email + "<br>address: " + data.address;
    mainContainer.appendChild(div);
}