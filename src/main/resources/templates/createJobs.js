document.getElementById('jobPostForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const title = document.getElementById('jobTitle').value;
    const description = document.getElementById('jobDescription').value;
    const employerId = 1; // Example employer ID

    fetch('/api/jobs/post?employerId=' + employerId, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ title, description })
    })
        .then(response => response.json())
        .then(data => {
            alert(data.message);
            document.getElementById('jobPostForm').reset();
        })
        .catch(error => console.error('Error posting job:', error));
});
