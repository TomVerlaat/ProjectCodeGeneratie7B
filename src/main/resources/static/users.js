fetch(url +'/Users',{credentials:'include'})
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
    var mainContainer = document.getElementById("usersData");
    for (var i = 0; i < data.length; i++) {
        var div = document.createElement("div");
        var updateLink= url+"/updateuser.html?userid="+encodeURI(data[i].id);
        var deactivationlink = url+"/deactivateuser.html?userid="+encodeURI(data[i].id);
        div.innerHTML = data[i].username + "<br>" + "email: " + data[i].email + "<br>address: " + data[i].address + "<br>Active: " + data[i].active + "<br><a href=" + updateLink + ">update user</a>" + '<br><a href="' + deactivationlink + '">deactivate user</a>' + "<br><br>"
        mainContainer.appendChild(div);
    }
}