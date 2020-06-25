fetch(url +'/Accounts/GetByCurrentUser',{credentials:'include'})
    .then(function (response) {
        return response.json();
    })
    .then(function (data) {
        appendData(data);
    })
    .catch(function (err) {
        console.log(err);
    });

function appendData(data) {
    var mainContainer = document.getElementById("accountsData");
    for (var i = 0; i < data.length; i++) {
        var div = document.createElement("div");
        var transactionlink = url+"/transactions.html?account="+encodeURI(data[i].iban);
        div.innerHTML = data[i].type + "<br>" + "IBAN: " + data[i].iban + "<br>Balance: " + data[i].balance + " " + data[i].currency + " <br> Active: " + data[i].active + " " + '<a href="' + transactionlink + '">Show Transactions</a>' + "<br><br>"
        mainContainer.appendChild(div);
    }
}