document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const userType = document.getElementById('userType').value;

    const data = {
        email: email,
        username: username,
        password: password,
        userType: userType
    };

    fetch('http://localhost:8080/api/auth/register', {
        method: 'POST',
            headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: document.getElementById('email').value,
            username: document.getElementById('username').value,
            password: document.getElementById('password').value,
            userType: document.getElementById('userType').value
        })
    })
.then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
        .then(data => {
            if (data.success) {
                alert('Registration successful!');
                document.getElementById('registerForm').reset();
                window.location.href = '/login.html';
            } else {
                alert('Registration failed: ' + data.message);
            }
        })
        .catch(error => console.error('Error:', error));

});
