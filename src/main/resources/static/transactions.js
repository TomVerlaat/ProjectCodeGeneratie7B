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

var account = decodeURI(getUrlParam('account','Empty'));

if(account!="Empty") {
    fetch(url + '/Transactions/all/' + account, {credentials: 'include'})
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
    var mainContainer = document.getElementById("transactionsData");
    for (var i = 0; i < data.length; i++) {
        var div = document.createElement("div");
        //var transactionlink = url+"/transactions.html?account="+encodeURI(data[i].iban);
        div.innerHTML = "Transaction id: " + data[i].id + "<br>" + "Amount " + data[i].amount + "<br>Description: " + data[i].description + "<br>" + "Type: " + data[i].transactionType + "<br>" +"Date: " + data[i].timestamp + "<br><br>"
        mainContainer.appendChild(div);
    }
}