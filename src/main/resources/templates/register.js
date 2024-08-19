document.getElementById('registerForm').addEventListener('SUBMIT', function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const userType = document.getElementById('userType').value;

    const data = {
        email: email,
        password: password,
        userType: userType,
        username: username
    };

    fetch('/api/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('Registration successful!');
                document.getElementById('registerForm').reset();
                window.location.href = '/login.html';
            } else {
                alert('<h1>Registration failed:</h1> ' + data.message);
            }
        })
        .catch(error => console.error('Error:', error));
});
