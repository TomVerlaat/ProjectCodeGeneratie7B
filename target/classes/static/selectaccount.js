fetch(url +'/Accounts/GetByUserId',{credentials:'include'})
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
    var select = document.getElementById("select");
    for (var i = 0; i < data.length; i++) {
        var option = document.createElement("OPTION"),
            txt = document.createTextNode(data[i].iban + " Balance: " + data[i].balance);
        option.appendChild(txt);
        option.setAttribute("value", data[i].iban);
        select.insertBefore(option, select.lastChild);
    }
}