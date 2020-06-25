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
    var select = document.getElementById("getAccountFrom");
    var options = '';
    for (var i = 0; i < data.length; i++) {
        options+= '<option value="'+data[i].iban+'" />';

        var option = document.createElement("OPTION"),
            txt = document.createTextNode(data[i].iban + " balance: " + data[i].balance);
        option.appendChild(txt);
        option.setAttribute("value", data[i].iban);
        select.insertBefore(option, select.lastChild);
    }
    //select.innerHtml = options;
}