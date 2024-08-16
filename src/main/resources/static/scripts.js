function searchJobs() {
    const title = document.getElementById('search-title').value;
    const jobList = document.getElementById('job-list');
    jobList.innerHTML = '';

    fetch(`/api/jobs?title=${title}`)
        .then(response => response.json())
        .then(jobs => {
            if (jobs.length > 0) {
                jobs.forEach(job => {
                    const li = document.createElement('li');
                    li.innerHTML = `<h3>${job.title}</h3><p>${job.description}</p>`;
                    jobList.appendChild(li);
                });
            } else {
                jobList.innerHTML = '<li>No jobs found</li>';
            }
        })
        .catch(error => console.error('Error fetching jobs:', error));
}

function postJob() {
    const title = document.getElementById('post-title').value;
    const description = document.getElementById('post-description').value;
    const employerId = 1; // Example employer ID

    fetch(`/api/jobs/post?employerId=${employerId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ title, description })
    })
        .then(response => response.json())
        .then(data => {
            alert(data.message);
            document.getElementById('post-title').value = '';
            document.getElementById('post-description').value = '';
        })
        .catch(error => console.error('Error posting job:', error));
}

function startChat() {
    const employerId = document.getElementById('employer-id').value;
    const jobSeekerId = document.getElementById('job-seeker-id').value;

    fetch(`/api/chats/start`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ employerId, jobSeekerId })
    })
        .then(response => response.json())
        .then(data => {
            alert(data.message);
            document.getElementById('employer-id').value = '';
            document.getElementById('job-seeker-id').value = '';
        })
        .catch(error => console.error('Error starting chat:', error));
}
