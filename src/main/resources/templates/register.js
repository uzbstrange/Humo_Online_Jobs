document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('emailId').value;
    const username = document.getElementById('usernameId').value;
    const password = document.getElementById('passwordId').value;
    const userType = document.getElementById('userTypeId').value;

    const data = {
        email: email,
        username: username,
        password: password,
        userType: userType
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
                window.location.href = '/login.html'; // Redirect after registration
            } else {
                alert('Registration failed: ' + data.message);
            }
        })
        .catch(error => console.error('Error:', error));
});
