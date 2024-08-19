document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const userType = document.getElementById('userType').value;

    const data = {
        email: email,
        username: username,  // Make sure this key matches what the backend expects
        password: password,
        userType: userType
    };

    fetch('/api/auth/register', {  // Ensure the URL is correct for your backend
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            console.log(response); // Log the raw response for debugging
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
