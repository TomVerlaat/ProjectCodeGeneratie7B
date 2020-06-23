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
    document.getElementById("username").value = data.username;
    document.getElementById("password").value = data.password;
    document.getElementById("firstname").value = data.firstName;
    document.getElementById("lastname").value = data.lastName;
    document.getElementById("email").value = data.email;
    document.getElementById("birthdate").value = data.birthdate;
    document.getElementById("address").value = data.address;
    document.getElementById("postalcode").value = data.postalcode;
    document.getElementById("city").value = data.city;
    document.getElementById("phonenumber").value = data.phoneNumber;
    document.getElementById("type").value = data.type;
    document.getElementById("transactionLimit").value = data.transactionLimit;
    document.getElementById("maximumDebt").value = data.maximumDebt;

}