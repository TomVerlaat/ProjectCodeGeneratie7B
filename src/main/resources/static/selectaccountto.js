fetch(url +'/Accounts/GetByCurrentUser',{credentials:'include'})
    .then(function (response) {
        return response.json();
    })
    .then(function (data) {
        appendData2(data);
    })
    .catch(function (err) {
        console.log(err);
    });

function appendData2(data) {
    var select = document.getElementById("getAccountTo");
    var options = '';
    for (var i = 0; i < data.length; i++) {
        options += '<option value="' + data[i].iban + '" />';

        var option = document.createElement("OPTION"),
            txt = document.createTextNode(data[i].iban + " balance: " + data[i].balance + " type: " + data[i].type);
        option.appendChild(txt);
        option.setAttribute("value", data[i].iban);
        select.insertBefore(option, select.lastChild);
    }
}