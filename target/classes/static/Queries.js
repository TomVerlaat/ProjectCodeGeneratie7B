let domain= "http://localhost:8080/Groep7B/BankAPI_V3/1.0.0";

fetch(domain +'/Accounts/GetByUserId',{credentials:'include'})
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
    var mainContainer = document.getElementById("myData");
    for (var i = 0; i < data.length; i++) {
        var div = document.createElement("div");
        var transactionlink = domain+"/Transactions/all/"+data[i].iban;
        div.innerHTML = data[i].type + "<br>" + "IBAN: " + data[i].iban + "<br>Balance: " + data[i].currency + " " + '<a href="' + transactionlink + '">Show Transactions</a>' + "<br><br>"
        mainContainer.appendChild(div);
    }
}