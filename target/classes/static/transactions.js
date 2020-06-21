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

        var accountTo = data[i].accountTo;
        var transactionOutgoing = accountTo.localeCompare(account);

        if(transactionOutgoing)
        {
            showDirection = '<p style="color:red">Outgoing</p>';
            showAmount = '<div style="color:red">' +  data[i].amount + '</div>';
            showIban = '<div>' +  data[i].accountTo + '</div>';

        }
        else
        {
            showDirection = '<p style="color:green">Incoming</p>';
            showAmount = '<div style="color:green">' +  data[i].amount + '</div>';
            showIban = '<div>' +  data[i].accountFrom + '</div>';
        }

        div.innerHTML =  showDirection + "Transaction id: " + data[i].id + showIban + "Amount: " + showAmount + "Description: " + data[i].description + "<br>" + "Type: " + data[i].transactionType + "<br>" +"Date: " + data[i].timestamp + "<br><br>"
        mainContainer.appendChild(div);
    }
}