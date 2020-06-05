const form = document.getElementById('payment-form');
form.addEventListener('submit', function (e) {
e.preventDefault();
const formData = new FormData(this);

    fetch(url + '/Transactions/pay', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: form
    });

    console.log(form);
});



